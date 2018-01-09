package com.crtvu.web;

import com.crtvu.dto.DeleteJson;
import com.crtvu.dto.FinalPoint;
import com.crtvu.entity.AttachmentEntity;
import com.crtvu.entity.PointEntity;
import com.crtvu.service.AttachmentService;
import com.crtvu.service.PointService;
import com.crtvu.utils.POI_Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
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
    public String quantitative(@RequestParam(value = "year") int year,Model model,
                    HttpServletRequest request){
        List<AttachmentEntity> attachments = attachmentService.findByYear(year,1);
        List<FinalPoint> finalpoints = new ArrayList<>();
        FinalPoint finalPoint;
        float[] maxRes = new float[100];
        float[] sumRes = new float[100];
        float[][] tempRes = new float[300][100];
        int rowindex=0;
        double point1=0,point11=0,point12=0,point13=0,point14=0,pointtemp=0;
        for (AttachmentEntity attachment : attachments){
            String dir = attachment.getFile();
            File file = new File(dir);
            if (!file.exists()) {
                break;
            }
            int[] p = {10,10,100,50,30,100,60,40,20,15,50,100,60,20,10,100,40,20,15,1,100,80,70,40,40,20,50,30,50,30};
            String[] files = file.list();
            finalPoint = new FinalPoint();
            finalPoint.init();
            for(String file1: files){
                if (file1.matches(".*博物馆运行评估申报书\\.doc(x)?$")){
                    System.out.println(file1);
                    float[] res = POI_Word.readData(dir+"/"+file1);
                    int index=0,index1=0; PointEntity  point = new PointEntity();
                    if ( pointService.findPointByName(attachment.getName(),0,year) == null){
                        pointService.addPoint(attachment.getName(),0,year,0,0);
                        point = pointService.findPointByName(attachment.getName(),0,year).get(0);
                    }
                    if (pointService.findPointByName(attachment.getName(),1,year) != null){
                        pointService.deleteByYear(year);
                    }
                    index1=0;index=0;
                    for (float temp :res){

                        if(index>29){
                            break;
                        }
                        switch (index){
                            case 0:pointtemp=temp*p[index];finalPoint.setPoint101(pointtemp);
                                if (maxRes[index1]<pointtemp){
                                    maxRes[index1] = (float) pointtemp;
                                }
                                sumRes[index1] += pointtemp;index1++; break;
                            case 1: pointtemp=temp*p[index];finalPoint.setPoint102(pointtemp);
                                if (maxRes[index1]<pointtemp){
                                    maxRes[index1] = (float) pointtemp;
                                }
                                sumRes[index1] += pointtemp;index1++; break;
                            case 2: pointtemp = temp*p[index];break;
                            case 3:pointtemp += temp*p[index];break;
                            case 4:pointtemp += temp*p[index];finalPoint.setPoint103(pointtemp);
                                if (maxRes[index1]<pointtemp){
                                    maxRes[index1] = (float) pointtemp;
                                }
                                sumRes[index1] += pointtemp;index1++;break;
                            case 5:pointtemp=temp*p[index];break;
                            case 6:pointtemp+=temp*p[index];break;
                            case 7:pointtemp+=temp*p[index];break;
                            case 8:pointtemp+=temp*p[index];break;
                            case 9:pointtemp+=temp*p[index];break;
                            case 10:pointtemp+=temp*p[index];finalPoint.setPoint104(pointtemp);
                                if (maxRes[index1]<pointtemp){
                                    maxRes[index1] = (float) pointtemp;
                                }
                                sumRes[index1] += pointtemp;index1++;break;
                            case 11:pointtemp=temp*p[index];break;
                            case 12:pointtemp+=temp*p[index];break;
                            case 13:pointtemp+=temp*p[index];break;
                            case 14:pointtemp+=temp*p[index];finalPoint.setPoint105(pointtemp);
                                if (maxRes[index1]<pointtemp){
                                    maxRes[index1] = (float) pointtemp;
                                }
                                sumRes[index1] += pointtemp;index1++;break;
                            case 15:pointtemp=temp*p[index];break;
                            case 16:pointtemp+=temp*p[index];break;
                            case 17:pointtemp+=temp*p[index];break;
                            case 18:pointtemp+=temp*p[index];break;
                            case 19:pointtemp+=temp*p[index];finalPoint.setPoint106(pointtemp);
                                if (maxRes[index1]<pointtemp){
                                    maxRes[index1] = (float) pointtemp;
                                }
                                sumRes[index1] += pointtemp;index1++;break;
                            case 20:pointtemp=temp*p[index];break;
                            case 21:pointtemp+=temp*p[index];break;
                            case 22:pointtemp+=temp*p[index];break;
                            case 23:pointtemp+=temp*p[index];break;
                            case 24:pointtemp+=temp*p[index];break;
                            case 25:pointtemp+=temp*p[index];finalPoint.setPoint107(pointtemp);
                                if (maxRes[index1]<pointtemp){
                                    maxRes[index1] = (float) pointtemp;
                                }
                                sumRes[index1] += pointtemp;index1++;break;
                            case 26:pointtemp=temp*p[index];break;
                            case 27:pointtemp+=temp*p[index];finalPoint.setPoint108(pointtemp);
                                if (maxRes[index1]<pointtemp){
                                    maxRes[index1] = (float) pointtemp;
                                }
                                sumRes[index1] += pointtemp;index1++;break;
                            case 28:pointtemp=temp*p[index];break;
                            case 29:pointtemp+=temp*p[index];finalPoint.setPoint109(pointtemp);
                                if (maxRes[index1]<pointtemp){
                                    maxRes[index1] = (float) pointtemp;
                                }
                                sumRes[index1] += pointtemp;index1++;
                        }
                        index++;
                    }
                    finalpoints.add(finalPoint);
                    break;
                }
            }
        }
        for (FinalPoint newpoint : finalpoints){
            pointtemp = ((maxRes[0] - (sumRes[0]/finalpoints.size())) ==0)?0:(((newpoint.getPoint101()-(sumRes[0]/finalpoints.size()))*0.4)/(maxRes[0] - (sumRes[0]/finalpoints.size())));
            newpoint.setPoint101(10*(pointtemp+0.6));
            pointtemp = ((maxRes[1] - (sumRes[1]/finalpoints.size())) ==0)?0:(((newpoint.getPoint102()-(sumRes[1]/finalpoints.size()))*0.4)/(maxRes[1] - (sumRes[1]/finalpoints.size())));
            newpoint.setPoint102(5*(pointtemp+0.6));
            pointtemp = ((maxRes[2] - (sumRes[2]/finalpoints.size())) ==0)?0:(((newpoint.getPoint103()-(sumRes[2]/finalpoints.size()))*0.4)/(maxRes[2] - (sumRes[2]/finalpoints.size())));
            newpoint.setPoint103(8*(pointtemp+0.6));
            pointtemp = ((maxRes[3] - (sumRes[3]/finalpoints.size())) ==0)?0:(((newpoint.getPoint104()-(sumRes[3]/finalpoints.size()))*0.4)/(maxRes[3] - (sumRes[3]/finalpoints.size())));
            newpoint.setPoint104(12*(pointtemp+0.6));
            pointtemp = ((maxRes[4] - (sumRes[4]/finalpoints.size())) ==0)?0:(((newpoint.getPoint105()-(sumRes[4]/finalpoints.size()))*0.4)/(maxRes[4] - (sumRes[4]/finalpoints.size())));
            newpoint.setPoint105(5*(pointtemp+0.6));
            pointtemp = ((maxRes[5] - (sumRes[5]/finalpoints.size())) ==0)?0:(((newpoint.getPoint106()-(sumRes[5]/finalpoints.size()))*0.4)/(maxRes[5] - (sumRes[5]/finalpoints.size())));
            newpoint.setPoint106(25*(pointtemp+0.6));
            pointtemp = ((maxRes[6] - (sumRes[6]/finalpoints.size())) ==0)?0:(((newpoint.getPoint107()-(sumRes[6]/finalpoints.size()))*0.4)/(maxRes[6] - (sumRes[6]/finalpoints.size())));
            newpoint.setPoint107(20*(pointtemp+0.6));
            pointtemp = ((maxRes[7] - (sumRes[7]/finalpoints.size())) ==0)?0:(((newpoint.getPoint108()-(sumRes[7]/finalpoints.size()))*0.4)/(maxRes[7] - (sumRes[7]/finalpoints.size())));
            newpoint.setPoint108(5*(pointtemp+0.6));
            pointtemp = ((maxRes[8] - (sumRes[8]/finalpoints.size())) ==0)?0:(((newpoint.getPoint109()-(sumRes[8]/finalpoints.size()))*0.4)/(maxRes[8] - (sumRes[8]/finalpoints.size())));
            newpoint.setPoint109(10 *(pointtemp+0.6));
            newpoint.setPoint11(newpoint.getPoint101()+newpoint.getPoint102());
            newpoint.setPoint12(newpoint.getPoint103()+newpoint.getPoint104()+newpoint.getPoint105());
            newpoint.setPoint13(newpoint.getPoint106()+newpoint.getPoint107());
            newpoint.setPoint14(newpoint.getPoint108()+newpoint.getPoint109());
            newpoint.setPoint1(newpoint.getPoint11()+newpoint.getPoint12()+newpoint.getPoint13()+newpoint.getPoint14());
        }

        model.addAttribute("quantative",finalpoints);
        return "quantative";
    }
}
