package com.crtvu.web;


import com.crtvu.auth.Auth;
import com.crtvu.dto.MuseumJson;
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

import static java.lang.System.in;

/**
 * Created by 21441 on 2018/1/3.
 */
@Controller
@RequestMapping("/museum/museum")
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

    @Auth("Museum")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "redirect:/museum/museum/list/1";
    }

    @Auth("Museum")
    @RequestMapping(value = "/list/{page}",method = RequestMethod.GET)
    public String list(@RequestParam(value = "nameKey",defaultValue = "") String museumProperty,
                       Model model,@PathVariable("page") int page){
        //根据博物馆名字查询
        try{
           //museumProperty = new String(museumProperty.getBytes("ISO-8859-1"), "UTF-8");
            //museumProperty = new String(museumProperty.getBytes("ISO-8859-1"), "UTF-8");

            List<MuseumEntity> list= museumService.getMuseumList(page,museumProperty);
            System.out.println("qqqqqqqqqqqqqqq"+museumProperty+list+"55555555555");
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
    @Auth("Museum")
    @RequestMapping(value = "/detail/{museumId}", method = RequestMethod.GET)
    public String detail(@PathVariable("museumId") int museumId, Model model) {
        MuseumEntity museumEntity = museumService.getMuseum(museumId);
        model.addAttribute("museumEntity", museumEntity);
        return "museum/museum/detail";
    }

    //修改博物馆信息
    @Auth("Museum")
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id")int id, Model model){
        try{
            MuseumEntity museum =museumService.getMuseum(id);
            if ( museum == null){
                return "forward:/museum/museum/list/1";
            }
            model.addAttribute("museum",museum);
            return "museum/museum/edit";
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  "museum/index";
    }

    //跳转至修改博物馆页面
    @Auth("Museum")
    @RequestMapping(value = "/updateMuseum" ,method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public MuseumService.Result updateMuseum(@RequestBody MuseumJson museum){
        System.out.println("ymymymymy"+museum);
        return museumService.updateMuseum(museum.getName(),museum.getCategory(),museum.getLevel(),museum.getYear(),museum.getDescription(),museum.getId());
    }

    //根据ID删除博物馆
    @Auth("Museum")
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public MuseumService.Result delete(@RequestBody MuseumJson museum) {
        return museumService.deleteMuseum(museum.getId());

    }

    //添加博物馆
    @Auth("Museum")
    @RequestMapping(value = "/insert" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public MuseumService.Result insert(@RequestBody MuseumJson museum ){

        try{
            MuseumEntity museumEntity = new MuseumEntity(museum.getName(),museum.getCategory(),museum.getLevel(),museum.getYear(),museum.getDescription());
            System.out.println(museumEntity);
            return museumService.insertMuseum(museumEntity);

        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //跳转至增添博物馆页面
    @Auth("Museum")
    @RequestMapping(value = "/insertInfo" ,method = RequestMethod.GET)
    public String insertInfo(){
        return "museum/museum/insert";
    }
}
