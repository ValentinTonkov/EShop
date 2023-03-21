package com.example.eshop.store;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class OrderItem implements Serializable {
    private final int quantity;
    private final Product product;

    public OrderItem(Product product, int quantity){

        this.quantity = quantity;
        this.product = product;
    }

    public Double getTotalPrice(){
        return product.getPrice() * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product getProduct() {
        return product;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(product.getDescription());
        builder.append(" USD ");
        builder.append(product.getPrice());
        builder.append("/piece  (");
        builder.append(quantity);
        builder.append(" pieces)");

        return builder.toString();
    }
}