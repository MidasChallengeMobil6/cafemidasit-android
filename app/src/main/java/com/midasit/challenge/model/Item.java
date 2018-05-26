package com.midasit.challenge.model;

import java.io.Serializable;

public class Item implements Serializable {

    public int id;
    public String name;
    public String img;
    public int price;

    public Item() {

    }

    public Item(int id, String name, String img, int price) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.price = price;
    }
}
