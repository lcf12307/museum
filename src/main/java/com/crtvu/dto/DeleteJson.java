package com.crtvu.dto;

/**
 * Created by yangming on 2017/3/28/0028.
 */

public class DeleteJson {

    private int page;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DeleteJson(int page, String id) {

        this.page = page;
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public DeleteJson(int page) {

        this.page = page;
    }

    public DeleteJson() {

    }
}
