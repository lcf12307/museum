package com.crtvu.web;

import com.crtvu.auth.Auth;
import com.crtvu.dto.DeleteJson;
import com.crtvu.entity.AttachmentEntity;
import com.crtvu.service.AttachmentService;
import com.crtvu.utils.FileUnZip;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipOutputStream;


@Controller
@RequestMapping("/notification")
public class NotificationController {
    @Autowired
    private AttachmentService AttachmentService;

    @Auth("NotificationForm")
    @RequestMapping(value = "/list")
    public  String list1(){
        return "redirect:/notification/list/1";
    }

    @Auth("NotificationForm")
    @RequestMapping(value = "/list/{page}",method = RequestMethod.GET)
    public String list(@RequestParam(value = "name",defaultValue = "") String name,@RequestParam(value = "year",defaultValue="0") int year,
                       Model model, @PathVariable("page") int page)
    {
        try{
            name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
            List<AttachmentEntity> list= AttachmentService.pagingAttachment(page,year , name,1);
            String format = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            for(AttachmentEntity attament:list){
                attament.setAddtime(sdf.format(new Date(Long.valueOf(attament.getAddtime()))));
            }
            model.addAttribute("count",page);
            int pages = AttachmentService.page(year,name,1)/10 + 1;
            model.addAttribute("list",list);
            model.addAttribute("pages",pages>0?pages:1);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "notification/list";
    }

    @Auth("NotificationForm")
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public DeleteJson delete(@RequestBody DeleteJson deletejson)
    {
        int result = AttachmentService.delete(  Integer.parseInt(deletejson.getId()));
        deletejson.setId(Integer.toString(result));
        return deletejson;
    }

    @Auth("NotificationForm")
    @RequestMapping(value = "/upload")
    public String handleFormUpload(@RequestParam("uploadName") String name,@RequestParam("uploadYear") int year,Model model,
                                   @RequestParam("file") MultipartFile[] myfiles,HttpServletRequest request) throws Exception{
        request.setCharacterEncoding("UTF-8");
        name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        // 对name进行处理
        String filename ;
       for(MultipartFile mf :myfiles){
           String realPath = request.getSession().getServletContext()
                   .getRealPath("\\WEB-INF\\upload");
           File uploadDir = new File(realPath+"\\"+name+"_"+year);
           if (!uploadDir.exists()) {
               uploadDir.mkdirs();
           }
           try {

               if (AttachmentService.findAttachment(year,name,1) == 0){
                   AttachmentService.addAttachment(year,name,realPath+"/"+name+"_"+year,1) ;
               }
               filename  = new String(mf.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");

//               if(filename.matches(".*\\.zip")){                //是zip压缩文件
//
//                   FileInputStream fis = new FileInputStream(uploadDir  + "\\"+filename);
//                   ZipInputStream zis = new ZipInputStream(fis);
//                   ZipEntry z1 = null;
//                   while((z1 = zis.getNextEntry()) != null){                    //获取zip包中的每一个zip file entry
//                       String fileName = z1.getName();
//                       FileOutputStream fos = new FileOutputStream(uploadDir+ "\\"+fileName);
//                       int tmp = -1;
//                       while((tmp = zis.read()) != -1){
//                           fos.write(tmp);
//                       }
//                       zis.closeEntry();
//                       fos.close();
//                   }
//                   return "success";
//               }else if(filename.matches(".*\\.rar")) {                    //是rar压缩文件
//                       //MultipartFile file 转化为File 有临时文件产生：
//                   CommonsMultipartFile cf = (CommonsMultipartFile) file;
//                   DiskFileItem fi = (DiskFileItem) cf.getFileItem();
//                   File fs = fi.getStoreLocation();
//                   Archive archive = new Archive(fs);
//                   ByteArrayOutputStream bos = null;
//                   byte[] picture = null;
//                   FileHeader fh = archive.nextFileHeader();
//                   while (fh != null) {
//                       String fileName = fh.getFileNameString();
//                       bos = new ByteArrayOutputStream();
//                       archive.extractFile(fh, bos);
//                       picture = bos.toByteArray();
//                       ImageFile image = new ImageFile(packageName, "N", fileName, picture); //保存image，非缩略图
//                       productService.insertImage(image);
//                       fh = archive.nextFileHeader();
//                   }
//
//                   bos.close();
//                   archive.close();
//                   return "success";
//
//               }else{
                   File newfile = new File(uploadDir  + "\\"+ filename);
                   InputStream is = mf.getInputStream();
                   System.out.println(is);
                   FileUtils.copyInputStreamToFile(mf.getInputStream(),newfile);
//               }




           } catch (Exception e) {
               e.printStackTrace();
           }
       }
        if (request.getParameter("add")!=null && request.getParameter("add").equals("1")){
           String dir = name+"_"+year;

            model.addAttribute("dir",dir);
            model.addAttribute("add",1);
           return "redirect:/notification/listFile";//?dir="+dir+"&add=1";
        }
        model.addAttribute("upload","success");
        return "redirect:/notification/list/1";
    }



    @Auth("NotificationForm")
    @RequestMapping("/listFile")
    public String listFile(@RequestParam("dir") String dir,
                           HttpServletRequest request,   HttpServletResponse response) {

        try{
            dir  = new String(dir.getBytes("ISO-8859-1"), "UTF-8");
            request.setAttribute("dir",dir);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(request.getParameter("add")!=null && request.getParameter("add").equals("1")){
            String[] params = dir.split("_");

            request.setAttribute("add",1);
            request.setAttribute("name",params[0]);
            request.setAttribute("year",params[1]);
        } else {
            request.setAttribute("add",0);
        }
        String realPath = request.getSession().getServletContext()
                .getRealPath("\\WEB-INF\\upload");
        dir = realPath+"\\"+dir;
        Map<String, String> fileNameMap = new HashMap<String, String>();
        // 递归遍历filepath目录下的所有文件和目录，将文件的文件名存储到map集合中
        String[] files = new File(dir).list();
        if ( files != null) {
            for ( String file:files  ) {

                String[] names=file.split("/");
                String filename = "";
                for (String name:names){
                    filename=name;
                }
                fileNameMap.put(filename,file);
            }
        }

        // 将Map集合发送到listfile.jsp页面进行显示
        request.setAttribute("fileNameMap", fileNameMap);
        return "notification/listFile";
    }

    @Auth("NotificationForm")
    @RequestMapping("/downFile")
    public void downFile(HttpServletRequest request,
                         HttpServletResponse response) {
        // 得到要下载的文件名
        String fileName = request.getParameter("filename");
        String dir = request.getParameter("dir");String realPath = request.getSession().getServletContext()
                .getRealPath("\\WEB-INF\\upload");
        dir = realPath+"/"+dir;
        try {
            fileName = new String(fileName.getBytes("iso8859-1"), "UTF-8");
            dir = new String(dir.getBytes("iso8859-1"), "UTF-8");

            // 得到要下载的文件
            File file = new File(dir + "\\" + fileName);

            // 如果文件不存在
            if (!file.exists()) {
                request.setAttribute("message", "您要下载的资源已被删除！！");
                System.out.println("您要下载的资源已被删除！！");
                return;
            }else{
                request.setAttribute("message", "success");
            }
            // 处理文件名
            String realname = fileName.substring(fileName.indexOf("_") + 1);
            // 设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename="
                    + URLEncoder.encode(realname, "UTF-8"));
            // 读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(dir + "\\" + fileName);
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

    @Auth("NotificationForm")
    @RequestMapping(value = "deleteFile",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public DeleteJson deleteFiles(@RequestBody DeleteJson deleteJson,
                                 HttpServletRequest request,   HttpServletResponse response){
        int res=0;
        String realPath = request.getSession().getServletContext()
                .getRealPath("\\WEB-INF\\upload");
        String filename,dir;
        try {
            filename = deleteJson.getName();
            dir = realPath+"/"+deleteJson.getId();

            // 得到要下载的文件
            File file = new File(dir + "\\" + filename);
            if (!file.exists()) {
                System.out.println("删除文件失败:" + filename + "不存在！");
                deleteJson.setPage(0);
            } else {
                res = file.delete()?1:0;
            }
        } catch (Exception e) {

        }
        deleteJson.setPage(res);
        return deleteJson;
    }

    @Auth("NotificationForm")
    @RequestMapping(value = "/downloadZip")
    public void downloadFiles(@RequestParam("dir") String dir, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<File> files = new ArrayList<File>();
        File Allfile = new File(dir);
        if (Allfile.exists()) {
            File[] fileArr = Allfile.listFiles();
            for (File file2 : fileArr) {
                files.add(file2);
            }
        }
        String fileName = UUID.randomUUID().toString() + ".zip";
// 在服务器端创建打包下载的临时文件
        String outFilePath = request.getSession().getServletContext().getRealPath("\\WEB-INF\\upload");
        File fileZip = new File(outFilePath + fileName);
// 文件输出流
        FileOutputStream outStream = new FileOutputStream(fileZip);
// 压缩流
        ZipOutputStream toClient = new ZipOutputStream(outStream);
// toClient.setEncoding("gbk");
        FileUnZip.zipFile(files, toClient);
        toClient.close();
        outStream.close();


        // 处理文件名
        String realname = fileName.substring(fileName.indexOf("_") + 1);
        // 设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename="
                + URLEncoder.encode(realname, "UTF-8"));
        // 读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(outFilePath + "\\" + fileName);
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
    }
}
