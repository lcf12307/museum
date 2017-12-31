package com.crtvu.service.implementation;

import com.crtvu.dao.AttachmentDAO;
import com.crtvu.entity.AttachmentEntity;
import com.crtvu.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentDAO attachmentDAO;

    @Override
    public List<AttachmentEntity> pagingAttachment(int page, int year, String name) {
        name = "%"+name+"%";
        List<AttachmentEntity> attachments;
        if (year == 0){
            attachments = attachmentDAO.selectAttachementByName(name,(page-1)*10,10);
        }else{
            attachments = attachmentDAO.selectAttachementByLimit(name,year,(page-1)*10,10);
        }

        return attachments;
    }

    @Override
    public int delete(int id) {
        return attachmentDAO.deleteAttachment(id);
    }

    @Override
    public int page(int year, String name) {
        name = "%"+name+"%";
        int page;
        if ( year == 0 ){
            page = attachmentDAO.countAttachementByName(name);
        } else {
            page = attachmentDAO.countAttachementByLimit(name, year);
        }
        return page;
    }
}
