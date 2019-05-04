package com.raju.onlineclothing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class LoginAndRegistration extends AppCompatActivity {

    private TabAdapter tabAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_and_registration);

        viewPager = findViewById(R.id.activity_login_and_registration_viewPager);
        tabLayout = findViewById(R.id.activity_login_and_registration_tabLayout);

        tabAdapter = new TabAdapter(getSupportFragmentManager());
        tabAdapter.addFragment(new LoginFragment(), "Login");
        tabAdapter.addFragment(new RegistrationFragment(), "Register");

        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);

        sharedPreferences = getApplicationContext().getSharedPreferences("users", Context.MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        setupLogin();
        setupRegistration();
    }

    private void setupLogin(){
        final EditText username = findViewById(R.id.fragment_login_username);
        final EditText password = findViewById(R.id.fragment_login_password);
        Button login = findViewById(R.id.fragment_login_loginBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty() || username.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Empty username/password", Toast.LENGTH_LONG).show();
                }
                else{
                    String savedUsername = sharedPreferences.getString("username", "");
                    String savedPassword = sharedPreferences.getString("password", "");
                    if(savedUsername == username.getText().toString() && savedPassword == password.getText().toString()){
                        sharedPreferencesEditor.putBoolean("isLoggedIn", true);
                        Intent intent = new Intent(getApplicationContext(), Dashboard.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Invalid username/password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }

    private void setupRegistration(){
        final EditText username = findViewById(R.id.fragment_registration_username);
        final EditText password = findViewById(R.id.fragment_registration_password);
        final EditText email = findViewById(R.id.fragment_registration_email);
        final EditText address = findViewById(R.id.fragment_registration_address);
        final Button register = findViewById(R.id.fragment_registration_registerBtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty() || email.getText().toString().isEmpty() || address.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Empty credentials provided", Toast.LENGTH_LONG).show();
                }
                else{
                    sharedPreferencesEditor.putString("username", username.getText().toString());
                    sharedPreferencesEditor.putString("password", password.getText().toString());
                    sharedPreferencesEditor.apply();
                }
            }
        });
    }
}
