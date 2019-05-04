package com.raju.onlineclothing;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sharedPreferences = getActivity().getSharedPreferences("users", Context.MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        final View view =  inflater.inflate(R.layout.fragment_login, container, false);

        final EditText username = view.findViewById(R.id.fragment_login_username);
        final EditText password = view.findViewById(R.id.fragment_login_password);
        Button login = view.findViewById(R.id.fragment_login_loginBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty() || username.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Empty username/password", Toast.LENGTH_LONG).show();
                }
                else{
                    String savedUsername = sharedPreferences.getString("username", "");
                    String savedPassword = sharedPreferences.getString("password", "");
                    if(savedUsername == username.getText().toString() && savedPassword == password.getText().toString()){
                        sharedPreferencesEditor.putBoolean("isLoggedIn", true);
                        Intent intent = new Intent(getActivity(), Dashboard.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(getActivity(), "Invalid username/password", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        return view;
    }
}
