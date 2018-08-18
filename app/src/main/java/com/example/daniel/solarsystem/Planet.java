package com.example.daniel.solarsystem;

public class Planet {

    private int id;
    private String name;
    private int image;
    private String description;
    private String link;

    public Planet(int id, String name, int image, String description, String link) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.description = description;
        this.link = link;
    }

    public Planet(){
        id = 0;
        name ="";
        image = 0;
        description = "";
        link = "";
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

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
