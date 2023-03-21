package com.example.eshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.eshop.databinding.ActivityShoppingCartBinding;
import com.example.eshop.recyclerview.OrderItemsAdapter;
import com.example.eshop.store.Order;

public class ShoppingCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityShoppingCartBinding binding = ActivityShoppingCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("order");
        Order order = (Order) bundle.getSerializable("order");
        OrderItemsAdapter adapter = new OrderItemsAdapter(order);
        Intent i = new Intent();

        binding.ordersRecyclerView.setAdapter(adapter);
        binding.ordersRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }
}