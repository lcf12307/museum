package com.crtvu.dao;

import com.crtvu.dto.StrValue;
import com.crtvu.entity.MuseumEntity;
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
}
