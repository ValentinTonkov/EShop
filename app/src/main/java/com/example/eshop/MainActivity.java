package com.example.eshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.eshop.databinding.ActivityMainBinding;
import com.example.eshop.store.Order;
import com.example.eshop.store.OrderItem;
import com.example.eshop.usbstick.NoSuchUsbStickException;
import com.example.eshop.usbstick.UsbStick;
import com.example.eshop.usbstick.UsbStickPrices;
import com.example.eshop.usbstick.UsbSticksRepository;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private UsbSticksRepository sticksRepository = new UsbSticksRepository();
    private ActivityMainBinding binding;

    private int selectedSize = UsbStick.GB_16;
    private int selectedColor = Color.BLUE;
    private int quantity = 1;
    private Order order = new Order();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //creating an instance of the binding class by calling static method inflate
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        //passing the root view to setContentView() to make it the active view on the screen
        setContentView(binding.getRoot());

        //creating usb sticks
        addUsbSticksToRepository();

        //updating TextViews of the interface with the default UsbStick selected
        updateQuantityText();
        updateTotalPrice();
        updateProductDescription();

        //adding listeners for the
        addListeners();
    }

    /**
     *  overriding onCreateOptionsMenu to specify the options menu for the Activity
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * overriding onOptionsItemSelected to handle the users selection of
     * an item from the menu
     * @param item the chosen menu item by the user
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //save the IDs of the items to variables
        final int profileId = R.id.profile_item;
        final int aboutId = R.id.about_item;
        final int shoppingCartId = R.id.shopping_cart_item;
        final int ordersId = R.id.orders_item;

        //using switch to detect which option is selected
        switch (item.getItemId()) {
            case profileId:
                Toast.makeText(getApplicationContext(), "Profile clicked", Toast.LENGTH_SHORT).show();
                return true;
            case aboutId:
                Toast.makeText(getApplicationContext(), "About clicked", Toast.LENGTH_SHORT).show();
                return true;

                //this case will be triggered when the user presses the Shoping cart item from the menu
            case shoppingCartId:

                //Creating an Intent object that will be used to start ShoppingCartActivity
                Intent intent = new Intent(getApplicationContext(), ShoppingCartActivity.class);

                //attaching data to the intent object
                intent.putExtra("order", order);

                //starting the activity by using the intent
                startActivity(intent);
                return true;

            case ordersId:
                Toast.makeText(getApplicationContext(), "Orders clicked", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * this method updates the quantity TextView in the interface
     * */
    private void updateQuantityText() {
        binding.quantityText.setText(String.valueOf(quantity));
    }

    /**
     * this method updates the total price TextView in the interface
     * */
    private void updateTotalPrice() {
        try {
            UsbStick currentStick = getCurrentUsbStick();
            Double totalPrice = currentStick.getPrice() * quantity;
            binding.totalPriceText.setText(String.format("%.2f", totalPrice));
        } catch (NoSuchUsbStickException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * updates products description TextView in the interface
     * */
    private void updateProductDescription() {
        try {
            UsbStick stick = getCurrentUsbStick();
            String description = stick.getDescription();

            binding.descriptionText.setText(description);
        } catch (NoSuchUsbStickException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return gets the current UsbStick object from the repository
     * */
    private UsbStick getCurrentUsbStick() throws NoSuchUsbStickException {
        return sticksRepository.getUsbStickBySizeAndColor(selectedSize, selectedColor);
    }

    /**
     * updates the TextViews and selectedSize
     * */
    private void updateInfoBySize(int size) {
        selectedSize = size;
        updateTotalPrice();
        updateProductDescription();
    }

    /**
     * OnClickListener for addButton
     * */
    private void addAddButtonListener() {
        binding.addButton.setOnClickListener(v -> {
            quantity++;
            updateQuantityText();
            updateTotalPrice();
        });
    }

    /**
     * OnClickListener for removeButton
     * */
    private void addRemoveButtonListener() {
        binding.removeButton.setOnClickListener(v -> {
            if (quantity > 0) {
                quantity--;
                updateQuantityText();
                updateTotalPrice();
            } else {
                Toast.makeText(getApplicationContext(), "Quantity must be greater than 0", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * This method adds OnCheckedChangeListener for the radio group. When the user chooses
     * a radio button, onCheckedChanged method is called
     * */
    private void addMemorySizeSelectionListener() {
        binding.memorySizeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                final int id16GB = R.id.radioButton16GB;
                final int id32GB = R.id.radioButton32GB;
                final int id64GB = R.id.radioButton64GB;

                //using switch to detect which option is chosen
                switch (checkedId) {
                    case id16GB:
                        updateInfoBySize(UsbStick.GB_16);
                        break;

                    case id32GB:
                        updateInfoBySize(UsbStick.GB_32);
                        break;

                    case id64GB:
                        updateInfoBySize(UsbStick.GB_64);
                        break;
                }
            }
        });
    }


    /**
     * This method adds OnCheckedStateChangeListener for the chip group. When the user
     * chooses a chip, onCheckedChanged method is called. In this case, the chipGroup has
     * an attribute app:singleSelection, so the checkedIds list will always have 1 item
     * */
    private void addColorSelectionListener() {
        binding.chipGroup.setOnCheckedStateChangeListener(new ChipGroup.OnCheckedStateChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {

                for (int id :
                        checkedIds) {
                    if (id == R.id.blackChip) {
                        binding.usbStickImage.setImageResource(R.drawable.usb_stick_black);
                        selectedColor = Color.BLACK;
                    } else if (id == R.id.blueChip) {
                        binding.usbStickImage.setImageResource(R.drawable.usb_stick_blue);
                        selectedColor = Color.BLUE;
                    }
                }
            }
        });
    }
/**
 * Adds all listeners
 * */
    private void addListeners() {
        addAddButtonListener();
        addRemoveButtonListener();
        addMemorySizeSelectionListener();
        addColorSelectionListener();
        addAddToCartButtonListener();
    }

    private void addAddToCartButtonListener() {
        binding.addToCartButton.setOnClickListener(v -> {
            try {
                OrderItem orderItem = new OrderItem(getCurrentUsbStick(), quantity);
                order.addOrderItem(orderItem);
                Toast.makeText(getApplicationContext(), "Product added", Toast.LENGTH_SHORT).show();

            } catch (NoSuchUsbStickException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * This method creates and adds UsbStick objects to UsbStickRepository
     * */
    private void addUsbSticksToRepository() {
        try {
            UsbStick usbStick16Black = new UsbStick(0, UsbStick.GB_16, UsbStickPrices.PRICE_16GB, "Black USB Stick, 16 GB", Color.BLACK);
            UsbStick usbStick16Blue = new UsbStick(1, UsbStick.GB_16, UsbStickPrices.PRICE_16GB, "Blue USB Stick, 16 GB", Color.BLUE);
            UsbStick usbStick32Black = new UsbStick(2, UsbStick.GB_32, UsbStickPrices.PRICE_32GB, "Black USB Stick, 32 GB", Color.BLACK);
            UsbStick usbStick32Blue = new UsbStick(3, UsbStick.GB_32, UsbStickPrices.PRICE_32GB, "Blue USB Stick, 32 GB", Color.BLUE);
            UsbStick usbStick64Black = new UsbStick(4, UsbStick.GB_64, UsbStickPrices.PRICE_64GB, "Black USB Stick, 64 GB", Color.BLACK);
            UsbStick usbStick64Blue = new UsbStick(5, UsbStick.GB_64, UsbStickPrices.PRICE_64GB, "Blue USB Stick, 64 GB", Color.BLUE);

            sticksRepository.addUsbStick(usbStick16Black);
            sticksRepository.addUsbStick(usbStick32Black);
            sticksRepository.addUsbStick(usbStick64Black);
            sticksRepository.addUsbStick(usbStick64Blue);
            sticksRepository.addUsbStick(usbStick32Blue);
            sticksRepository.addUsbStick(usbStick16Blue);

        } catch (NoSuchUsbStickException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}