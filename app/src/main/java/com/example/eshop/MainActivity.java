package com.example.eshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final double PRICE_16GB = 7.5;
    private static final double PRICE_32GB = 15.2;
    private static final double PRICE_64GB = 18.75;

    private List<USBStick> usbSticks = new ArrayList<>();

    private int selectedSize = USBStick.GB_16;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            USBStick usbStick16 = new USBStick(USBStick.GB_16, PRICE_16GB, "16 GB USB memory");
            USBStick usbStick32 = new USBStick(USBStick.GB_32, PRICE_32GB, "32 GB USB memory");
            USBStick usbStick64 = new USBStick(USBStick.GB_64, PRICE_64GB, "64 GB USB memory");

            usbSticks.add(usbStick16);
            usbSticks.add(usbStick32);
            usbSticks.add(usbStick64);
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        updateQuantityText();
        updateTotalPrice();
        updateProductDescription();

        ImageButton addButton = findViewById(R.id.addButton);
        ImageButton removeButton = findViewById(R.id.removeButton);

        addButton.setOnClickListener(v -> {
            quantity++;
            updateQuantityText();
            updateTotalPrice();
        });

        removeButton.setOnClickListener(v -> {
            if (quantity > 0){
                quantity--;
                updateQuantityText();
                updateTotalPrice();
            }else {
                Toast.makeText(getApplicationContext(), "Quantity must be greater than 0", Toast.LENGTH_SHORT).show();
            }
        });

        RadioGroup radioGroup = findViewById(R.id.memorySizeRG);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final int id16GB = R.id.radioButton16GB;
                final int id32GB = R.id.radioButton32GB;
                final int id64GB = R.id.radioButton64GB;

                switch (checkedId){
                    case id16GB:
                        updateInfoBySize(USBStick.GB_16);
                        break;

                    case id32GB:
                        updateInfoBySize(USBStick.GB_32);
                        break;

                    case id64GB:
                        updateInfoBySize(USBStick.GB_64);
                        break;
                }
            }
        });
    }

    private void updateQuantityText() {
        TextView quantityText = findViewById(R.id.quantityText);
        quantityText.setText(String.valueOf(quantity));
    }

    private void updateTotalPrice(){
        try {
            USBStick currentStick = getUsbStickBySize(selectedSize);
            Double totalPrice = currentStick.getPrice() * quantity;
            TextView totalPriceText = findViewById(R.id.totalPriceText);
            totalPriceText.setText(String.format("%.2f",totalPrice));
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private USBStick getUsbStickBySize(int selectedSize) throws Exception {
        for (USBStick s :
                usbSticks) {
            if (s.getMemorySize() == selectedSize) {
                return s;
            }
        }
        throw new Exception("Invalid size");
    }

    private void updateProductDescription()  {
        TextView descriptionText = findViewById(R.id.dexcribtionText);
        String description = null;
        try {
            description = getUsbStickBySize(selectedSize).getDescription();
            descriptionText.setText(description);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void updateInfoBySize(int size){
        selectedSize = size;
        updateTotalPrice();
        updateProductDescription();
    }

}