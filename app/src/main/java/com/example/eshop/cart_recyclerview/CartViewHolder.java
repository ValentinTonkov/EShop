package com.example.eshop.cart_recyclerview;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eshop.R;
import com.example.eshop.store.OrderItem;

import java.util.List;

public class CartViewHolder extends RecyclerView.ViewHolder {

    private TextView quantityText;
    private TextView descriptionText;
    private TextView priceText;
    private TextView orderItemTotalPrice;

    public CartViewHolder(@NonNull View itemView, List<OrderItem> orderItems) {
        super(itemView);
        quantityText = itemView.findViewById(R.id.qtyText);
        descriptionText = itemView.findViewById(R.id.dscrText);
        orderItemTotalPrice = itemView.findViewById(R.id.orderItemPriceText);
        priceText = itemView.findViewById(R.id.singlePriceText);

        ImageButton addButton = itemView.findViewById(R.id.addBtn);
        ImageButton removeButton = itemView.findViewById(R.id.rmvBtn);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OrderItem currentItem = orderItems.get(getAdapterPosition());
                currentItem.addQuantity(1);
                quantityText.setText(String.valueOf(currentItem.getQuantity()));
                orderItemTotalPrice.setText(String.format("%.2f", currentItem.getTotalPrice()));

            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderItem currentItem = orderItems.get(getAdapterPosition());
                currentItem.removeQuantity(1);
                quantityText.setText(String.valueOf(currentItem.getQuantity()));
                orderItemTotalPrice.setText(String.format("%.2f", currentItem.getTotalPrice()));
            }
        });
    }

    public TextView getQuantityText() {
        return quantityText;
    }

    public TextView getPriceText() {
        return priceText;
    }

    public TextView getOrderItemTotalPrice() {
        return orderItemTotalPrice;
    }

    public TextView getDescriptionText() {
        return descriptionText;
    }
}
