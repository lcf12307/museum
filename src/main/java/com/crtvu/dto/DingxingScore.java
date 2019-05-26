package com.crtvu.dto;

/**
 * Created by Jixw on 2018/1/9.
 */
public class DingxingScore {

    String name;
    int year;
    double total;
    double score21;
    double score22;
    double score23;
    double score24;
    double score25;

    public DingxingScore() {
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

    public double getScore21() {
        return score21;
    }

    public void setScore21(double score21) {
        this.score21 = score21;
    }

    public double getScore22() {
        return score22;
    }

    public void setScore22(double score22) {
        this.score22 = score22;
    }

    public double getScore23() {
        return score23;
    }

    public void setScore23(double score23) {
        this.score23 = score23;
    }

    public double getScore24() {
        return score24;
    }

    public void setScore24(double score24) {
        this.score24 = score24;
    }

    public double getScore25() {
        return score25;
    }

    public void setScore25(double score25) {
        this.score25 = score25;
    }

    @Override
    public String toString() {
        return "DingxingScore{" +
                "name='" + name + '\'' +
                ", year=" + year +
                ", total=" + total +
                ", score21=" + score21 +
                ", score22=" + score22 +
                ", score23=" + score23 +
                ", score24=" + score24 +
                ", score25=" + score25 +
                '}';
    }
}
