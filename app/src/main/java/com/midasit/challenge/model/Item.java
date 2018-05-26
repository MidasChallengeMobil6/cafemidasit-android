package com.midasit.challenge.model;

import java.io.Serializable;

public class Item implements Serializable {

    public int itemId;
    public String name;
    public String img;
    public int price;

    public Item() {

    }

    public Item(int itemId, String name, String img, int price) {
        this.itemId = itemId;
        this.name = name;
        this.img = img;
        this.price = price;
    }
}
