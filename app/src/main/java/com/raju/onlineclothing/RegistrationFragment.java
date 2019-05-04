package com.raju.onlineclothing;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrationFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private android.content.SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        sharedPreferences = getActivity().getSharedPreferences("users", Context.MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        View view = inflater.inflate(R.layout.fragment_registration, container, false);

        final EditText username = view.findViewById(R.id.fragment_registration_username);
        final EditText password = view.findViewById(R.id.fragment_registration_password);
        final EditText email = view.findViewById(R.id.fragment_registration_email);
        final EditText address = view.findViewById(R.id.fragment_registration_address);
        final Button register = view.findViewById(R.id.fragment_registration_registerBtn);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty() || email.getText().toString().isEmpty() || address.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "Empty credentials provided", Toast.LENGTH_LONG).show();
                }
                else{
                    sharedPreferencesEditor.putString("username", username.getText().toString());
                    sharedPreferencesEditor.putString("password", password.getText().toString());
                    sharedPreferencesEditor.apply();
                    Toast.makeText(getActivity(), "Registration successful", Toast.LENGTH_LONG).show();
                }
            }
        });

        return view;
    }
}
