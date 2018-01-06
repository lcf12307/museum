package com.crtvu.entity;

/**
 * Created by yangming on 2017/12/26/0026.
 */
public class Quota {
    private int id;//专家序号
    private String name;//专家名字
    private String quotaId;//指标ID
    private String description;//专家简介
    private String year;//参与年份

    public Quota() {
    }

    public Quota(String name, String quotaId, String description) {
        this.name = name;
        this.quotaId = quotaId;
        this.description = description;
    }

    public Quota(int id, String name, String quotaId, String description) {
        this.id = id;
        this.name = name;
        this.quotaId = quotaId;
        this.description = description;
    }

    public Quota(int id, String name, String quotaId, String description, String year) {
        this.id = id;
        this.name = name;
        this.quotaId = quotaId;
        this.description = description;
        this.year = year;
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

    public String getQuotaId() {
        return quotaId;
    }

    public void setQuotaId(String quotaId) {
        this.quotaId = quotaId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Quota{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quotaId='" + quotaId + '\'' +
                ", description='" + description + '\'' +
                ", year='" + year + '\'' +
                '}';
    }
}
