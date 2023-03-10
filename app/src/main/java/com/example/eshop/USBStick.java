package com.example.eshop;

public class USBStick {
    public static final int GB_16 = 16;
    public static final int GB_32 = 32;
    public static final int GB_64 = 64;

    private final int memorySize;
    private final Double price;
    private final String description;

    public USBStick(int memorySize, Double price, String description) throws Exception {
        if (memorySize == GB_16 || memorySize == GB_32 || memorySize == GB_64) {
            this.memorySize = memorySize;
        } else throw new Exception("Invalid size");
        if (price > 0) {
            this.price = price;
        } else throw new Exception("Invalid price");
        this.description = description;
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
}
