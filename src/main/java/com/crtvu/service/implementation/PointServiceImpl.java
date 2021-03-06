package com.crtvu.service.implementation;

import com.crtvu.dao.PointDAO;
import com.crtvu.entity.PointEntity;
import com.crtvu.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lcf12307 on 2018/1/7.
 */
@Service
public class PointServiceImpl implements PointService {
    @Autowired
    private PointDAO pointDAO;

    @Override
    public int deleteByYear(int year) {
        return pointDAO.deletePointByYear(year);
    }

    @Override
    public int addPoint(String name, int mid, int year, double point, int type) {
        return pointDAO.insertPoint(name, mid, year, point, type);
    }

    @Override
    public List<PointEntity> findPointByName(String name, int type, int year) {
        return pointDAO.selectPoints(name,year,type);
    }

    @Override
    public int updateById(int id, double point) {
        return pointDAO.updatePoint(id,point);
    }

    @Override
    public List<PointEntity> findPoint(int type, int year) {
        return pointDAO.selectPointByYear(year,type);
    }

    @Override
    public List<PointEntity> findPointByMid(int mid, int year) {
        return pointDAO.selectPointByMid(mid,year);
    }

    @Override
    public List<PointEntity> findPointByType(int type, int year, int mid) {
        return pointDAO.selectPointByType(mid,year,type);
    }
}
