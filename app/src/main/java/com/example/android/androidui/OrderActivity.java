package com.example.android.androidui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity {

    String emailText;
    double orderPriseSale;
    double sale = 0.7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent newOrderIntent = getIntent();
        String userName = newOrderIntent.getStringExtra("userName");
        String goodsName = newOrderIntent.getStringExtra("goodsName");
        int quantity = newOrderIntent.getIntExtra("quantity", 0);
        double orderPrice = newOrderIntent.getDoubleExtra("orderPrice", 0);


        if (orderPrice >= 4000.0) {
            orderPriseSale = orderPrice * sale;
            Toast toastSale = Toast.makeText(getApplicationContext(), "You have sale - 30%", Toast.LENGTH_LONG);
            toastSale.setGravity(Gravity.CENTER, 0, 0);
            toastSale.show();
        } else {
            orderPriseSale = orderPrice;
            Toast toastNoSale = Toast.makeText(getApplicationContext(), "When ordering more than $ 4000, you get a 30% discount", Toast.LENGTH_LONG);
            toastNoSale.show();
        }


        emailText = "Order is Music Shop:" + "\n" +
                "You name: " + userName + "\n" +
                "Good name: " + goodsName + "\n" +
                "Quantity: " + quantity + "\n" +
                "Price: " + orderPriseSale + " $";


        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText(emailText);

        ImageView goodImageView = findViewById(R.id.goodsImageView);

        switch (goodsName) {
            case "Guitar":
                goodImageView.setImageResource(R.drawable.guitar);
                break;
            case "Drums":
                goodImageView.setImageResource(R.drawable.eletronica);
                break;
            case "Saxophone":
                goodImageView.setImageResource(R.drawable.saxophone);
                break;
            default:
                goodImageView.setImageResource(R.drawable.guitar);
                break;

        }
    }

    public void submitOrder(View view) {

        String subject = "Your order is Music Shop";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}


