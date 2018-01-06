package com.crtvu.dao;

import com.crtvu.entity.MuseumEntity;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface MuseumDAO {
    /**
     * 添加博物馆实体
     * @param name
     * @param category
     * @param level
     * @param year
     * @param description
     */
    void insertMuseum(@Param("name") String name, @Param("category") int category, @Param("level") int level, @Param("year") String year, @Param("description") String description);

    /**
     * 修改博物馆实体
     * @param id
     * @param name
     * @param category
     * @param level
     * @param year
     * @param description
     */
    int updateMuseum(@Param("name") String name, @Param("category") int category, @Param("level") int level, @Param("year") String year, @Param("description") String description, @Param("id") int id);

    /**
     *删除博物馆实体
     * @param id
     */
    int deleteMuseum(@Param("id") int id);

    /**
     * 查询所有博物馆
     * @return
     */
    List<MuseumEntity> selectAllMuseum();

    /**
     * 通过博物馆名称查询博物馆
     * @param name
     * @return
     */
    List<MuseumEntity> selectMuseumByName(@Param("name") String name);

    /**
     * 通过关键字查询博物馆并分页
     * @param museumProperty
     * @param index
     * @param count
     * @return
     */
    List<MuseumEntity> selectMuseumByLimit(@Param("museumProperty") String museumProperty, @Param("index") int index, @Param("count") int count);

    /**
     * 通过年份查询博物馆并分页
     * @param museumProperty
     * @param index
     * @param count
     */
    List<MuseumEntity> selectMuseumByYear(@Param("museumProperty") String museumProperty, @Param("index") int index, @Param("count") int count);

    /**
     * 查询博物馆数量
     * @return
     */
    int countAllMuseum(String museumProperty);

    /**
     * 通过ID查询博物馆详细信息
     * @param id
     * @return
     */
    MuseumEntity selectMuseum(@Param("id") int id);






}