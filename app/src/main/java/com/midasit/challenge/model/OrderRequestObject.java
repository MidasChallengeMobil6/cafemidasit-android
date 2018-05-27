package com.midasit.challenge.model;

/**
 * Created by ichaeeun on 2018. 5. 26..
 */

public class OrderRequestObject {

    int userId;
    int quantity;
    String size;
    int itemId;

    public OrderRequestObject(int userId, int quantity, String size, int itemId) {
        this.userId = userId;
        this.quantity = quantity;
        this.size = size;
        this.itemId = itemId;
    }
}