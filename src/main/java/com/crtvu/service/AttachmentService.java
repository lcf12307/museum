package com.crtvu.service;

import com.crtvu.entity.AttachmentEntity;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public interface AttachmentService {
    enum Result{
        SUCCESS,
        FAIL,
    }

    public List<AttachmentEntity> pagingAttachment(int page, int year, String name,int type);

    public int page(int year, String name,int type);

    public int delete(int id);

    public void inputstreamtofile(InputStream ins, File file) throws Exception;

    public int findAttachment(int year,String name,int type);

    public int addAttachment(int year,String name,String dir,int type);

    public int deleteFile(String fileName);

    public int deleteDirectory(String dir);
}
