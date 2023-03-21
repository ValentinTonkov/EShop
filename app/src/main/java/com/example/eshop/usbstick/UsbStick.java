package com.example.eshop.usbstick;

import com.example.eshop.store.Product;

public final class UsbStick implements Product {
    //constants used to identify memory size
    public static final int GB_16 = 16;
    public static final int GB_32 = 32;
    public static final int GB_64 = 64;

    private final int id;
    private final int memorySize;
    private final Double price;
    private final String description;
    private final int color;

    public UsbStick(int id, int memorySize, Double price, String description, int color) throws NoSuchUsbStickException {
        if (memorySize == GB_16 || memorySize == GB_32 || memorySize == GB_64) {
            this.memorySize = memorySize;
        } else throw new NoSuchUsbStickException("Invalid size");
        if (price > 0) {
            this.price = price;
        } else throw new NoSuchUsbStickException("Invalid price");
        this.description = description;
        this.color = color;
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    public Double getPrice() {
        return price;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public String getDescription() {
        return description;
    }

    public int getColor() {
        return color;
    }

    @Override
    public int compareTo(Product o) {
        return 0;
    }
}
