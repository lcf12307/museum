package com.crtvu.dto;

/**
 * Created by yangming on 2017/12/28/0028.
 */

import com.crtvu.service.QuotaService;

public class ErrorQuoJson {

    private int id;
    private String errors;

    public ErrorQuoJson() {
    }


    public ErrorQuoJson(int id, String errors) {
        this.id = id;
        this.errors = errors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }


    @Override
    public String toString() {
        return "ErrorQuoJson{" +
                "id=" + id +
                ", errors='" + errors + '\'' +
                '}';
    }
}
