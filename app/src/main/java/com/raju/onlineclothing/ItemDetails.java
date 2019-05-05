package com.raju.onlineclothing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ItemDetails extends AppCompatActivity {

    TextView itemName;
    TextView itemPrice;
    ImageView itemImageName;
    TextView itemDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        itemName = findViewById(R.id.activity_item_details_itemName);
        itemPrice = findViewById(R.id.activity_item_details_itemPrice);
        itemImageName = findViewById(R.id.activity_item_details_itemImageName);
        itemDescription = findViewById(R.id.activity_item_details_itemDescription);

        Intent intent = getIntent();
        itemName.setText(intent.getStringExtra("itemName"));
        itemPrice.setText(intent.getStringExtra("itemPrice"));

        String imageName = "@drawable/" + intent.getStringExtra("itemImageName");
        int imageResource = getApplicationContext().getResources().getIdentifier(imageName, null, getApplicationContext().getPackageName());
        itemImageName.setImageResource(imageResource);

        itemDescription.setText(intent.getStringExtra("itemDescription"));
    }
}
