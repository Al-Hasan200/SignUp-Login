package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.signuplogin.Database.MyDBHelper;

public class MainActivity extends AppCompatActivity {

    //variable declaring
    EditText loginUsername, loginPassword;
    Button login, signUp;
    MyDBHelper myDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //find id
        loginUsername = findViewById(R.id.loginUsername);
        loginPassword = findViewById(R.id.loginPassword);
        login = findViewById(R.id.login);
        signUp = findViewById(R.id.signUp);

        //database write
        myDBHelper = new MyDBHelper(this);
        SQLiteDatabase sqLiteDatabase = myDBHelper.getWritableDatabase();

        //login code here
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //editText value get
                String username = loginUsername.getText().toString();
                loginUsername.getText().clear();
                String password = loginPassword.getText().toString();
                loginPassword.getText().clear();

                boolean result = myDBHelper.login(username, password);
                if (result == true){
                    startActivity(new Intent(MainActivity.this, MainActivity3.class));
                }else {
                    Toast.makeText(MainActivity.this, "Username or Password did not match...", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //signup activity open
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });
    }
}