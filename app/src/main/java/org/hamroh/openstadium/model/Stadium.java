package org.hamroh.openstadium.model;

public class Stadium {

    private int id;
    private String name, image, price, likes, distance;

    public Stadium() {
    }

    public Stadium(int id, String name, String image, String price, String likes, String distance) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
        this.likes = likes;
        this.distance = distance;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
