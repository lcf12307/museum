package com.crtvu.web;

import com.crtvu.dto.DeleteJson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by lcf12307 on 2018/1/2.
 */
@Controller
@RequestMapping("/point")
public class PointController {

    @RequestMapping(value = "/index")
    public String index(){
        return "/point/index";
    }

    @RequestMapping(value = "/count",produces = {"application/json;charset=UTF-8"})
    public DeleteJson count(@RequestParam(value = "year",defaultValue = "") int year){
        DeleteJson deleteJson = new DeleteJson();
        return  deleteJson;
    }
    @RequestMapping(value = "/list")
    public String list(@RequestParam(value = "type") int type){
        return "";
    }

    @RequestMapping(value = "/statics")
    public String statics(@RequestParam(value = "statics") int type){
        return "";
    }
}
