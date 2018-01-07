package com.crtvu.dto;

import com.crtvu.Authority;
import com.crtvu.entity.Role;

import java.util.HashSet;

/**
 * Created by Jixw on 2018/1/6.
 */
public class RoleJson {

    private int id;
    private String name;
    private String description;
    private int authority;
    private String authorityNames;

    public RoleJson(){}

    public RoleJson(int id, String name, String description, int authority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.authority = authority;
    }

    public RoleJson(int id, String name, String description, int authority, String authorityNames) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.authority = authority;
        this.authorityNames = authorityNames;
    }

    public RoleJson(Role role){
        this.id=role.getId();
        this.name = role.getName();
        this.description = role.getDescription();
        this.authority = role.getAuthority();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public void setAuthorityName(String authorityNames){
        this.authorityNames=authorityNames;
    }

    public String getAuthorityNames() {
        return authorityNames;
    }
}
