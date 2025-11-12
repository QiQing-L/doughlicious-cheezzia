package com.pluralsight.doughliciouscheezzia.pos;

import com.pluralsight.doughliciouscheezzia.models.MenuItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Order {
    private String dateTime;
    private List<MenuItem> orderItems;
    private String orderID;

    public Order(List<MenuItem> orderItems) {
        this.orderItems = new ArrayList<>();

    }

    public List<MenuItem> getOrderItems() {
        return orderItems;
    }

    public void addItem (MenuItem item, int quantity ){
        for (int i = 0; i < quantity; i++) {
            orderItems.add(item);
        }
    }

    public double calculatePrice(){
        double orderTotal = 0;
        for (MenuItem orderItem : orderItems) {
            orderTotal += orderItem.calculatePrice();
        }
        return orderTotal;
    }
}
