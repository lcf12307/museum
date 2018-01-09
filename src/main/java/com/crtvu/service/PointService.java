package com.crtvu.service;

import com.crtvu.entity.PointEntity;

import java.util.List;

/**
 * Created by lcf12307 on 2018/1/7.
 */
public interface PointService {
   public int addPoint(String name, int mid, int year, double point, int type);
   public List<PointEntity> findPointByMid(int mid,int year);
   public List<PointEntity> findPointByType(int type,int year,int mid);
   public List<PointEntity> findPoint(int type,int year);
   public List<PointEntity> findPointByName(String name,int type,int year);
   public int deleteByYear(int year);
}
