package com.crtvu.service;

import com.crtvu.entity.AttachmentEntity;

import java.util.List;

public interface AttachmentService {
    enum Result{
        SUCCESS,
        FAIL,
    }

    public List<AttachmentEntity> pagingAttachment(int page, int year, String name,int type);

    public int page(int year, String name,int type);

    public int delete(int id);
}
