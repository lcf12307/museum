package com.crtvu.web;

import com.crtvu.dto.DeleteJson;
import com.crtvu.entity.AttachmentEntity;
import com.crtvu.service.AttachmentService;
import com.crtvu.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * Created by lcf12307 on 2018/1/2.
 */
@Controller
@RequestMapping("/point")
public class PointController {
    @Autowired
    private PointService pointService;
    @Autowired
    private AttachmentService attachmentService;
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
    public String quantitative(@RequestParam(value = "year") int year,
                    HttpServletRequest request){
        List<AttachmentEntity> attachments = attachmentService.findByYear(year,2);
        for (AttachmentEntity attachment : attachments){
            String dir = attachment.getFile();
            File file = new File(dir);
            if (!file.exists()) {
                break;
            }
            String[] files = file.list();
            for(String file1: files){
                if (file1.matches(".*博物馆运行评估申报书\\.doc$")){


                    break;
                };
            }
        }
        return "";
    }
}
