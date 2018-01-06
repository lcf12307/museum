package com.crtvu.service.implementation;

import com.crtvu.dao.AttachmentDAO;
import com.crtvu.entity.AttachmentEntity;
import com.crtvu.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentDAO attachmentDAO;

    @Override
    public List<AttachmentEntity> pagingAttachment(int page, int year, String name,int type) {
        name = "%"+name+"%";
        List<AttachmentEntity> attachments;
        if (year == 0){
            attachments = attachmentDAO.selectAttachementByName(name,(page-1)*10,10,type);
        }else{
            attachments = attachmentDAO.selectAttachementByLimit(name,year,(page-1)*10,10,type);
        }

        return attachments;
    }

    @Override
    public int delete(int id) {
        return attachmentDAO.deleteAttachment(id);
    }

    @Override
    public void inputstreamtofile(InputStream ins,File file) throws Exception{
        OutputStream os = new FileOutputStream(file);
        int bytesRead = 0;
        byte[] buffer = new byte[8192];
        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
    }

    @Override
    public int addAttachment(int year, String name, String dir, int type) {
        String addtime = Long.toString(System.currentTimeMillis());
        return attachmentDAO.insertAttachment(name,dir,type,year,addtime);
    }

    @Override
    public AttachmentEntity findById(int id) {
        return  attachmentDAO.selectAttachment(id);
    }

    @Override
    public int findAttachment(int year,String name,int type) {
        return attachmentDAO.countAttachement(name,year,type);
    }

    @Override
    public int page(int year, String name,int type) {
        name = "%"+name+"%";
        int page;
        if ( year == 0 ){
            page = attachmentDAO.countAttachementByName(name,type);
        } else {
            page = attachmentDAO.countAttachementByLimit(name, year,type);
        }
        return page;
    }
    @Override
    public int deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return 1;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return 0;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return 0;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir
     *            要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    @Override
    public int deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return 0;
        }
        int flag = 1;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (flag == 0)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]
                        .getAbsolutePath());
                if (flag==0)
                    break;
            }
        }
        if (flag == 0) {
            System.out.println("删除目录失败！");
            return 0;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return 1;
        } else {
            return 0;
        }
    }
}
