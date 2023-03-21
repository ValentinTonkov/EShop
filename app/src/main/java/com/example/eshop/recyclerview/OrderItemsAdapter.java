package com.example.eshop.recyclerview;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eshop.R;
import com.example.eshop.store.Order;
import com.example.eshop.store.OrderItem;

import java.util.List;

public class OrderItemsAdapter extends RecyclerView.Adapter<OrderItemsViewHolder> {

    private final List<OrderItem> orderItems;

    public OrderItemsAdapter (Order order){
        orderItems = order.getOrderItems();

    }

    @NonNull
    @Override
    public OrderItemsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item_row, parent, false);
        return new OrderItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderItemsViewHolder holder, int position) {
       holder.getOrderItemText().setText(orderItems.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }
}