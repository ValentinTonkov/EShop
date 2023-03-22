package com.example.eshop.store;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents an order and holds a List of OrderItems
 * */
public class Order implements Serializable {

    private final List<OrderItem> orderItems = new LinkedList<>();

    /**
     * This method adds an OrderItem object to the Order. If the item is already added,
     * than the item is updated with the new quantity
     * @param orderItem the new order item (product with quantity) that will be added to the order
     * */
    public void addOrderItem(OrderItem orderItem){

        Product newOrdersProduct = orderItem.getProduct();

        if (isProductAlreadyAdded(newOrdersProduct)){

            OrderItem orderItemToUpdate = getOrderItemByProduct(newOrdersProduct);
            int quantityToAdd = orderItem.getQuantity();
            orderItemToUpdate.addQuantity(quantityToAdd);
        } else {
            orderItems.add(orderItem);
        }
    }


    /**
     * @return the OrderItem that holds the required Product
     * */
    private OrderItem getOrderItemByProduct(Product newOrdersProduct) {
        for (OrderItem item :
                orderItems) {
            if (item.getProduct().getId() == newOrdersProduct.getId()) {
                return item;
            }
        }
        return null;
    }

    /**
     * @param p is searched in the list with OrderItems
     * */
    private boolean isProductAlreadyAdded(Product p) {
        for (OrderItem item :
               orderItems ) {
            if (item.getProduct().getId() == p.getId()){
                return true;
            }
        }
        return false;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Double getTotalPrice(){
        Double price = 0.0;
        for (OrderItem i :
                orderItems) {
            price += i.getTotalPrice();
        }
        return price;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (OrderItem item :
                orderItems) {
            builder.append(item.toString());
            builder.append("\n");
        }
        return builder.toString();
    }
}
