package com.crtvu.service.implementation;

import com.crtvu.Authority;
import com.crtvu.dao.MemberDAO;
import com.crtvu.dao.RoleDAO;
import com.crtvu.entity.Role;
import com.crtvu.service.RoleService;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Jixw on 2017/12/26.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDAO roleDAO;

    @Autowired
    MemberDAO memberDAO;

    @Override
    public List<Role> getAllRole(String name) {
        return roleDAO.getAllRole(StringEscapeUtils.escapeSql(name));

    }

    @Override
    public Role getRole(int id) {
        return roleDAO.getRole(id);
    }

    @Override
    public int editRole(int id, String name, String description, int auth) {
        if(roleDAO.checkName(id,name)>0)
            return -1;
        return roleDAO.editRole(id, StringEscapeUtils.escapeSql(name), StringEscapeUtils.escapeSql(description), auth);
    }

    @Override
    public int delete(int id) {
        if(memberDAO.getMemberCountByRole(id)>0)
            return -1;
        return roleDAO.delete(id);
    }

    @Override
    public int insert(String name, String description, int authority) {
        if(roleDAO.checkName(-1,name)>0)
            return -1;
        return roleDAO.insert(StringEscapeUtils.escapeSql(name), StringEscapeUtils.escapeSql(description), authority);
    }

    @Override
    public HashMap<Integer, String> getIdNameMap() {
        List<Role> roles = roleDAO.getAllRole("");
        HashMap<Integer, String> rtn = new HashMap<>();
        for(Role role:roles){
            rtn.put(role.getId(),role.getName());
        }
        return rtn;
    }
}
