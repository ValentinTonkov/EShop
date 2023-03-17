package com.example.eshop.usbstick;

import java.util.LinkedList;
import java.util.List;

public class UsbSticksRepository {
    private List<UsbStick> list = new LinkedList<>();

    public void addUsbStick(UsbStick stick){
        list.add(stick);
    }

    public void removeUsbStick(UsbStick stick){
        list.remove(stick);
    }

    public UsbStick getUsbStickBySizeAndColor(int size, int color) throws NoSuchUsbStickException {
        for (UsbStick s : list) {
            if (s.getColor() == color && s.getMemorySize() == size){
                return s;
            }
        }
        throw new NoSuchUsbStickException("No such USB stick added");
    }

}
