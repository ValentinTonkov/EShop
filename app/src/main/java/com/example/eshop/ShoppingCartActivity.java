package com.example.eshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.eshop.databinding.ActivityShoppingCartBinding;

public class ShoppingCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityShoppingCartBinding binding = ActivityShoppingCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}