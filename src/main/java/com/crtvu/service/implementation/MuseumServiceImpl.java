package com.crtvu.service.implementation;

import com.crtvu.dao.MuseumDAO;
import com.crtvu.entity.MuseumEntity;
import com.crtvu.service.MuseumService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 21441 on 2018/1/3/0016.
 */
@Service
public class MuseumServiceImpl implements MuseumService {
    //日志对象
    private Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MuseumDAO museumDAO;
    private static final int pageNumber = 10;
    private static final int idLength = 1000;
    public List<MuseumEntity> getMuseumList (int page,String museumProperty) {
        if(page<=0||page>getPageCount(museumProperty)) {
            return null;
        }
        return museumDAO.selectMuseumByLimit(museumProperty,(page-1)*pageNumber,pageNumber);
    }
    public int getPageCount(String museumProperty) {
        int count = museumDAO.countAllMuseum(museumProperty);
        int page_count = count == 0 ? 1 : count / pageNumber + (count % pageNumber > 0 ? 1 : 0);
        return page_count;
    }

    public List<MuseumEntity> getMuseumListByYear (String museumProperty) {
        return museumDAO.selectMuseumByYear(museumProperty);
    }


    public MuseumEntity getMuseum(int id) {
        return museumDAO.selectMuseum(id);
    }

    public List<MuseumEntity> getAllMuseumList() {
        return museumDAO.selectAllMuseum();
    }

    public Result insertMuseum(MuseumEntity museum) {

        if(museum.getName().length() < 0){
            return Result.NAME_FAIL;
        }
        /*if (museum.getCategory() != "历史文化与综合类"||museum.getCategory() != "自然、科技与专题类"||museum.getCategory()!="纪念类"){
            return Result.CATEGORY_FAIL;
        }
        if (museum.getLevel() != "省级及省级以上"&&museum.getLevel() != "其他") {
            return Result.LEVEL_FAIL;
        }*/
        if(museum.getYear().length() < 0){
            return Result.YEAR_FAIL;
        }
        try {
            museumDAO.insertMuseum(museum.getName(),museum.getCategory(),museum.getLevel(),museum.getYear(),museum.getDescription());
        }catch (Exception e){
            return Result.ID_FAIL;
        }
        return Result.SUCCESS;
    }

    public Result deleteMuseum(int id){
        return museumDAO.deleteMuseum(id)>0?Result.SUCCESS:Result.ID_FAIL;
    }
    public Result updateMuseum(String name,String category,String level,String year,String description,int id){
        if(name.length() < 0){
            return Result.NAME_FAIL;
        }
       /* if (category != "历史文化与综合类"&&category != "自然、科技与专题类"&category!="纪念类"){
            return Result.CATEGORY_FAIL;
        }
        if (level != "省级及省级以上"&&level != "其他") {
            return Result.LEVEL_FAIL;
        }*/
        if(year.length() < 0){
            return Result.YEAR_FAIL;
        }
        //System.out.println(id+name+category+level+year+description);
        int result;
        try{
            result = museumDAO.updateMuseum(name, category, level, year, description, id);
        }catch(Exception e){
            return Result.ID_FAIL;
        }
        return result > 0 ? Result.SUCCESS : Result.ID_FAIL;
    }




}

