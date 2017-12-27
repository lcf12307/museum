package com.crtvu.entity;

public class PointEntity{
    private int id;
    private String name;
    private int mid;
    private int year;
    private int point;
    private int type;

    public PointEntity(int mid, int year, int point, int type) {
        this.mid = mid;
        this.year = year;
        this.point = point;
        this.type = type;
    }

    public PointEntity() {

    }

    public PointEntity(int id, String name, int mid, int year, int point, int type) {
        this.id = id;
        this.name = name;
        this.mid = mid;
        this.year = year;
        this.point = point;
        this.type = type;
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

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "PointEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mid=" + mid +
                ", year=" + year +
                ", point=" + point +
                ", type=" + type +
                '}';
    }
}