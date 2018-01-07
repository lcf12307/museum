package com.crtvu.web;


import com.crtvu.dto.DeleteJson;
import com.crtvu.entity.AttachmentEntity;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private com.crtvu.service.AttachmentService AttachmentService;
    @Autowired
    private com.crtvu.service.QuotaService quotaService;

    @RequestMapping(value = "/list")
    public  String list1(){
        return "redirect: /score/list/1";
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
            String format = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            for(AttachmentEntity attament:list){
                attament.setAddtime(sdf.format(new Date(Long.valueOf(attament.getAddtime()))));
            }
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
            String realname = fileName.substring(fileName.indexOf("_") + 1);
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

}
