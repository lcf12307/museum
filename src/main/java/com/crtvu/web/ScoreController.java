package com.crtvu.web;


import com.crtvu.dto.DeleteJson;
import com.crtvu.entity.AttachmentEntity;
import com.crtvu.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private com.crtvu.service.AttachmentService AttachmentService;

    @RequestMapping(value = "/list/{page}",method = RequestMethod.GET)
    public String list(@RequestParam(value = "name",defaultValue = "") String name, @RequestParam(value = "year",defaultValue="0") int year,
                       Model model, @PathVariable("page") int page)
    {
        try{
            name = new String(name.getBytes("ISO-8859-1"), "UTF-8");
            List<AttachmentEntity> list= AttachmentService.pagingAttachment(page,year , name,2);
            model.addAttribute("count",page);
            int pages = AttachmentService.page(year,name,2)/20 + 1;
            model.addAttribute("list",list);
            model.addAttribute("pages",pages);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return "notification/list";
    }
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public DeleteJson delete(@RequestBody DeleteJson deletejson)
    {
        int result = AttachmentService.delete(  Integer.parseInt(deletejson.getId()));
        deletejson.setId(Integer.toString(result));
        return deletejson;
    }

}
