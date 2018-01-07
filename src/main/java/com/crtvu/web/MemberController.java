package com.crtvu.web;

import com.crtvu.auth.Auth;
import com.crtvu.dto.MemberJson;
import com.crtvu.entity.Member;
import com.crtvu.entity.Role;
import com.crtvu.service.MemberService;
import com.crtvu.service.RoleService;
import com.crtvu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;


@Controller
@RequestMapping("/member")
public class MemberController extends AbstractController{

    @Autowired
    MemberService memberService;

    @Autowired
    RoleService roleService;

    @RequestMapping("/add")
    public String add(Model model){
        List<Role> roles =  roleService.getAllRole("");
        model.addAttribute("roles",roles);
        return "/member/add";
    }

    @RequestMapping(value = "/addProcess",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public R addProcess(@RequestBody MemberJson memberJson){
        try{
            int rtn = memberService.insert(memberJson);
            if(rtn>0)
                return R.ok();
            else if(rtn==-1)
                return R.error("用户名重复，请换一个！");
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error();
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id,Model model){
        Member member = memberService.getMember(id);
        if(member!=null)
            member.setPassword("");
        List<Role> roles =  roleService.getAllRole("");
        model.addAttribute("member",member);
        model.addAttribute("roles",roles);
        return "/member/edit";
    }

    @RequestMapping(value = "/editProcess/{id}",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"} )
    @ResponseBody
    public R editProcess(@PathVariable("id") int id,@RequestBody MemberJson memberJson){
        try{
            if(memberService.edit(id,memberJson)>0)
                return R.ok();
        }catch (Exception e){
            e.printStackTrace();
        }
        return R.error();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public R delete(int id){
        return memberService.delete(id)>0?R.ok():R.error();
    }

    @RequestMapping("/list")
    public String listJump(){
        return "redirect:/member/list/1";
    }

    @RequestMapping("/list/{page}")
    public String list(@PathVariable int page,@RequestParam(value = "nameKey",defaultValue = "") String nameKey,Model model){
        try {
            if(page<=0)
                page = 1;
            nameKey = new String(nameKey.getBytes("ISO-8859-1"), "UTF-8");
            List<Member> memberList = memberService.getMemberListLimit(page,nameKey);
            int pages = memberService.getPageCount(nameKey);
            HashMap<Integer,String> roles = roleService.getIdNameMap();
            model.addAttribute("roles",roles);
            model.addAttribute("memberList",memberList);
            model.addAttribute("pages",pages);
            model.addAttribute("nameKey",nameKey);
            return "/member/list";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

}
