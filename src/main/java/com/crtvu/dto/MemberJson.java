package com.crtvu.dto;

/**
 * Created by Jixw on 2018/1/6.
 */
public class MemberJson {

    private String name;
    private int age;
    private String password;
    private String email;
    private String phone;
    private int role;

    public MemberJson() {
    }

    public MemberJson(String name, int age, String password, String email, String phone, int role) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = role;
    }

    public MemberJson(String name, String age, String password, String email, String phone, String role) {
        this.name = name;
        this.age = Integer.parseInt(age);
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.role = Integer.parseInt(role);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAge(String age) {
        this.age = Integer.parseInt(age);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setRole(String role) {
        this.role = Integer.parseInt(role);
    }

    @Override
    public String toString() {
        return "MemberJson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role=" + role +
                '}';
    }
}
