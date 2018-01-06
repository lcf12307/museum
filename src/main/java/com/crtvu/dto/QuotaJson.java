package com.crtvu.dto;

/**
 * Created by yangming on 2017/12/28/0028.
 */

public class QuotaJson {
    private int id;//专家序号
    private String name;//专家名字
    private  String quotaId;//指标ID
    private String description;//专家简介
    private String year;//参与年份
    private boolean success;
    private String error;

    @Override
    public String toString() {
        return "QuotaJson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", quotaId='" + quotaId + '\'' +
                ", description='" + description + '\'' +
                ", year='" + year + '\'' +
                ", success=" + success +
                ", error='" + error + '\'' +
                '}';
    }


    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public QuotaJson() {
    }

    public QuotaJson(String name, String year) {
        this.name = name;
        this.year = year;
    }

    public QuotaJson(int id, String name, String quotaId, String description, String year) {
        this.id = id;
        this.name = name;
        this.quotaId = quotaId;
        this.description = description;
        this.year = year;
    }


    public QuotaJson(int id, String name, String quotaId, String description) {
        this.id = id;
        this.name = name;
        this.quotaId = quotaId;
        this.description = description;
    }

    public QuotaJson(String name, String quotaId, String description) {
        this.name = name;
        this.quotaId = quotaId;
        this.description = description;
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
}