package com.crtvu.dao;


import com.crtvu.entity.PointEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PointDAO{

    void insertPoint(@Param("name") String name,@Param("mid") int mid,@Param("year") int year,@Param("point") int point,@Param("type") int type);

    int deletePoint(@Param("id") int id);

    int deletePointByName(@Param("name") String name);

    int deletePointByNameAndYear(@Param("name") String name ,@Param("year") int year);

    int updatePoint(@Param("id") int id,@Param("point") int point);

    PointEntity selectPoint(@Param("id") int id);

    List<PointEntity> selectPointByName(@Param("name") String name,@Param("mid") int mid,@Param("year") int year);

    List<PointEntity> selectPointByMid(@Param("mid") int mid,@Param("year") int year);

    List<PointEntity> selectPointByType(@Param("mid") int mid,@Param("year") int year,@Param("type") String type);

    int hasPointed(@Param("name") String name);

}