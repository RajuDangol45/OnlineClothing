package com.raju.onlineclothing;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddItem extends AppCompatActivity {

    private Button addItem;

    private EditText itemName;
    private EditText itemPrice;
    private EditText itemImageName;
    private EditText itemDescription;

    private String fileName = "items.txt";

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
                if (item.getItemName().isEmpty() || item.getItemPrice().isEmpty() || item.getItemImageName().isEmpty() || item.getItemDescription().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Empty values are not allowed", Toast.LENGTH_LONG).show();
                } else {
                    FileOutputStream fileOutputStream;
                    String fileContents = "";

                    try {
                        FileInputStream fileInputStream = getApplicationContext().openFileInput("items.txt");
                        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        String line;
                        String line1 = "";
                        while ((line = bufferedReader.readLine()) != null) {
                            if (!line.isEmpty()) {
                                line1 = line1 + line + "\n";
                                fileContents = line + "\n" + item.getItemName() + "->" + item.getItemPrice() + "->" + item.getItemImageName() + "->" + item.getItemDescription();
                            }
                        }
                        if (line1 != null) {
                            fileContents = line1 + item.getItemName() + "->" + item.getItemPrice() + "->" + item.getItemImageName() + "->" + item.getItemDescription();
                        } else {
                            fileContents = item.getItemName() + "->" + item.getItemPrice() + "->" + item.getItemImageName() + "->" + item.getItemDescription();
                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                        fileOutputStream.write(fileContents.getBytes());
                        fileOutputStream.close();
                        Toast.makeText(getApplicationContext(), "Add item successful", Toast.LENGTH_LONG).show();
                        resetValues();
                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        startActivity(intent);
                        finish();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void resetValues() {
        itemName.setText("");
        itemDescription.setText("");
        itemImageName.setText("");
        itemPrice.setText("");
    }

}
