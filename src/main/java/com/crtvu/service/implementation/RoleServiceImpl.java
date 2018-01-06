package com.crtvu.service.implementation;

import com.crtvu.dao.RoleDAO;
import com.crtvu.entity.Role;
import com.crtvu.service.RoleService;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

/**
 * Created by Jixw on 2017/12/26.
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDAO roleDAO;

    public HashSet<String> getAuthotity(int role_id) {
        HashSet<String> set = new HashSet<String>();
        set.add("user");
        return set;
    }

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
        return roleDAO.editRole(id, StringEscapeUtils.escapeSql(name), StringEscapeUtils.escapeSql(description), auth);
    }

    @Override
    public int delete(int id) {
        return roleDAO.delete(id);
    }

    @Override
    public int insert(String name, String description, int authority) {
        return roleDAO.insert(StringEscapeUtils.escapeSql(name), StringEscapeUtils.escapeSql(description), authority);
    }
}
