package com.example.eshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.eshop.databinding.ActivityUserInfoBinding;
import com.example.eshop.store.Order;

public class UserInfoActivity extends AppCompatActivity {
    private ActivityUserInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        Order order = (Order) intent.getSerializableExtra("order");
        binding.finishOrderTotalPrice.setText(order.getTotalPrice().toString());

        binding.submitButton.setOnClickListener(v -> {
            String orderInfo = generateOrderString(order);
            String[] addresses = {"example@gmail.com", "ex2@gmail.com"};
            String subject = "New order 133131";
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("*/*");
            i.putExtra(Intent.EXTRA_EMAIL, addresses);
            i.putExtra(Intent.EXTRA_SUBJECT, subject);
            i.putExtra(Intent.EXTRA_TEXT, orderInfo);
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            }
        });


    }

    private String generateOrderString(Order order){
        String clientName = binding.personName.getText().toString();
        String email = binding.emailAddress.getText().toString();
        String phone = binding.phoneNumber.getText().toString();
        String address = binding.shippingAddress.getText().toString();
        String courier;
        if (binding.radioGroup.getCheckedRadioButtonId() == binding.radioButtonEcont.getId()){
            courier = "Econt";
        } else {
            courier = "Speedy";
        }
        StringBuilder builder = new StringBuilder();
        builder.append("Name: ");
        builder.append(clientName);
        builder.append("\nemail: ");
        builder.append(email);
        builder.append("\nphone number: ");
        builder.append(phone);
        builder.append("\naddress: ");
        builder.append(address);
        builder.append("\ncourier: ");
        builder.append(courier);
        builder.append("\n\nOrder info:\n");
        builder.append(order.toString());
        return builder.toString();
    }
}