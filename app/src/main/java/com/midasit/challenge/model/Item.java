package com.midasit.challenge.model;

import java.io.Serializable;

public class Item implements Serializable {

    public int id;
    public String name;
    public String imgurl;
    public int price;
    public int category;

    public Item() {

    }

    public Item(int id, String name, String imgurl, int price) {
        this.id = id;
        this.name = name;
        this.imgurl = imgurl;
        this.price = price;
    }

    public Item(int id, String name, String imgurl, int price, int category) {
        this.id = id;
        this.name = name;
        this.imgurl = imgurl;
        this.price = price;
        this.category = category;
    }
}
