package com.crtvu.service;

import com.crtvu.entity.Quota;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface QuotaService{
    enum Result{
        SUCCESS,
        ID_FAIL,
        NAME_FAIL,//名字重复
        QUOTA_ID_FAIL,//指标错误
        DESCRIPTION_FAIL,//描述错误
        YEAR_FAIL,   //参与年份错误
        UNKNOWN_FAIL,
        POINT_FAIL
    }


    /**
     * 对已参与评审专家信息进行分页，并可以翻到第page
     * @param page
     * @return
     */
    List<Quota> getExpertList(int page, String expertProperty);

    /**
     * 待评审的专家列表
     * @param page
     * @param expertProperty
     * @return
     */
    List<Quota> getExpertListWait(int page, String expertProperty);

    /**
     * 找出模糊查询总页数
     * @return
     */
    int getPageCount(String expertProperty);

    /**
     * 找出不属于模糊查询的页数
     * @param expertProperty
     * @return
     */
    int getPageCountNot(String expertProperty);
    /**
     * 通过专家名查找专家信息
     * @param name
     * @return
     */
   Quota getExpert(String name);

    /**
     * 通过ID查找专家
     * @param id
     * @return
     */
   Quota getExpertById(int id);
    /**
     * 名字模糊查询的专家列表
     * @param page
     * @param expertProperty
     * @return
     */

   List<Quota> getExpertByName(int page, String expertProperty);
    /**
     * 得到所有专家列表
     * @return
     */
    List<Quota> getAllExpertList();

    /**
     * 添加专家
     * @param quota
     */
    Result insertExpert(Quota quota);

    /**
     * 通过专家名字删除专家
     * @param name
     */
    Result deleteExpert(String name);

    /**
     * 修改专家三项信息
     * @param name
     * @param quotaId
     * @param description
     * @param id
     * @return
     */
    Result updateExpert(String name, String quotaId, String description, int id);

    /**
     * 修改专家简介
     * @param description
     * @param name
     * @return
     */
    Result  updateExpertOnDesc(String description, String name);

    /**
     * 为某年份指定评审专家
     * @param year
     * @param name
     * @return
     */
    Result addExpertYear(String year, String name);


    /**
     * 移除某年份的专家
     * @param year
     * @param name
     * @return
     */
    Result movExpertYear(String year, String name);

    /**
     * 搜索出某年份未上传打分表的专家
     * @param year
     * @return
     */
    List<String> selectByYearPoint(String year);

}