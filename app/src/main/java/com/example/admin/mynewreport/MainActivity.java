package com.example.admin.mynewreport;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSignIn;
    EditText editText_username,editText_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_username=(EditText)findViewById(R.id.editTextUserNameToLogin);
        editText_password=(EditText)findViewById(R.id.editTextPasswordToLogin);

        btnSignIn=(Button)findViewById(R.id.buttonSignIN);
        btnSignIn.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        String password = editText_password.getText().toString().trim();
        if (editText_username.getText().toString().equalsIgnoreCase("Lenyalo")) {
            System.out.println("Username correct");
            if (password.equalsIgnoreCase("1234")) {
                startActivity(new Intent(this, StudentInformation.class));
                editText_password.setText("");
                editText_username.setText("");
            } else {
                Toast.makeText(this, "Wrong Password", Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, "Wrong Username", Toast.LENGTH_LONG).show();
        }
    }
}


