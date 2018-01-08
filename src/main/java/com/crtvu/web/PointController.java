package com.crtvu.web;

import com.crtvu.dto.DeleteJson;
import com.crtvu.entity.AttachmentEntity;
import com.crtvu.entity.PointEntity;
import com.crtvu.service.AttachmentService;
import com.crtvu.service.PointService;
import com.crtvu.utils.POI_Word;
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
        List<AttachmentEntity> attachments = attachmentService.findByYear(year,1);
        for (AttachmentEntity attachment : attachments){
            String dir = attachment.getFile();
            File file = new File(dir);
            if (!file.exists()) {
                break;
            }
            int[] p = {10,10,100,50,30,100,60,40,20,15,50,100,60,20,10,100,40,20,15,1,100,80,70,40,40,20,50,30,50,30};
            String[] files = file.list();
            for(String file1: files){
                if (file1.matches(".*博物馆运行评估申报书\\.doc(x)?$")){
                    System.out.println(file1);
                    float[] res = POI_Word.readData(dir+"\\"+file1);
                    int index=0; PointEntity  point = new PointEntity();
                    if ( pointService.findPointByName(attachment.getName(),0,year) == null){
                        pointService.addPoint(attachment.getName(),0,year,0,0);
                        point = pointService.findPointByName(attachment.getName(),0,year).get(0);
                    }
                    int point1=0,point11=0,point12=0,point13=0,point14=0;
                    if (pointService.findPointByName(attachment.getName(),1,year) != null){
                        pointService.deleteByYear(year);
                    }
                    for (float temp :res){
                        pointService.addPoint(attachment.getName(),point.getId(),year, temp*p[index],index+100);
                        point1+=temp*p[index];
                        switch (index){
                            case 0:
                            case 1: point11+=temp*p[index];



                        }
                        index++;
                    }
                    break;
                }
            }
        }
        return "index";
    }
}
