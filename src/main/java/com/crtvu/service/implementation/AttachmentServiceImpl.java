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
}
