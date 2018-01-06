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
 */
@Controller
@RequestMapping("/member")
public class MemberController extends AbstractController{

    @Autowired
    MemberService memberService;


}
