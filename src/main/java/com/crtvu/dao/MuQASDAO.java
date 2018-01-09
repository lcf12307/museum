package com.crtvu.dao;

import com.crtvu.dto.StrValue;
import com.crtvu.entity.MuseumEntity;
import com.crtvu.entity.PointEntity;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jixw on 2018/1/8.
 */
public interface MuQASDAO {

    /**
     * 可用于判断某一年的评审专家是否为大于3的奇数
     * @param year
     * @return
     */
    List<StrValue> getQuotaIdCount(String year);

    /**
     * 得到参与某一年评审的所有博物馆的信息
     * @param year
     * @return
     */
    List<MuseumEntity> getAllMuseums(String year);

    /**
     * 删除某一年某个类型的分数数据
     * typeFrom=<type<=typeTo
     * @param year
     * @param typeFrom
     * @param typeTo
     * @return
     */
    int deletePoint(@Param("year") int year,@Param("typeFrom")  int typeFrom,@Param("typeTo")  int typeTo);

    /**
     * 计算某年的定性总分
     * @param year
     * @return
     */
    int staticsTotal(int year);

    List<PointEntity> getDingXingScore(@Param("year") int year,@Param("museumName") String museumName,@Param("orderString") String orderString);

    List<MuseumEntity> getAllMuseum(int year);

    List<PointEntity>  getOneMuseumScore(@Param("id") int museum_id,@Param("year") int year);

    PointEntity getTotalStatic(@Param("name") String name,@Param("type") int type,@Param("year") int year);

    int CalculateRank(int year);

    List<PointEntity> getRank(int year);

    List<PointEntity> getDingXingRank(int year);

    PointEntity findPointByid(int id);
}
