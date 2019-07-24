package com.example.android.androidui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String emailText;
    String subject = "Your order is Music Shop";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent newOrderIntent = getIntent();
        String userName = newOrderIntent.getStringExtra("userName");
        String goodsName = newOrderIntent.getStringExtra("goodsName");
        int quantity = newOrderIntent.getIntExtra("quantity", 0);
        double oderPrice = newOrderIntent.getDoubleExtra("oderPrice", 0);

        emailText = "Order is Music Shop:" + "\n" +
                "You name: " + userName + "\n" +
                "Good name: " + goodsName + "\n" +
                "Quantity: " + quantity + "\n" +
                "Price: " + oderPrice + " $";


        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText(emailText);

        ImageView goodImageView = findViewById(R.id.goodsImageView);

        switch (goodsName) {
            case "Гитара":
                goodImageView.setImageResource(R.drawable.guitar);
                break;
            case "Барабан":
                goodImageView.setImageResource(R.drawable.eletronica);
                break;
            case "Саксафон":
                goodImageView.setImageResource(R.drawable.saxophone);
                break;
            default:
                goodImageView.setImageResource(R.drawable.guitar);
                break;

        }
    }

    public void submitOrder(View view) {

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}


