package com.raju.onlineclothing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Dashboard extends AppCompatActivity {

    private Button logout;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        sharedPreferences = getApplicationContext().getSharedPreferences("users", Context.MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        logout.findViewById(R.id.activity_dashboard_logout);

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
    }
}
