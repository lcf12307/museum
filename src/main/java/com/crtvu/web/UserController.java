package com.crtvu.web;

import com.crtvu.auth.Auth;
import com.crtvu.entity.Member;
import com.crtvu.service.MemberService;
import com.crtvu.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Jixw on 2017/12/28.
 * 处理在线用户修改自己的资料等
 */
@Controller
@RequestMapping("/member")
public class UserController extends AbstractController{

    @Autowired
    MemberService memberService;

    @Auth()
    @RequestMapping("/info")
    @ResponseBody
    public R getMenberInfo(HttpSession session){
        Member member = getMember(session);
        return member==null?R.error("用户不存在"):R.ok().put("member",getMember(session));
    }

    //修改个人信息页面跳转
    @Auth()
    @RequestMapping("/editMyInfo")
    public String editMyInfo(HttpSession session, Model model){
        Member member = getMember(session);
        if(member.getId()<=0){
            return "redirect:/index";
        }
        model.addAttribute("member",member);
        return "/member/editMyInfo";

    }

    //修改个人信息提交
    @Auth()
    @RequestMapping(value = "/editMyInfoProcess",method = RequestMethod.POST)
    @ResponseBody
    public R editMyInfoProcess(HttpSession session,int age,String phone,String email){
        Member member = getMember(session);
        if(member.getId()<=0){
            session.invalidate();
            return R.error();
        }
        if(age>0&&phone!=null&&!phone.equals("")&&email!=null&&!email.equals("")){
            int rtn = memberService.updateMemberInfo(member.getId(),age, phone,email);
            if(rtn>0)
                return R.ok("修改成功");
            else
                return R.error("修改失败，请稍后重试");
        }else{
            return R.error("参数错误");
        }
    }

    //修改密码页面跳转
    @Auth()
    @RequestMapping("/editMyPassword")
    public String editMyPassword(HttpSession session, Model model){
        Member member = getMember(session);
        if(member.getId()<=0){
            return "redirect:/index";
        }
        model.addAttribute("member",member);
        return "/member/editMyPassword";

    }

    //修改我的密码
    @Auth()
    @RequestMapping(value = "/password",method = RequestMethod.POST)
    @ResponseBody
    public R updatePassword(HttpSession session,String password,String newPassword){
        Member member = getMember(session);
        int rtn = memberService.updatePassword(member.getId(), password, newPassword);
        if(rtn==0)
            return R.error("原密码错误");
        return R.ok();
    }
}
