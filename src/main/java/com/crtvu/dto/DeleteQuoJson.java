package com.crtvu.dto;

/**
 * Created by yangming on 2017/12/28/0028.
 */

public class DeleteQuoJson {

    private int page;
    private String name;

    @Override
    public String toString() {
        return "DeleteQuoJson{" +
                "page=" + page +
                ", name='" + name + '\'' +
                '}';
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DeleteQuoJson() {
    }

    public  DeleteQuoJson(int page) {
        this.page = page;
    }

    public  DeleteQuoJson(int page, String name) {
        this.page = page;
        this.name = name;
    }


}
