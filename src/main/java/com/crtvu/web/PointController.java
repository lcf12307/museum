package com.crtvu.web;

import com.crtvu.auth.Auth;
import com.crtvu.dto.DeleteJson;
import com.crtvu.service.ScoreService;
import com.crtvu.utils.R;
import com.crtvu.dto.FinalPoint;
import com.crtvu.entity.AttachmentEntity;
import com.crtvu.entity.PointEntity;
import com.crtvu.service.AttachmentService;
import com.crtvu.service.PointService;
import com.crtvu.utils.POI_Word;
import com.crtvu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping(value = "quantitativedetail")
    public  String quantitative1(@RequestParam(value = "id" ,defaultValue = "0" ) int id,@RequestParam(value = "year",defaultValue = "2018") int year,
                                 Model model){
        List<PointEntity> points = pointService.findPointByType(1,year,id);
        FinalPoint fp;
        PointEntity point = points.get(0);
        int mid = point.getMid();
        fp = new FinalPoint();
        fp.setName(point.getName());
        fp.setYear(point.getYear());
        fp.setPoint1(pointService.findPointByType(1,year,mid).get(0).getPoint());
        fp.setPoint11(pointService.findPointByType(11,year,mid).get(0).getPoint());
        fp.setPoint12(pointService.findPointByType(12,year,mid).get(0).getPoint());
        fp.setPoint13(pointService.findPointByType(13,year,mid).get(0).getPoint());
        fp.setPoint14(pointService.findPointByType(14,year,mid).get(0).getPoint());


        fp.setPoint101(pointService.findPointByType(101,year,mid).get(0).getPoint());
        fp.setPoint102(pointService.findPointByType(102,year,mid).get(0).getPoint());
        fp.setPoint103(pointService.findPointByType(103,year,mid).get(0).getPoint());
        fp.setPoint104(pointService.findPointByType(104,year,mid).get(0).getPoint());
        fp.setPoint105(pointService.findPointByType(105,year,mid).get(0).getPoint());
        fp.setPoint106(pointService.findPointByType(106,year,mid).get(0).getPoint());
        fp.setPoint107(pointService.findPointByType(107,year,mid).get(0).getPoint());
        fp.setPoint108(pointService.findPointByType(108,year,mid).get(0).getPoint());
        fp.setPoint109(pointService.findPointByType(109,year,mid).get(0).getPoint());

        model.addAttribute("quantative",fp);
        model.addAttribute("year",year);
        return "/point/quantativeDetail";
    }
    @RequestMapping(value = "quantitativelistdetail")
    public  String quantitative1232(@RequestParam(value = "id" ,defaultValue = "0" ) int id,@RequestParam(value = "year",defaultValue = "2018") int year,
                                 Model model){
        List<PointEntity> points = pointService.findPointByType(1,year,id);
        FinalPoint fp;
        PointEntity point = points.get(0);
        int mid = point.getMid();
        fp = new FinalPoint();
        fp.setName(point.getName());
        fp.setYear(point.getYear());
        fp.setPoint1(pointService.findPointByType(3,year,mid).get(0).getPoint());
        fp.setPoint11(pointService.findPointByType(31,year,mid).get(0).getPoint());
        fp.setPoint12(pointService.findPointByType(32,year,mid).get(0).getPoint());
        fp.setPoint13(pointService.findPointByType(33,year,mid).get(0).getPoint());
        fp.setPoint14(pointService.findPointByType(34,year,mid).get(0).getPoint());


        fp.setPoint101(pointService.findPointByType(301,year,mid).get(0).getPoint());
        fp.setPoint102(pointService.findPointByType(302,year,mid).get(0).getPoint());
        fp.setPoint103(pointService.findPointByType(303,year,mid).get(0).getPoint());
        fp.setPoint104(pointService.findPointByType(304,year,mid).get(0).getPoint());
        fp.setPoint105(pointService.findPointByType(305,year,mid).get(0).getPoint());
        fp.setPoint106(pointService.findPointByType(306,year,mid).get(0).getPoint());
        fp.setPoint107(pointService.findPointByType(307,year,mid).get(0).getPoint());
        fp.setPoint108(pointService.findPointByType(308,year,mid).get(0).getPoint());
        fp.setPoint109(pointService.findPointByType(309,year,mid).get(0).getPoint());

        model.addAttribute("quantative",fp);
        model.addAttribute("year",year);
        return "/point/quantativelistDetail";
    }
    @RequestMapping(value = "totallist")
    public  String quantitative122(@RequestParam(value = "name" ,defaultValue = "" ) String name,@RequestParam(value = "year",defaultValue = "2018") int year,
                                    Model model){
        List<PointEntity> points = pointService.findPointByName("%"+name+"%",5,year);
        List<FinalPoint> finalPoints = new ArrayList<>();
        FinalPoint fp;
        for (PointEntity point : points){
            int mid = pointService.findPointByName(point.getName(),0,point.getYear()).get(0).getId();
            fp = new FinalPoint();
            fp.setName(point.getName());
            fp.setYear(point.getYear());
            fp.setMid(point.getId());

            if(pointService.findPointByType(5,year,mid).size() != 0){
                fp.setPoint1(pointService.findPointByType(5,year,mid).get(0).getPoint());
            }
            if (pointService.findPointByType(3,year,mid).size() !=0) {
                fp.setPoint1(pointService.findPointByType(3, year, mid).get(0).getPoint());
            }
            if (pointService.findPointByName(point.getName(),40,year).size() != 0) {
                fp.setPoint2(pointService.findPointByName(point.getName(), 40, year).get(0).getPoint());
            }
            finalPoints.add(fp);
        }
        model.addAttribute("quantative",finalPoints);
        model.addAttribute("year",year);
        return "/point/totallist";
    }
    @RequestMapping(value = "quantitative")
    public  String quantitative1(@RequestParam(value = "year" ,defaultValue = "2018" ) int year,@RequestParam(value = "name" ,defaultValue = "") String name,
                                 Model model){
        List<PointEntity> points = pointService.findPointByName("%"+name+"%",0,year);
        List<FinalPoint> finalPoints = new ArrayList<>();
        FinalPoint fp;
        for (PointEntity point : points){
            int mid = point.getId();
            fp = new FinalPoint();
            fp.setName(point.getName());
            fp.setYear(point.getYear());
            fp.setMid(point.getId());
            fp.setPoint1(pointService.findPointByType(1,year,mid).get(0).getPoint());
            fp.setPoint11(pointService.findPointByType(11,year,mid).get(0).getPoint());
            fp.setPoint12(pointService.findPointByType(12,year,mid).get(0).getPoint());
            fp.setPoint13(pointService.findPointByType(13,year,mid).get(0).getPoint());
            fp.setPoint14(pointService.findPointByType(14,year,mid).get(0).getPoint());


            fp.setPoint101(pointService.findPointByType(101,year,mid).get(0).getPoint());
            fp.setPoint102(pointService.findPointByType(102,year,mid).get(0).getPoint());
            fp.setPoint103(pointService.findPointByType(103,year,mid).get(0).getPoint());
            fp.setPoint104(pointService.findPointByType(104,year,mid).get(0).getPoint());
            fp.setPoint105(pointService.findPointByType(105,year,mid).get(0).getPoint());
            fp.setPoint106(pointService.findPointByType(106,year,mid).get(0).getPoint());
            fp.setPoint107(pointService.findPointByType(107,year,mid).get(0).getPoint());
            fp.setPoint108(pointService.findPointByType(108,year,mid).get(0).getPoint());
            fp.setPoint109(pointService.findPointByType(109,year,mid).get(0).getPoint());
            finalPoints.add(fp);
        }
        model.addAttribute("quantative",finalPoints);
        model.addAttribute("year",year);
        return "/point/quantative";
    }
    @RequestMapping(value = "quantitativelist")
    public  String quantitative121(@RequestParam(value = "year" ,defaultValue = "2018" ) int year,@RequestParam(value = "name" ,defaultValue = "") String name,
                                 Model model){
        List<PointEntity> points = pointService.findPointByName("%"+name+"%",0,year);
        List<FinalPoint> finalPoints = new ArrayList<>();
        FinalPoint fp;
        for (PointEntity point : points){
            int mid = point.getId();
            fp = new FinalPoint();
            fp.setName(point.getName());
            fp.setYear(point.getYear());
            fp.setMid(point.getId());
            fp.setPoint1(pointService.findPointByType(3,year,mid).get(0).getPoint());
            fp.setPoint11(pointService.findPointByType(31,year,mid).get(0).getPoint());
            fp.setPoint12(pointService.findPointByType(32,year,mid).get(0).getPoint());
            fp.setPoint13(pointService.findPointByType(33,year,mid).get(0).getPoint());
            fp.setPoint14(pointService.findPointByType(34,year,mid).get(0).getPoint());


            fp.setPoint101(pointService.findPointByType(301,year,mid).get(0).getPoint());
            fp.setPoint102(pointService.findPointByType(302,year,mid).get(0).getPoint());
            fp.setPoint103(pointService.findPointByType(303,year,mid).get(0).getPoint());
            fp.setPoint104(pointService.findPointByType(304,year,mid).get(0).getPoint());
            fp.setPoint105(pointService.findPointByType(305,year,mid).get(0).getPoint());
            fp.setPoint106(pointService.findPointByType(306,year,mid).get(0).getPoint());
            fp.setPoint107(pointService.findPointByType(307,year,mid).get(0).getPoint());
            fp.setPoint108(pointService.findPointByType(308,year,mid).get(0).getPoint());
            fp.setPoint109(pointService.findPointByType(309,year,mid).get(0).getPoint());
            finalPoints.add(fp);
        }
        model.addAttribute("quantative",finalPoints);
        model.addAttribute("year",year);
        return "/point/quantativelist";
    }
    @RequestMapping(value = "/quantitativeinit")
    @ResponseBody
    public R quantitative(@RequestParam(value = "year") int year, Model model,
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
                    float[] res = POI_Word.readData(dir+"\\"+file1);
                    int index=0,index1=0; PointEntity  point = new PointEntity();
                    System.out.println( pointService.findPointByName(attachment.getName(),0,year));
                    if ( pointService.findPointByName(attachment.getName(),0,year).size() == 0){
                        pointService.addPoint(attachment.getName(),0,year,0,0);
                    }
                    point = pointService.findPointByName(attachment.getName(),0,year).get(0);
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
                    finalPoint.setName(attachment.getName());
                    finalPoint.setMid(point.getId());
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
            pointService.addPoint(newpoint.getName(),newpoint.getMid(),year,newpoint.getPoint101(),101);
            pointService.addPoint(newpoint.getName(),newpoint.getMid(),year,newpoint.getPoint102(),102);
            pointService.addPoint(newpoint.getName(),newpoint.getMid(),year,newpoint.getPoint103(),103);
            pointService.addPoint(newpoint.getName(),newpoint.getMid(),year,newpoint.getPoint104(),104);
            pointService.addPoint(newpoint.getName(),newpoint.getMid(),year,newpoint.getPoint105(),105);
            pointService.addPoint(newpoint.getName(),newpoint.getMid(),year,newpoint.getPoint106(),106);
            pointService.addPoint(newpoint.getName(),newpoint.getMid(),year,newpoint.getPoint107(),107);
            pointService.addPoint(newpoint.getName(),newpoint.getMid(),year,newpoint.getPoint108(),108);
            pointService.addPoint(newpoint.getName(),newpoint.getMid(),year,newpoint.getPoint109(),109);
            pointService.addPoint(newpoint.getName(),newpoint.getMid(),year,newpoint.getPoint11(),11);
            pointService.addPoint(newpoint.getName(),newpoint.getMid(),year,newpoint.getPoint12(),12);
            pointService.addPoint(newpoint.getName(),newpoint.getMid(),year,newpoint.getPoint13(),13);
            pointService.addPoint(newpoint.getName(),newpoint.getMid(),year,newpoint.getPoint14(),14);
            pointService.addPoint(newpoint.getName(),newpoint.getMid(),year,newpoint.getPoint1(),1);
        }

        model.addAttribute("quantative",finalpoints);
        model.addAttribute("year",year);
        return R.ok();
    }

    @RequestMapping(value = "/quantitativelistinit")
    @ResponseBody
    public R quantitativeqqq(@RequestParam(value = "year") int year, Model model,
                          HttpServletRequest request){
        List<PointEntity> attachments = pointService.findPoint(1,year);
        if (attachments.size()==0){
            return R.error("请先确认已经生成定量数据");
        }
        int index=1;
        for (PointEntity pointEntity:attachments){
            pointService.addPoint(pointEntity.getName(),pointEntity.getMid(),pointEntity.getYear(),index,3);
            index++;
        }
        for (int type = 11;type<15;type++){
            attachments = pointService.findPoint(type,year);
            index=1;
            for (PointEntity pointEntity:attachments){
                pointService.addPoint(pointEntity.getName(),pointEntity.getMid(),pointEntity.getYear(),index,type+20);
                index++;
            }
        }
        for (int type = 101;type<110;type++){
            attachments = pointService.findPoint(type,year);
            index=1;
            for (PointEntity pointEntity:attachments){
                pointService.addPoint(pointEntity.getName(),pointEntity.getMid(),pointEntity.getYear(),index,type+200);
                index++;
            }
        }
        return R.ok("生成成功");
    }

    @RequestMapping(value = "/totalinit")
    @ResponseBody
    public R quantitativeq12312qq(@RequestParam(value = "year") int year, Model model,
                             HttpServletRequest request){
        List<PointEntity> attachments = pointService.findPoint(1,year);
        if (attachments.size()==0){
            return R.error("请先确认已经生成定量数据");
        }
       attachments = pointService.findPoint(2,year);
        if (attachments.size()==0){
            return R.error("请先确认已经生成定性数据");
        }
       attachments = pointService.findPoint(0,year);
        for (PointEntity point : attachments){
            double liang = pointService.findPointByName(point.getName(),1,year).get(0).getPoint();
            double xing = pointService.findPointByName(point.getName(),2,year).get(0).getPoint();
            double total = 0.3*liang+0.7*xing;
            pointService.updateById(point.getId(),total);
        }
        return R.ok("生成成功");
    }
    @RequestMapping(value = "/totallistinit")
    @ResponseBody
    public R quantitativeq1212qq(@RequestParam(value = "year") int year, Model model,
                                  HttpServletRequest request){
        List<PointEntity> attachments = pointService.findPoint(0,year);
        if (attachments.size()==0){
            return R.error("请先确认已经生成数据");
        }
        attachments = pointService.findPoint(0,year);
        int index=1;
        for (PointEntity point : attachments){
            pointService.addPoint(point.getName(),point.getMid(),point.getYear(),index,5);
        }

        return R.ok("生成成功");
    }
}
