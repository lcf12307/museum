package com.crtvu.web;

import com.crtvu.auth.Auth;
import com.crtvu.dto.DeleteQuoJson;
import com.crtvu.dto.ErrorQuoJson;
import com.crtvu.dto.QuotaJson;
import com.crtvu.dto.QuotaResult;
import com.crtvu.dto.WDWUtil;

import com.crtvu.entity.Quota;
import com.crtvu.service.QuotaService;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

import static java.lang.Integer.parseInt;

@Controller
@RequestMapping("/museum/quota")
public class QuotaController {

   /* Calendar cal = Calendar.getInstance();
    private String year6 = "2018";
    private int year5 = cal.get(Calendar.YEAR);
    year6 = Integer.toString(year5);*/

    @Autowired
    private QuotaService QuotaService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(){
        return "redirect:/museum/quota/list/1";
    }

    //根据专家名字查询
    @Auth("Quota")
    @RequestMapping(value = "/list/{page}",method = RequestMethod.GET)
    public String list(@RequestParam(value = "nameKey",defaultValue = "") String expertProperty,
                       Model model,@PathVariable("page") int page){
        try{
            expertProperty = new String(expertProperty.getBytes("ISO-8859-1"), "UTF-8");
            List<Quota> list= QuotaService.getExpertByName(page,expertProperty);

            int pages = QuotaService.getPageCount(expertProperty);
            model.addAttribute("pages",pages);
            model.addAttribute("list",list);
            model.addAttribute("page",page);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "museum/quota/list";
    }

    //根据评审年份查询
    @Auth("Quota")
    @RequestMapping(value = "/appoint/{page}",method = RequestMethod.GET)
    public String appoint(@RequestParam(value = "yearKey",defaultValue = "2018" ) String expertProperty,
                          Model model,@PathVariable("page") int page){
        try{
            expertProperty = new String(expertProperty.getBytes("ISO-8859-1"), "UTF-8");

            List<Quota> list= QuotaService.getExpertList(page,expertProperty);

            int pages = QuotaService.getPageCount(expertProperty);
            System.out.println("aaaaaaaaa"+pages+"bbbbbbbbbbbbbbbb");
            model.addAttribute("pages",pages);
            model.addAttribute("list",list);
            model.addAttribute("yearKey",expertProperty);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "museum/quota/appoint";
    }

    @Auth("Quota")
    @RequestMapping(value = "/appoint2/{page}",method = RequestMethod.GET)
    public String appoint2(@RequestParam(value = "yearKey",defaultValue = "2018") String expertProperty,
                          Model model,@PathVariable("page") int page){
        try{
            expertProperty = new String(expertProperty.getBytes("ISO-8859-1"), "UTF-8");


            List<Quota> list= QuotaService.getExpertListWait(page,expertProperty);
            System.out.println("=====================");
            System.out.println(list);
            System.out.println("=====================");
            int pages = QuotaService.getPageCountNot(expertProperty);
            System.out.println("JJJJJJ"+pages+"GGGGGGGG");
            model.addAttribute("pages",pages);
            model.addAttribute("list",list);
            model.addAttribute("yearKey",expertProperty);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "museum/quota/appoint2";
    }

    //修改专家信息
    @Auth("Quota")
    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id")int id,@RequestParam("page") int page, Model model){
            try{
            Quota quota =QuotaService.getExpertById(id);
            if (quota == null){
                return "forward:/museum/quota/list/1";
            }
            model.addAttribute("quota",quota);
            model.addAttribute("page",page);
            return "museum/quota/edit";
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  "museum/index";
    }

    //专家详情
    @Auth("Quota")
    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public String detail(@PathVariable("id")int id,@RequestParam("page") int page, Model model){


        try{
            Quota quota =QuotaService.getExpertById(id);
            if (quota == null){
                return "forward:/museum/quota/list/1";
            }
            model.addAttribute("quota",quota);
            model.addAttribute("page",page);
            return "museum/quota/detail";
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return  "museum/index";
    }
    //删除专家信息
    @Auth("Quota")
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public QuotaService.Result delete(@RequestBody DeleteQuoJson DeleteQuoJson){
        return QuotaService.deleteExpert(DeleteQuoJson.getName());
    }

    @Auth("Quota")
    @RequestMapping(value = "/insertInfo" ,method = RequestMethod.GET)
    public String insertInfo(){
        return "museum/quota/insert";
    }

   //添加专家信息
    @Auth("Quota")
    @RequestMapping(value = "/insert" ,method = RequestMethod.POST ,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public QuotaService.Result insert(@RequestBody QuotaJson quota ){

         try{
             Quota quotaEntity = new Quota(quota.getName(),quota.getQuotaId(),quota.getDescription());
             return QuotaService.insertExpert(quotaEntity);

         }catch (Exception e){
             e.printStackTrace();
         }
        return null;
    }

    //更新专家信息
    @Auth("Quota")
    @RequestMapping(value = "/updateExpert" ,method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public QuotaService.Result updateExpert(@RequestBody QuotaJson quota){
            return QuotaService.updateExpert(quota.getName(),quota.getQuotaId(),quota.getDescription(),quota.getId());
    }

    //取消专家评审
    @Auth("Quota")
    @RequestMapping(value = "/cancel",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public QuotaService.Result cancel(@RequestBody QuotaJson quota){
        try{
            System.out.println("88888888888888"+quota.getYear()+"88888888888888");
            System.out.println("==========="+quota.getName()+"===============");
            return QuotaService.movExpertYear(quota.getYear(),quota.getName());
        }
        catch(Exception e){
            e.printStackTrace();
        }
         return com.crtvu.service.QuotaService.Result.YEAR_FAIL;//写着试试
    }

    //添加专家评审
    @Auth("Quota")
    @RequestMapping(value = "/add",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public QuotaService.Result add(@RequestBody QuotaJson quota){
        try{

            System.out.println("ABC8598A"+quota.getYear()+"ADADADA");
            System.out.println("==========="+quota.getName()+"===============");
            return QuotaService.addExpertYear(quota.getYear(),quota.getName());
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return com.crtvu.service.QuotaService.Result.YEAR_FAIL;//写着试试
    }

    @Auth("Quota")
    @RequestMapping(value = "/download/{yearKey}",method = RequestMethod.GET)
    public String download(@PathVariable("yearKey") String yearKey , Model model){
        String filename = QuotaService.download(yearKey);
        System.out.println(filename);
        String path = "/download/"+filename;
        model.addAttribute("jump",path);
        return  "museum/quota/jump";
    }
}

