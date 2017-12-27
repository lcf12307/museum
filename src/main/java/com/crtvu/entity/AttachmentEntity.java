package com.crtvu.entity;

public class AttachmentEntity{
    private int id;
    private String name;
    private String file;
    private int type;

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