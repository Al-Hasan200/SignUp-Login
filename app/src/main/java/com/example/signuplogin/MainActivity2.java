package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.signuplogin.Database.MyDBHelper;

public class MainActivity2 extends AppCompatActivity {

    //variable declaring
    EditText signupName, signupEmail, signupUsername, signupPassword;
    Button signup;
    MyDBHelper myDBHelper;
    UserInfo userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //find id
        signupName = findViewById(R.id.signupName);
        signupEmail = findViewById(R.id.signupEmail);
        signupUsername = findViewById(R.id.signupUsername);
        signupPassword = findViewById(R.id.signupPassword);
        signup = findViewById(R.id.signup);

        //userinfo class object create
        userInfo = new UserInfo();

        //database write
        myDBHelper = new MyDBHelper(this);
        SQLiteDatabase sqLiteDatabase = myDBHelper.getWritableDatabase();

        //insert data in database
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //editText value get
                String name = signupName.getText().toString();
                signupName.getText().clear();
                String email = signupEmail.getText().toString();
                signupEmail.getText().clear();
                String username = signupUsername.getText().toString();
                signupUsername.getText().clear();
                String password = signupPassword.getText().toString();
                signupPassword.getText().clear();

                //set userinfo
                userInfo.setName(name);
                userInfo.setEmail(email);
                userInfo.setUsername(username);
                userInfo.setPassword(password);

                long rowID = myDBHelper.insertData(userInfo);
                if(rowID == -1){
                    Toast.makeText(MainActivity2.this, "SignUp not successful...", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity2.this, "SignUp successfully...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}