package com.crtvu.service;

import com.crtvu.utils.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jixw on 2018/1/7.
 */
public interface ScoreService {

    Map<Integer,HashMap<String,Integer>> ReadScoreExcel22();

    /**
     * 测试事务
     */
    void testTran();

    R Calculate(String year) throws Exception;

    R CalculateRank(int year);

}
