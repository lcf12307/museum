package com.crtvu.web;


import com.crtvu.entity.MuseumEntity;
import com.crtvu.service.MuseumService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by 21441 on 2018/1/3.
 */
@Controller
@RequestMapping("admin/museum")
public class MuseumController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MuseumService museumService;

    /*@RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        //获取列表页
        List<MuseumEntity> list = museumService.getMuseumList(1,"");
        model.addAttribute("list",list);
        return "/museum/list";//WEB-INF/jsp/
    }*/
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "redirect:/museum/museum/list/1";
    }

    @RequestMapping(value = "/list/{page}",method = RequestMethod.GET)
    public String list(@RequestParam(value = "searchKey",defaultValue = "") String museumProperty,
                       Model model,@PathVariable("page") int page){
        //根据博物馆名字查询
        try{
            museumProperty = new String(museumProperty.getBytes("ISO-8859-1"), "UTF-8");
            List<MuseumEntity> list= museumService.getMuseumList(page,museumProperty);
            int pages = museumService.getPageCount(museumProperty);
            model.addAttribute("pages",pages);
            model.addAttribute("list",list);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "museum/museum/list";
    }

    //详细信息
    @RequestMapping(value = "/detail/{museumId}", method = RequestMethod.GET)
    public String detail(@PathVariable("museumId") int museumId, Model model) {
        MuseumEntity museumEntity = museumService.getMuseum(museumId);
        model.addAttribute("museumEntity", museumEntity);
        return "museum/museum/detail";
    }

    //修改博物馆信息
    @RequestMapping(value = "/updateInformation", method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public MuseumService.Result updateInformation(@RequestBody MuseumEntity museumEntity) {
        return museumService.updateMuseum(museumEntity.getName(),museumEntity.getCategory(),museumEntity.getLevel(),
        museumEntity.getYear(),museumEntity.getDescription(),museumEntity.getId());
    }

    //根据ID删除博物馆
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public MuseumService.Result delete(@RequestBody MuseumEntity museumEntity) {
        return museumService.deleteMuseum(museumEntity.getId());
    }

    //添加博物馆
    @RequestMapping(value = "/insert" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public MuseumService.Result insertMuseum(@RequestBody MuseumEntity museumEntity) {

        try{
            MuseumEntity museum = new MuseumEntity(museumEntity.getName(),museumEntity.getCategory(),
                    museumEntity.getLevel(),museumEntity.getYear(),museumEntity.getDescription());
            return museumService.insertMuseum(museum);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //跳转至增添博物馆页面
    @RequestMapping(value = "/insertPage" ,method = RequestMethod.GET)
    public String insertInfo(){
        return "museum/museum/insert";
    }
}
