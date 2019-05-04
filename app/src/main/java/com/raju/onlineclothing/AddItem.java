package com.raju.onlineclothing;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AddItem extends AppCompatActivity {

    private Button addItem;

    private EditText itemName;
    private EditText itemPrice;
    private EditText itemImageName;
    private EditText itemDescription;

    private String fileName = "items";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        addItem = findViewById(R.id.activity_add_item_addItem);
        itemName = findViewById(R.id.activity_add_item_itemName);
        itemPrice = findViewById(R.id.activity_add_item_itemPrice);
        itemImageName = findViewById(R.id.activity_add_item_itemImageName);
        itemDescription = findViewById(R.id.activity_add_item_itemDescription);

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Item item = new Item(itemName.getText().toString(), itemPrice.getText().toString(), itemImageName.getText().toString(), itemDescription.getText().toString());
                if(item.getItemName().isEmpty() || item.getItemPrice().isEmpty() || item.getItemImageName().isEmpty() || item.getItemDescription().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Empty values are not allowed", Toast.LENGTH_LONG).show();
                }
                else{
                    FileOutputStream fileOutputStream;
                    String fileContents;

                    fileContents = "\n" + item.getItemName() + "->" + item.getItemPrice() + "->" + item.getItemImageName() + "->" + item.getItemDescription();
                    try {
                        fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                        fileOutputStream.write(fileContents.getBytes());
                        fileOutputStream.close();
                        Toast.makeText(getApplicationContext(), "Add item successful", Toast.LENGTH_LONG).show();
                        resetValues();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void resetValues(){
        itemName.setText("");
        itemDescription.setText("");
        itemImageName.setText("");
        itemPrice.setText("");
    }

}
