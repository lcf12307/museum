package com.crtvu.web;

import com.crtvu.auth.Auth;
import com.crtvu.dto.DeleteJson;
import com.crtvu.service.ScoreService;
import com.crtvu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lcf12307 on 2018/1/2.
 */
@Controller
@RequestMapping("/point")
public class PointController {

    @Autowired
    ScoreService scoreService;

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

    @RequestMapping(value = "/quantitative")
    public String quantitative(@RequestParam(value = "year") int year){
        return "";
    }

    @RequestMapping(value = "/dingxing")
    @ResponseBody
    public R dingxing(@RequestParam(value = "year") int year){
        if(year<=0)
            return R.error("参数错误");
        R r = R.error(-99,"未知错误");
        try {
            r=scoreService.Calculate(String.valueOf(year));
        } catch (Exception e) {
            if(canParseInt(e.getMessage())){
                if(Integer.parseInt(e.getMessage())<0){
                    r=R.error(Integer.parseInt(e.getMessage()),e.getCause().getMessage());
                }
            }else{
                r=R.error("未知错误！");
            }
        }
        return r;
    }

    public static boolean canParseInt(String  str){
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
