package com.example.eshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
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

import com.example.eshop.databinding.ActivityMainBinding;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final double PRICE_16GB = 7.5;
    private static final double PRICE_32GB = 15.2;
    private static final double PRICE_64GB = 18.75;

    private List<USBStick> usbSticks = new ArrayList<>();
    private ActivityMainBinding binding;

    private int selectedSize = USBStick.GB_16;
    private int selectedColor = Color.BLUE;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.mainLayout);

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
        
        binding.addButton.setOnClickListener(v -> {
            quantity++;
            updateQuantityText();
            updateTotalPrice();
        });

        binding.removeButton.setOnClickListener(v -> {
            if (quantity > 0){
                quantity--;
                updateQuantityText();
                updateTotalPrice();
            }else {
                Toast.makeText(getApplicationContext(), "Quantity must be greater than 0", Toast.LENGTH_SHORT).show();
            }
        });

            binding.memorySizeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
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


        binding.chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {

                for (int id :
                        checkedIds) {
                    if (id == R.id.blackChip) {
                        binding.usbStickImage.setImageResource(R.drawable.usb_stick_black);
                        selectedColor = Color.BLACK;
                    } else if (id == R.id.blueChip){
                        binding.usbStickImage.setImageResource(R.drawable.usb_stick_blue);
                        selectedColor = Color.BLUE;
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final int profileId = R.id.profile_item;
        final int aboutId = R.id.about_item;
        final int shoppingCartId = R.id.shopping_cart_item;
        final int ordersId = R.id.orders_item;

        switch (item.getItemId()) {
            case profileId:
                Toast.makeText(getApplicationContext(), "Profile clicked", Toast.LENGTH_SHORT).show();
                break;
            case aboutId:
                break;
            case shoppingCartId:
                break;
            case ordersId:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    private void updateQuantityText() {
        binding.quantityText.setText(String.valueOf(quantity));
    }

    private void updateTotalPrice(){
        try {
            USBStick currentStick = getUsbStickBySize(selectedSize);
            Double totalPrice = currentStick.getPrice() * quantity;
            binding.totalPriceText.setText(String.format("%.2f",totalPrice));
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
        String description = null;
        try {
            description = getUsbStickBySize(selectedSize).getDescription();
            binding.descriptionText.setText(description);
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