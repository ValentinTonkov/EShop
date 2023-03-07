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

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int GB_16 = 0;
    private static final int GB_32 = 1;
    private static final int GB_64 = 2;

    private static final double PRICE_16GB = 7.5;
    private static final double PRICE_32GB = 15.2;
    private static final double PRICE_64GB = 18.75;

    HashMap<Integer, Double> priceMap = new HashMap<>();

    private int selectedSize = GB_16;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        priceMap.put(GB_16, PRICE_16GB);
        priceMap.put(GB_32, PRICE_32GB);
        priceMap.put(GB_64, PRICE_64GB);

        updateQuantityText();
        updateTotalPrice();

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
                        Toast.makeText(getApplicationContext(), "clicked 16gb", Toast.LENGTH_SHORT).show();
                        selectedSize = GB_16;
                        updateTotalPrice();
                        break;

                    case id32GB:
                        Toast.makeText(getApplicationContext(), "clicked 32gb", Toast.LENGTH_SHORT).show();
                        selectedSize = GB_32;
                        updateTotalPrice();
                        break;

                    case id64GB:
                        Toast.makeText(getApplicationContext(), "clicked 64gb", Toast.LENGTH_SHORT).show();
                        selectedSize = GB_64;
                        updateTotalPrice();
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
        Double totalPrice = priceMap.get(selectedSize) * quantity;

        TextView totalPriceText = findViewById(R.id.totalPriceText);
        totalPriceText.setText(String.format("%.2f",totalPrice));
    }

}