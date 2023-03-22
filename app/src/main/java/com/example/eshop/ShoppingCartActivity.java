package com.example.eshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.eshop.databinding.ActivityShoppingCartBinding;
import com.example.eshop.store.Order;

public class ShoppingCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityShoppingCartBinding binding = ActivityShoppingCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //getting the Intent object that has started the Activity
        Intent intent = getIntent();

        //calling the method getSerializableExtra() to retrieve the Order object passed with
        //the intent. It should be casted to Object
        Order order = (Order) intent.getSerializableExtra("order");

        //setting the order's info text to a TextView
        binding.orderText.setText(order.toString());

        //setting the total price to a TextView
        binding.orderTotalText.setText(String.valueOf(order.getTotalPrice()));


    }
}