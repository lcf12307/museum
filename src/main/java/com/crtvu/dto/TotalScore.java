package com.crtvu.dto;

/**
 * Created by Jixw on 2018/1/10.
 */
public class TotalScore {

    private String name;
    private int year;
    private double total;
    private double dingxing;
    private double dingliang;

    public TotalScore() {
    }

    public TotalScore(String name, int year, double total, double dingxing, double dingliang) {
        this.name = name;
        this.year = year;
        this.total = total;
        this.dingxing = dingxing;
        this.dingliang = dingliang;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDingxing() {
        return dingxing;
    }

    public void setDingxing(double dingxing) {
        this.dingxing = dingxing;
    }

    public double getDingliang() {
        return dingliang;
    }

    public void setDingliang(double dingliang) {
        this.dingliang = dingliang;
    }

    public void setTotal() {
        this.total = Double.parseDouble(new java.text.DecimalFormat("#.00").format(this.dingxing*0.7+dingliang*0.3));
    }
}
