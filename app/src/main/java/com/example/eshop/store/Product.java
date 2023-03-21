package com.example.eshop.store;

import java.io.Serializable;

public interface Product extends Comparable<Product>, Serializable {

    int getId();
    Double getPrice();
    String getDescription();
}
