package com.crtvu.service.implementation;


import com.crtvu.entity.Quota;
import com.crtvu.service.QuotaService;
import com.crtvu.service.AttachmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.crtvu.dao.QuotaDAO;
import org.springframework.util.DigestUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.*;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yangming on 2017/3/16/0016.
 */
@Service
public class QuotaServiceImpl implements QuotaService {
    //日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private QuotaDAO quotaDAO;
    @Autowired
    private AttachmentService attachmentService;
    private static final int pageNumber = 10;


    //搜索出某年份的参评的专家
    public List<Quota> getExpertList(int page, String expertProperty) {
        if (page <= 0 || page > getPageCount(expertProperty)) {
            System.out.println("搜索某年的专家");
            return null;
        }
        System.out.println("搜索某年的专家123");
        return quotaDAO.selectExpertByLimit(expertProperty, (page - 1) * pageNumber, pageNumber);
    }

    //搜索出某年份待评审的专家
    public List<Quota> getExpertListWait(int page, String expertProperty) {
        if (page <= 0 || page > getPageCountNot(expertProperty)) {
            return null;
        }
        return quotaDAO.selectExpertByNoLimit(expertProperty, (page - 1) * pageNumber, pageNumber);
    }


    //搜索专家数量
    public int getPageCount(String expertProperty) {
        int count = quotaDAO.countAllExpert(expertProperty);
        int page_count = count == 0 ? 1 : count / pageNumber + (count % pageNumber > 0 ? 1 : 0);
        System.out.println("专家数量");
        return page_count;
    }

    public int getPageCountNot(String expertProperty){
        int count = quotaDAO.countAllExpert("")-quotaDAO.countAllExpert(expertProperty);
        int page_count = count == 0 ? 1 : count / pageNumber + (count % pageNumber > 0 ? 1 : 0);
        return page_count;
    }
    //通过专家名字查找
    public Quota getExpert(String name) {
        return quotaDAO.selectExpert(name);
    }

    //通过专家ID查找
    public Quota getExpertById(int id){return  quotaDAO.selectById(id);}

    //通过专家名字模糊查询
    public List<Quota> getExpertByName(int page, String expertProperty){
        if (page <= 0 || page > getPageCount(expertProperty)) {
            return null;
        }
        return quotaDAO.selectByName(expertProperty, (page - 1) * pageNumber, pageNumber);
    }

    //得到所有的专家列表
    public List<Quota> getAllExpertList() {
        return quotaDAO.selectAllExpert();
    }


    //添加专家

    public Result insertExpert(Quota quota) {
        String name1 = quota.getName();
        Quota e1 = quotaDAO.selectExpert(name1);
        //判断有名字是否重复

        if (e1 != null) {
            return Result.NAME_FAIL;
        }

        try {
            quotaDAO.insertExpert(quota.getName(), quota.getQuotaId(), quota.getDescription());
        } catch (Exception e) {
            return Result.UNKNOWN_FAIL;
        }
        return Result.SUCCESS;
    }

    //删除专家

    public Result deleteExpert(String name) {
        Quota e1 = quotaDAO.selectExpert(name);

        String year = e1.getYear();
        System.out.println("==========================");
        System.out.println("AAAAA"+year+"BBBBBBBBBB");
        System.out.println("==========================");
        //此处需要调用point判断是否上传打分表
        if( year.length() != 0){
            System.out.println("这是在测试删除111111111");
            return Result.YEAR_FAIL;
        }else {
            System.out.println("这是在测试删除2222222222");
        return quotaDAO.deleteExpert(name) > 0 ? Result.SUCCESS : Result.NAME_FAIL;
        }
    }

    //更新未参与评审的专家信息
    public Result updateExpert(String name, String quotaId, String description, int id) {
        int result;
        Quota e1 = quotaDAO.selectExpert(name);

        if(e1 == null){
            result = quotaDAO.updateExpert(name, quotaId, description, id);
            System.out.println("========11111111=======");
        }else if(id == e1.getId()){
            result = quotaDAO.updateExpert(name, quotaId, description, id);
            System.out.println("========22222222=======");
        }else{
            return Result.NAME_FAIL;
        }
        return result > 0 ? Result.SUCCESS : Result.ID_FAIL;
    }


    //修改参与评审的专家简介
    public Result updateExpertOnDesc(String description, String name) {

        int result;
        try {
            result = quotaDAO.updateExpertOnDesc(description, name);
        } catch (Exception e) {
            return Result.NAME_FAIL;
        }
        return result > 0 ? Result.SUCCESS : Result.NAME_FAIL;
    }

    //指定评审专家
    public Result addExpertYear(String year, String name) {
        int result;

        try {
        result = quotaDAO.addExpertYear(year,name);

        } catch (Exception e) {
          return Result.NAME_FAIL;
        }

        return result > 0 ? Result.SUCCESS : Result.NAME_FAIL;
    }

    //取消某年评审专家
    public Result movExpertYear(String year , String name){
        int result;
        String year1 = year + ';';
        int year2 = Integer.parseInt(year);

        //此处添加判断专家是否上传打分表
        if(attachmentService.findAttachment(year2,name,2) > 0){
            return  Result.POINT_FAIL;
        }

        try{
            result = quotaDAO.movExpertYear(year1,name);

        }catch (Exception e){
            return Result.NAME_FAIL;
        }
        return result > 0 ? Result.SUCCESS : Result.NAME_FAIL;
    }

    /*
    搜索某年份的未上传打分表的专家
     */
       public List<String> selectByYearPoint(String year){
          List<Quota> list = quotaDAO.selectExpertByYear(year);
          List<String> list2 = new ArrayList();

          int year1 = Integer.parseInt(year);
          int asize =0;

           if(list.isEmpty()){
               return  null;
           }else{
               asize = list.size();
           }

           for(int i=0; i<asize; i++){
               Quota quo = list.get(i);
               String name = quo.getName();
               if(attachmentService.findAttachment(year1,name,2) > 0){
                   list2.add(name);
               }
           }
           return list2;

       }

}