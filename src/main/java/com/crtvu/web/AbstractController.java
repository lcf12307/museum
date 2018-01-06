package com.crtvu.web;

import com.crtvu.auth.AuthInterceptor;
import com.crtvu.dao.MemberDAO;
import com.crtvu.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;

/**
 * Controller公共组件
 * Created by Jixw on 2017/12/28.
 */
public abstract class AbstractController {

    @Autowired
    MemberDAO memberDAO;

    /**
     * 若用户不存在返回空
     * @param session
     * @return
     */
    protected Member getMember(HttpSession session) {
        Object SESSION_USERID=session.getAttribute(AuthInterceptor.SESSION_USERID);
        if(SESSION_USERID!=null){
            int member_id = Integer.parseInt(String.valueOf(SESSION_USERID));
            if(member_id<=0)
                return null;
            return memberDAO.getMemberInfo(member_id);
        }else{
            return null;
        }
    }

}
