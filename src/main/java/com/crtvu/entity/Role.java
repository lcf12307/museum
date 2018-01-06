package com.crtvu.entity;

public class Role{

    private int id;
    private String name;
    private String description;
    private int authority;

    public Role(){}

    public Role(int id, String name, String description, int authority) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.authority = authority;
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
}