package com.raju.onlineclothing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private Button logout;
    private Button addItem;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewManager;
    private ArrayList<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sharedPreferences = getApplicationContext().getSharedPreferences("users", Context.MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        logout = findViewById(R.id.activity_dashboard_logout);
        addItem = findViewById(R.id.activity_dashboard_addItem);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferencesEditor.putBoolean("isLoggedIn", false);
                sharedPreferencesEditor.apply();
                Intent intent = new Intent(getApplicationContext(), LoginAndRegistration.class);
                startActivity(intent);
                finish();
            }
        });

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddItem.class);
                startActivity(intent);
            }
        });

        recyclerView = findViewById(R.id.activity_dashboard_recyclerView);
        recyclerView.setHasFixedSize(false);

        recyclerViewManager = new GridLayoutManager(getApplicationContext(), 3);
        recyclerView.setLayoutManager(recyclerViewManager);

        setItems();

        recyclerViewAdapter = new MyViewAdapter(items);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void setItems(){
        try {
            FileInputStream fileInputStream = getApplicationContext().openFileInput("items.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int index = 0;
                Item item = new Item();
                String value = "";
                for(int i=0; i< line.length(); i++){
                    char c = line.charAt(i);
                    if(c == '-' && line.charAt(i+1) == '>'){
                        if(index == 0){
                            item.setItemName(value);
                        }
                        else if (index == 1){
                            item.setItemPrice(value);
                        }
                        else if (index == 2){
                            item.setItemImageName(value);
                        }
                        else{
                            item.setItemDescription(value);
                        }
                        index += 1;
                        i++;
                        value = "";
                    }
                    else{
                        value = value + c;
                    }
                }
                items.add(item);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
