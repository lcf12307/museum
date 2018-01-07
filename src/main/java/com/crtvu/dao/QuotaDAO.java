package com.crtvu.dao;
import  com.crtvu.entity.Quota;
import  java.util.*;
import org.apache.ibatis.annotations.Param;

public interface QuotaDAO{
    /**
     * 添加专家
     * @param name
     * @param quotaId
     * @param description
     */
    void insertExpert(@Param("name") String name, @Param("quotaId") String quotaId, @Param("description") String description);

    /**
     * 删除专家
     * @param name
     * @return
     */
    int deleteExpert(@Param("name") String name);

    /**
     * 修改未进行任何行为的专家信息
     * @param name
     * @param quotaId
     * @param description
     * @param id
     * @return
     */
    int updateExpert(@Param("name") String name, @Param("quotaId") String quotaId, @Param("description") String description, @Param("id") int id);

    /**
     * 已经上传打分表等行为的专家只能修改简介
     * @param description
     * @param name
     * @return
     */
    int updateExpertOnDesc(@Param("description") String description, @Param("name") String name);

    /**
     * 为专家指定评审年份
     * @param year
     * @param name
     * @return
     */
    int addExpertYear(@Param("year") String year, @Param("name") String name);

    /**
     * 取消某专家的某年评审
     * @param year
     * @param name
     * @return
     */
    int movExpertYear(@Param("year") String year, @Param("name") String name);


    /**
     * 通过专家名字查找专家信息
     * @param name
     * @return
     */
    Quota selectExpert(@Param("name") String name);

    Quota selectById(@Param("id") int id);
    /**
     * 通过名字模糊查询
     * @param expertProperty
     * @param index
     * @param count
     * @return
     */
    List<Quota> selectByName(@Param("expertProperty") String expertProperty, @Param("index") int index, @Param("count") int count);

    /**
     * 通过年份查找参与该年份评审的专家列表
     * @param expertProperty
     * @param index
     * @param count
     * @return
     */
    List<Quota> selectExpertByLimit(@Param("expertProperty") String expertProperty, @Param("index") int index, @Param("count") int count);

    /**
     * 搜索待参与该年份的专家
     * @param expertProperty
     * @param index
     * @param count
     * @return
     */
    List<Quota> selectExpertByNoLimit(@Param("expertProperty") String expertProperty, @Param("index") int index, @Param("count") int count);


    /**
     * 搜索所有的专家
     * @return
     */
    List<Quota> selectAllExpert();

    /**
     * 查询专家数量
     * @return
     */
    int countAllExpert(String expertProperty);

    /*
    搜索某年份所有的专家
     */
    List<Quota> selectExpertByYear(String expertProperty);

}