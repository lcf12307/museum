package com.crtvu.web;


import com.crtvu.dao.MuQASDAO;
import com.crtvu.dto.DeleteJson;
import com.crtvu.dto.DingxingScore;
import com.crtvu.dto.TotalScore;
import com.crtvu.entity.AttachmentEntity;
import com.crtvu.entity.MuseumEntity;
import com.crtvu.entity.PointEntity;
import com.crtvu.service.ScoreService;
import com.crtvu.utils.ExcelShower;
import com.crtvu.utils.R;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private com.crtvu.service.AttachmentService AttachmentService;
    @Autowired
    private com.crtvu.service.QuotaService quotaService;
    @Autowired
    ScoreService scoreService;
    @Autowired
    MuQASDAO muQASDAO;

    @RequestMapping(value = "/list")
    public  String list1(){
        return "redirect:/score/list/1";
    }
    @RequestMapping(value = "/list/{page}",method = RequestMethod.GET)
    public String list(@RequestParam(value = "name",defaultValue = "") String name, @RequestParam(value = "year",defaultValue="0") int year,
                       Model model, @PathVariable("page") int page)
    {
        try{
            name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
            List<AttachmentEntity> list= AttachmentService.pagingAttachment(page,year , name,2);
            model.addAttribute("count",page);
            int pages = AttachmentService.page(year,name,2)/20 + 1;
            model.addAttribute("list",list);
            model.addAttribute("pages",pages);
            if (year == 0){
                year = 2018;
            }
            List<String> experts = quotaService.selectByYearPoint(Integer.toString(year));

            model.addAttribute("experts",experts);
            System.out.println(experts);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "score/list";
    }

    @RequestMapping(value = "getExperts",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public List<String> getExperts(@RequestBody DeleteJson deletejson,HttpServletRequest request)
    {
        String year = deletejson.getName();
        List<String> experts = quotaService.selectByYearPoint(year);
        if (experts == null){
            experts = new ArrayList<>();
        }

        return experts;
    }
    @RequestMapping(value = "delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public DeleteJson delete(@RequestBody DeleteJson deletejson)
    {
        int result = AttachmentService.delete(  Integer.parseInt(deletejson.getId()));
        deletejson.setId(Integer.toString(result));
        return deletejson;
    }
    @RequestMapping(value = "deleteFile",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public DeleteJson deleteFiles(@RequestBody DeleteJson deleteJson,
                                  HttpServletRequest request, HttpServletResponse response){
        int res=0,id;
        try {
            id = deleteJson.getPage();

            AttachmentService.delete(id);
            deleteJson.setPage(1);
        } catch (Exception e) {

        }
        deleteJson.setPage(res);
        return deleteJson;
    }

    @RequestMapping("/downFile")
    public void downFile(HttpServletRequest request,
                         HttpServletResponse response) {
        // 得到要下载的文件名
        int id = Integer.valueOf(request.getParameter("file"));
        AttachmentEntity attachmentEntity = AttachmentService.findById(id);
        String fileName;
        try {
            fileName = attachmentEntity.getFile();

            // 得到要下载的文件
            File file = new File(fileName);

            // 如果文件不存在
            if (!file.exists()) {
                request.setAttribute("message", "您要下载的资源已被删除！！");
                System.out.println("您要下载的资源已被删除！！");
                return;
            }else{
                request.setAttribute("message", "success");
            }
            String realname = fileName.substring(fileName.lastIndexOf("\\") + 1);
            response.setHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode(realname, "UTF-8"));
            FileInputStream in = new FileInputStream( fileName);
            // 创建输出流
            OutputStream out = response.getOutputStream();
            // 创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
            // 循环将输入流中的内容读取到缓冲区当中
            while ((len = in.read(buffer)) > 0) {
                // 输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
            // 关闭文件输入流
            in.close();
            // 关闭输出流
            out.close();
        } catch (Exception e) {

        }
    }

    @RequestMapping("/detail")
    public String detail(int id,Model model) {
        // 得到要下载的文件名
        if(id<=0) {
            model.addAttribute("table","参数错误");
            return "/score/detail";
        }
        String table ="";
        AttachmentEntity attachmentEntity = AttachmentService.findById(id);
        String fileName = attachmentEntity.getFile();
        if(fileName==null||fileName.equals("")) {
            model.addAttribute("table","文件错误");
            return "/score/detail";
        }
        try {
            table = ExcelShower.read(fileName).toString();
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("table","文件错误");
            return "/score/detail";
        }
        System.out.println(table);
        model.addAttribute("table",table);
        return "/score/detail";
    }


    @RequestMapping(value = "/upload")
    public String handleFormUpload(@RequestParam("uploadName") String name, @RequestParam("uploadYear") int year,
                                   @RequestParam("file") MultipartFile[] myfiles, HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("UTF-8");
        name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        // 对name进行处理
        String filename ;
        for(MultipartFile mf :myfiles){
            String realPath = request.getSession().getServletContext()
                    .getRealPath("\\WEB-INF\\upload");
            File uploadDir = new File(realPath+"\\专家打分表_"+name+"_"+year);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            try {


                filename  = new String(mf.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");
                File newfile = new File(uploadDir  + "\\"+ filename);
                if (AttachmentService.findAttachment(year,name,2) == 0){
                    AttachmentService.addAttachment(year,name,realPath+"\\专家打分表_"+name+"_"+year+"\\"+filename,2) ;
                }
                InputStream is = mf.getInputStream();
                System.out.println(is);
                FileUtils.copyInputStreamToFile(mf.getInputStream(),newfile);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (request.getParameter("add")!=null && request.getParameter("add").equals("1")){
            return "redirect:/score/listFile?dir="+name+"_"+year+"&add=1";
        }
        return "redirect:/score/list/1?upload=success";
    }


    @RequestMapping(value = "/dingxing")
    @ResponseBody
    public R dingxing(String year){
        if(!canParseInt(year)||Integer.parseInt(year)<=0)
            return R.error("参数错误");
        R r = R.error(-99,"未知错误");
        try {
            r=scoreService.Calculate(String.valueOf(year));
        } catch (Exception e) {
            if(canParseInt(e.getMessage())){
                if(Integer.parseInt(e.getMessage())<0){
                    r=R.error(Integer.parseInt(e.getMessage()),e.getCause().getMessage());
                }
            }else{
                r=R.error("未知错误！");
            }
        }
        return r;
    }

    @RequestMapping("/listDingXingScore/{year}")
    public String dingXingScore(@PathVariable("year") int year,Model model){
        List<MuseumEntity> museumList = muQASDAO.getAllMuseum(year);
        List<DingxingScore> scoreList = new ArrayList<>();
        double total ;
        for(MuseumEntity museum: museumList){
            total=0;
            List<PointEntity> points = muQASDAO.getOneMuseumScore(museum.getId(),year);
            DingxingScore score = new DingxingScore();
            for(PointEntity point:points){

                if(point.getType()==21){
                    score.setScore21(point.getPoint());
                    total+=point.getPoint()*0.2;
                }else if(point.getType()==22){
                    score.setScore22(point.getPoint());
                    total+=point.getPoint()*0.2;
                }else if(point.getType()==23){
                    score.setScore23(point.getPoint());
                    total+=point.getPoint()*0.35;
                }else if(point.getType()==24){
                    score.setScore24(point.getPoint());
                    total+=point.getPoint()*0.15;
                }else if(point.getType()==25){
                    score.setScore25(point.getPoint());
                    total+=point.getPoint()*0.1;
                }
                score.setName(point.getName());
                score.setYear(point.getType());
            }
            score.setTotal(total);
            scoreList.add(score);
        }

        model.addAttribute("scoreList",scoreList);
        model.addAttribute("year",year);
        return "/score/listDingXingScore";
    }

    @RequestMapping("/total/{year}")
    public String getTotalStatic(@PathVariable("year") int year,Model model){
        List<MuseumEntity> museumList = muQASDAO.getAllMuseum(year);
        List<TotalScore> totalScoreList = new ArrayList<>();
        PointEntity point;
        for(MuseumEntity museum: museumList){
            TotalScore totalScore = new TotalScore();
            totalScore.setName(museum.getName());
            totalScore.setYear(year);
            point = muQASDAO.getTotalStatic(museum.getName(),1,year);
            totalScore.setDingliang(point==null?0:point.getPoint());
            point = muQASDAO.getTotalStatic(museum.getName(),2,year);
            totalScore.setDingxing(point==null?0:point.getPoint());
            totalScore.setTotal();
            totalScoreList.add(totalScore);
        }
        model.addAttribute("year",year);
        model.addAttribute("totalScoreList",totalScoreList);
        return "/score/total";
    }

    public static boolean canParseInt(String  str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
