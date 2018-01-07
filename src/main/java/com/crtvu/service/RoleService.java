package com.crtvu.service;

import com.crtvu.auth.Auth;
import com.crtvu.entity.Role;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Jixw on 2017/12/26.
 */
public interface RoleService {

    List<Role> getAllRole(String name);

    Role getRole(int id);

    int editRole(int id,String name,String description,int auth);

    int delete(int id);

    int insert(String role,String description,int authority);

    HashMap<Integer,String> getIdNameMap();
}
