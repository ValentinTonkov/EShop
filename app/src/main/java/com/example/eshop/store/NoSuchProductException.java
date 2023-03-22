package com.example.eshop.store;

public class NoSuchProductException extends Throwable {
    public NoSuchProductException(String s) {
        super(s);
    }
}
