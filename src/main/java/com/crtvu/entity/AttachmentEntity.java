package com.crtvu.entity;

public class AttachmentEntity{
    private int id;
    private String name;
    private String file;
    private int type;
    private int year;
    private int addtime;

    public int getAddtime() {
        return addtime;
    }

    public void setAddtime(int addtime) {
        this.addtime = addtime;
    }

    public AttachmentEntity(String name, String file, int type, int year, int addtime) {

        this.name = name;
        this.file = file;
        this.type = type;
        this.year = year;
        this.addtime = addtime;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public AttachmentEntity(String name, String file, int type, int year) {

        this.name = name;
        this.file = file;
        this.type = type;
        this.year = year;
    }

    public AttachmentEntity() {
    }

    public AttachmentEntity(String name, String file, int type) {
        this.name = name;
        this.file = file;
        this.type = type;
    }


    public AttachmentEntity(int id, String name, String file, int type) {
        this.id = id;
        this.name = name;
        this.file = file;
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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AttachmentEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", file='" + file + '\'' +
                ", type=" + type +
                '}';
    }
}