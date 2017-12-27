package com.crtvu.dao;


import com.crtvu.entity.AttachmentEntity;
import org.apache.ibatis.annotations.Param;



public interface AttachmentDAO{
    /**
     * 添加
     * @param name
     * @param file
     * @param type
     */
    void insertAttachment(@Param("name") String name,@Param("file") String file,@Param("type") int type);

    int deleteAttachment(@Param("id") int id);

    AttachmentEntity selectAttachment(@param("id") int id);
}