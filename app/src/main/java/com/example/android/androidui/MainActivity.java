package com.example.android.androidui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap hashMapMusic;
    String goodsName;
    double price;
    EditText userNameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSpinner();
        createMap();
        userNameEditText = findViewById(R.id.editText);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("quantity", quantity);
        outState.putDouble("price", price);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        quantity = savedInstanceState.getInt("quantity");
        price = savedInstanceState.getDouble("price");
    }

    void createSpinner() {
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("Guitar");
        spinnerArrayList.add("Drums");
        spinnerArrayList.add("Saxophone");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinner.setAdapter(spinnerAdapter);
    }

    void createMap() {
        hashMapMusic = new HashMap();
        hashMapMusic.put("Guitar", 500.0);
        hashMapMusic.put("Drums", 1000.0);
        hashMapMusic.put("Saxophone", 1500.0);
    }

    public void plusQuantity(View view) {
        if (quantity < 10) {
            quantity = quantity + 1;
        }
        TextView addQuantity = findViewById(R.id.quantityDisplay);
        addQuantity.setText("" + quantity);
        TextView spinnerList = findViewById(R.id.totalPrice);
        spinnerList.setText(" " + quantity * price + "$");
    }

    public void decreaseQuantity(View view) {
        quantity = quantity - 1;
        if (quantity < 0) {
            quantity = 0;
        }
        TextView addQuantity = findViewById(R.id.quantityDisplay);
        addQuantity.setText("" + quantity);
        TextView spinnerList = findViewById(R.id.totalPrice);
        spinnerList.setText(" " + quantity * price + "$");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) hashMapMusic.get(goodsName);
        TextView spinnerList = findViewById(R.id.totalPrice);
        spinnerList.setText(" " + quantity * price + "$");


        ImageView goodsImageView = findViewById(R.id.goodImageView);

//        if (goodsName.equals("Гитара")) {
//            goodsImageView.setImageResource(R.drawable.guitar);
//        } else if (goodsName.equals("Барабан")) {
//            goodsImageView.setImageResource(R.drawable.eletronica);
//        } else if (goodsName.equals("Саксафон")) {
//            goodsImageView.setImageResource(R.drawable.saxophone);
//        }
//        switch - (переключатель) альтернатива (if (esle if)) для метода с большим количеством вариантов

        switch (goodsName) {
            case "Guitar":
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
            case "Drums":
                goodsImageView.setImageResource(R.drawable.eletronica);
                break;
            case "Saxophone":
                goodsImageView.setImageResource(R.drawable.saxophone);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.orderPrice = quantity * price;

        //  Проверка в лого
        //  Log.e("printUserName", "" + order.userName + order.goodsName + order.quantity + order.oderPrice);

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userName", order.userName);
        orderIntent.putExtra("goodsName", order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("orderPrice", order.orderPrice);
        startActivity(orderIntent);
    }
}