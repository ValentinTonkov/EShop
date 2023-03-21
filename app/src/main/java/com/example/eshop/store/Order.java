package com.example.eshop.store;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class Order implements Serializable {

    private List<OrderItem> orderItems = new LinkedList<>();

    public void addOrderItem(OrderItem orderItem){
        Product newOrdersProduct = orderItem.getProduct();

        for (int i = 0; i < orderItems.size(); i++) {
            OrderItem currentOrderItemFromList = orderItems.get(i);
            Product orderItemsProduct = currentOrderItemFromList.getProduct();
            if (orderItemsProduct.getId() == newOrdersProduct.getId()) {
                //the product is already added, increasing the quantity;
                int currentQuantity = currentOrderItemFromList.getQuantity();
                OrderItem updatedItem = new OrderItem(orderItemsProduct, currentQuantity + orderItem.getQuantity());
                orderItems.remove(i);
                orderItems.add(updatedItem);
                return;
            }
        }
        orderItems.add(orderItem);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (OrderItem item :
                orderItems) {
            builder.append(item.getProduct().getDescription());
            builder.append(", quantity: ");
            builder.append(item.getQuantity());
            builder.append(", price: ");
            builder.append(item.getTotalPrice());
            builder.append("\n");
        }
        return builder.toString();
    }
}
