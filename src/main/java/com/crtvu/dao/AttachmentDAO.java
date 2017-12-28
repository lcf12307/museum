package com.crtvu.dao;


import com.crtvu.entity.AttachmentEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AttachmentDAO{
    /**
     * 添加
     * @param name
     * @param file
     * @param type
     */
    void insertAttachment(@Param("name") String name,@Param("file") String file,@Param("type") int type,@Param("year") int year);

    int deleteAttachment(@Param("id") int id);

    int deleteAttachmentbyName(@Param("name") String name);

    int deleteAttachmentbyNameAndYear(@Param("name") String name,@Param("year") int year);

    AttachmentEntity selectAttachment(@Param("id") int id);

    int updateAttachFile(@Param("id") int id,@Param("file") String file);
    /**
     * 分页
     * @return
     */
    List<AttachmentEntity> selectAttachementByLimit(@Param("name") String name,@Param("year") int year,@Param("start") int page, @Param("perpage") int perpage);

    List<AttachmentEntity> selectAttachementByName(@Param("name") String name,@Param("page") int page, @Param("perpage") int perpage);

    int countAttachementByLimit(@Param("name") String name,@Param("year") int year);

    int countAttachementByName(@Param("name") String name);
}