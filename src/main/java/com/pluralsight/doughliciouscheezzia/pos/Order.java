package com.pluralsight.doughliciouscheezzia.pos;

import com.pluralsight.doughliciouscheezzia.models.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static com.pluralsight.doughliciouscheezzia.pos.Utility.currentOrderDateTime;

public class Order {
    private String dateTime;
    private List<MenuItem> orderItems;
    private String orderID;

    public Order(String orderID) {
        this.orderItems = new ArrayList<>();
        this.dateTime = currentOrderDateTime();
        this.orderID = orderID;


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

    public String getOrderID() {
        return orderID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "dateTime='" + dateTime + '\'' +
                ", orderItems=" + orderItems +
                ", orderID='" + orderID + '\'' +
                '}';
    }
}
