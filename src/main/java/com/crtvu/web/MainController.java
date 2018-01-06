package com.crtvu.web;

import com.crtvu.auth.Auth;
import com.crtvu.auth.AuthInterceptor;
import com.crtvu.dao.MemberDAO;
import com.crtvu.entity.Member;
import com.crtvu.entity.Role;
import com.crtvu.service.RoleService;
import com.crtvu.utils.MuseumContants;
import com.crtvu.utils.R;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Jixw on 2017/12/26.
 */
@Controller
public class MainController extends AbstractController{

    @Autowired
    private RoleService roleService;

    @Autowired
    private Producer producer;

    @Autowired
    private MemberDAO memberDAO;


    @RequestMapping("/")
    public String returnMainPage() {
        return "redirect:/index";
    }

    //@Auth()
    @RequestMapping("/main")
    public String getHomePage() {
        return "main";
    }

    @RequestMapping("/common")
    public String commonPage() {
        return "common";
    }


    @RequestMapping("/index")
    public String checkLogin(HttpSession session) {
        Member member = getMember(session);
        if(member==null)
            return "login";
        else{
            System.out.println("userLogin:"+member.toString());
            return "index";
        }
    }

    @RequestMapping(value="/login",method = RequestMethod.POST)
    @ResponseBody
    public R login(HttpSession session, String username, String password, String captcha){
        String kaptcha = session.getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
        if(!captcha.equalsIgnoreCase(kaptcha)){
            return R.error("验证码不正确");
        }
        password=DigestUtils.md5Hex(MuseumContants.SALT_FOR_MEMBER+password);
        Member member =memberDAO.checkLogin(username,password);
        if(member==null)
            return R.error("用户名或密码错误！");
        int member_id = member.getId();
        if(member_id>0){
            int role = member.getRole();
            if(role<=0)
                return R.error("用户没有登录权限");
            session.setAttribute(AuthInterceptor.SESSION_USERID,member_id);
            session.setAttribute(AuthInterceptor.SESSION_AUTHS, roleService.getAuthotity(role));
            return R.ok();
        }
        return R.error();
    }

    /**
     * 退出
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:index";
    }

    @RequestMapping("captcha.jpg")
    public void captcha(HttpSession session,HttpServletResponse response)throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到session
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
    }

    @RequestMapping(value = "/test/{page}", method = RequestMethod.GET)
    public String pageto(@PathVariable("page") String page){
        System.out.println(page);
        return page;
    }

}
