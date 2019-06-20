package com.example.mddeloarhossain.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity {
    private Button logInButton, registrationButton;
    private EditText userName, passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        islogin();

        //Hiding the title bar
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hiding the action bar
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);



        logInButton = findViewById(R.id.logInId);
        registrationButton = findViewById(R.id.registrationId);
        userName = findViewById(R.id.usernameLogInId);
        passWord = findViewById(R.id.passwordLogInId);

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = userName.getText().toString();
                String password = passWord.getText().toString();

                if(username.equals("") || password.equals("")){
                    Toast.makeText(getApplicationContext(), "Input Username and/or password", Toast.LENGTH_SHORT).show();
                }else {
                    SharedPreferences sharedPreferences = getSharedPreferences("userInfoo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("usernameKey",username);
                    editor.putString("passwordKey",password);
                    editor.commit();


                    Intent intent = (new Intent(getApplicationContext(), MainActivity.class));
                    Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                }


            }
        });
        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = (new Intent(getApplicationContext(), RegistrationActivity.class));
                Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }

    private void islogin(){

        SharedPreferences sharedPreferences = getSharedPreferences("userInfoo", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("usernameKey") && sharedPreferences.contains("passwordKey")){

            Intent intent = (new Intent(getApplicationContext(), MainActivity.class));
            Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }



    }
}
