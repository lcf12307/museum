package com.crtvu.service.implementation;

import com.crtvu.dao.MuQASDAO;
import com.crtvu.dao.PointDAO;
import com.crtvu.dto.StrValue;
import com.crtvu.entity.AttachmentEntity;
import com.crtvu.entity.MuseumEntity;
import com.crtvu.entity.Quota;
import com.crtvu.service.*;
import com.crtvu.utils.CheckExcelFileTypeUtil;
import com.crtvu.utils.R;
import com.crtvu.utils.ReadExcelUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Jixw on 2018/1/7.
 */
@Service
public class ScoreServiceImpl implements ScoreService{

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    MuseumService museumService;

    @Autowired
    QuotaService quotaService;

    @Autowired
    PointService pointService;

    @Autowired
    PointDAO pointDAO;

    @Autowired
    MuQASDAO muQASDAO;

    @Override
    public Map<Integer, HashMap<String, Integer>> ReadScoreExcel22() {
        return null;
    }

    /**
     *
     * @param path
     * @return
     *  code=0时 ,Object 为 LIst<String,int>类型
     *  code为其他时，Object为String类型的错误提示信息
     */
    public Map<String,Integer> ReadScoreExcel(String path) {


        File file = new File(path);
        if(!file.exists()||!file.isFile())
            return null;
        if(!CheckExcelFileTypeUtil.getFileType(file).equals("xls"))
            return null;
        try{
            HSSFSheet sheet = ReadExcelUtils.getSheet(file,0);//拿第一个sheet
            String[] titles = ReadExcelUtils.readExcelTitle(sheet);

            if(titles==null||titles.length!=2){
                return null;
            }
            return ReadExcelUtils.readMuseumScore(sheet,2);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Transactional(propagation= Propagation.REQUIRED, rollbackFor=Exception.class)
    public R Calculate(String year) throws Exception{
        HashSet<String> quota_ids=new HashSet<>();
        quota_ids.add("科学研究");
        quota_ids.add("藏品管理");
        quota_ids.add("公共关系与服务");
        quota_ids.add("陈列展览与社会教育");
        quota_ids.add("博物馆管理发展建设");
        String tempString = "";
        int tempInt=0;

        if(year==null&&year.equals(""))
            return R.error(-1,"年份参数错误");

        //1、检查每个指标的参评专家数量是否为大于等于3的奇数
        List<StrValue> strValues=muQASDAO.getQuotaIdCount(year);
        System.out.println(strValues);
        for(StrValue strValue:strValues){
            if(quota_ids.contains(strValue.getStr())&&strValue.getValue()>=3&&strValue.getValue()%2==1){
                quota_ids.remove(strValue.getStr());
            }
        }
        if(quota_ids.size()>0) {
            for(String e:quota_ids){
                tempString += ","+e;
            }
            tempString=tempString.substring(1);
            return R.error(-2, "有（"+tempString+"）等一级指标指定的专家数不满足条件：专家数量应该为大于或等于3的奇数");
        }

        //2、检查该年份所有的参评专家是否都上传了文件
        List<String> quotas = quotaService.selectByYearPoint(year);
        if (quotas!=null&&quotas.size()>0){
            return R.error(-3,"还有"+quotas.size()+"个参审专家没有上传打分表！");
        }

        //3、读所有参评博物馆
        List<MuseumEntity> museums = muQASDAO.getAllMuseums(year);
        if(museums==null|museums.size()==0){
            return R.error(-4,"系统错误，没有参评博物馆");
        }
        HashSet<String> museumSet = new HashSet<>();
        HashMap<String,Integer> museumMap = new HashMap<>();
        for(MuseumEntity museum:museums){
            museumSet.add(museum.getName());
            museumMap.put(museum.getName(),museum.getId());
        }

        //4、删除之前的该年的定性打分数据
        muQASDAO.deletePoint(Integer.parseInt(year),21,25);
        muQASDAO.deletePoint(Integer.parseInt(year),2,2);//总分

        //5、读所有文件并写入定性打分数据
        HashSet<String> museumSet2 = new HashSet<>();
        List<AttachmentEntity> attachments = attachmentService.findByYear(Integer.parseInt(year),2);
        int pointType ;
        String quota_id;
        for(AttachmentEntity e:attachments){
            pointType=0;
            quota_id="";
            quota_id = quotaService.getExpert(e.getName()).getQuotaId();
            if(quota_id==null||quota_id.equals(""))
                throw new RuntimeException();
            if(quota_id.equals("科学研究"))
                pointType=21;
            else if(quota_id.equals("藏品管理"))
                pointType=22;
            else if(quota_id.equals("公共关系与服务"))
                pointType=23;
            else if(quota_id.equals("陈列展览与社会教育"))
                pointType=24;
            else if(quota_id.equals("博物馆管理发展建设"))
                pointType=25;
            else
                throw new RuntimeException();
            Map<String,Integer> museumPointmap = ReadScoreExcel(e.getFile());
            if(museumPointmap==null||museumPointmap.size()==0){
				throw new RuntimeException("-5",new Throwable("专家："+e.getName()+"上传的文件格式存在问题，请检查"));
            }
            for(String key:museumPointmap.keySet()){
                museumSet2.add(key);
            }
            if(museumSet2.size()!=museumPointmap.size()){
				throw new RuntimeException("-5",new Throwable(e.getName()+"上传的文件的博物馆存在重复的博物馆名，请检查"));
            }
            if(!museumSet.equals(museumSet2)){
                throw new RuntimeException("-5",new Throwable(e.getName()+"上传的文件的博物馆数量存在问题，请检查"));
                //return R.error(-5,"专家："+e.getName()+"上传的文件的博物馆数量存在问题，请检查");
            }
            for(String key:museumPointmap.keySet()){
                //pointType需要根据专家类型来判断，新写一个查询
                if(pointService.addPoint(e.getName(),museumMap.get(key),Integer.parseInt(year),museumPointmap.get(key),pointType)<=0)
                    throw new RuntimeException("-6",new Throwable("插入定性分数时异常"));
            }
            museumSet2.clear();
        }
        //计算总分
        //一级指标 占比 type
        //科学研究 20%  21
        //藏品管理 20%  22
        //公共关系与服务 35%  23
        //陈列展览与社会教育 15%  24
        //博物馆管理发展建设 10%  25
        if(muQASDAO.staticsTotal(Integer.parseInt(year))<=0)
            throw new RuntimeException("-6",new Throwable("统计定性总分时发生错误"));;

        return R.ok("定性数据生成成功");
    }
	
	

    @Transactional
    public void testTran() throws RuntimeException{
        pointDAO.insertPoint("1",1,2017,8,21);
        pointDAO.insertPoint("2",1,2017,8,21);
        if(pointDAO.insertPoint("4",1,2017,8,21)>0){
            throw new RuntimeException("回滚");
        }
        pointDAO.insertPoint("3",1,2017,8,21);

    }
}
