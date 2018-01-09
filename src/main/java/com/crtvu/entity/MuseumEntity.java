package com.crtvu.entity;

public class MuseumEntity {

    private int id;
    private String name;
    private String category;
    private String level;
    private String year;
    private String description;
    public MuseumEntity(){

    }

    public MuseumEntity(int id, String name, String category, String level, String year, String description){
        this.id = id;
        this.name = name;
        this.category = category;
        this.level = level;
        this.year = year;
        this.description = description;
    }

    public MuseumEntity(String name, String category, String level, String year, String description) {
        this.name = name;
        this.category = category;
        this.level = level;
        this.year = year;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "MuseumEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", level=" + level +
                ", year='" + year + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


