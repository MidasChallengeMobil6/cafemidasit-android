package com.midasit.challenge.model;

/**
 * Created by ichaeeun on 2018. 5. 27..
 */

 public class Purchase {

    public int orderId;
    public int quantity;
    public String paymentDate;
    public String size;
    public String status;
    public String name;
    public String username;
    public String itemname;
    public int price;

    public Purchase(int orderId, int quantity, String paymentDate, String size, String status, String name, String username, String itemname, int price) {
        this.orderId = orderId;
        this.quantity = quantity;
        this.paymentDate = paymentDate;
        this.size = size;
        this.status = status;
        this.name = name;
        this.username = username;
        this.itemname = itemname;
        this.price = price;
    }
}
