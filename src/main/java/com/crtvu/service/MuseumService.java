package com.crtvu.service;

import java.util.*;

import com.crtvu.entity.MuseumEntity;
import org.springframework.web.multipart.MultipartFile;

public interface MuseumService {


    enum Result{
        SUCCESS,//成功
        ID_FAIL,//博物馆ID
        NAME_FAIL,//博物馆名称
        CATEGORY_FAIL,//类别
        LEVEL_FAIL,//等级
        YEAR_FAIL,//评审年份
    }
    /**
     * 对博物馆信息进行分页，并可以翻到第page
     * @param page
     * @return
     */
    List<MuseumEntity> getMuseumList(int page, String museumProperty);

    /**
     * 按年份搜索博物馆并翻页
     * @param museumProperty
     * @return
     */
    List<MuseumEntity> getMuseumListByYear(String museumProperty);

    /**
     * 找出总页数
     * @return
     */
    int getPageCount(String museumProperty);

    /**
     * 通过ID查找某个博物馆
     * @param id
     * @return
     */
    MuseumEntity getMuseum(int id);


    /**
     * 得到所有博物馆列表
     * @return
     */
    List<MuseumEntity> getAllMuseumList();

    /**
     * 添加博物馆
     * @param museum
     */
    Result insertMuseum(MuseumEntity museum);

    /**
     * 通过id删除博物馆
     * @param id
     */
    Result deleteMuseum(int id);

    /**
     * 修改博物馆
     * @param name
     * @param category
     * @param level
     * @param year
     * @param description
     * @param id
     */
    Result updateMuseum(String name, String category, String level, String year, String description, int id);


}