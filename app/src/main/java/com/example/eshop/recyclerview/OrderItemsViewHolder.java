package com.example.eshop.recyclerview;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eshop.R;

public class OrderItemsViewHolder extends RecyclerView.ViewHolder {
    private TextView orderItemText;

    public OrderItemsViewHolder(@NonNull View itemView) {
        super(itemView);
        orderItemText = itemView.findViewById(R.id.orderItemText);
    }

    public TextView getOrderItemText(){
        return orderItemText;
    }
}
