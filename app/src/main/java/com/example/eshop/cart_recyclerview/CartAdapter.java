package com.example.eshop.cart_recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eshop.R;
import com.example.eshop.store.OrderItem;
import com.example.eshop.store.Product;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {

    private List<OrderItem> orderItems;


    public CartAdapter(List<OrderItem> orderItems){
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(view, orderItems);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        OrderItem currentItem = orderItems.get(position);
        Product itemsProduct = currentItem.getProduct();

        holder.getOrderItemTotalPrice().setText(String.format("%.2f", currentItem.getTotalPrice()));
        holder.getDescriptionText().setText(itemsProduct.getDescription());
        holder.getQuantityText().setText(String.valueOf(currentItem.getQuantity()));
        holder.getPriceText().setText(String.valueOf(currentItem.getProduct().getPrice()));
    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }
}
