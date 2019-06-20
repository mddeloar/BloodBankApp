package com.example.mddeloarhossain.bloodbank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class FullScreenActivity extends AppCompatActivity {

    Switch aSwitch;
    TextView textView;
    private Button b1, b2, b3, b4, b5, b6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Hiding the title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //hiding the action bar
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);

        textView = findViewById(R.id.TextViewId);
        aSwitch = findViewById(R.id.SwitchId);

        b1 = findViewById(R.id.SingleProfileId);
        b2 = findViewById(R.id.SingleProfile2Id);
        b3 = findViewById(R.id.CircleMenuId);
        b4 = findViewById(R.id.LogInId);
        b5 = findViewById(R.id.gridViewId);
        b6 = findViewById(R.id.registrationId);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    textView.setText("ON");
                }else {
                    textView.setText("OFF");
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = (new Intent(getApplicationContext(), SingleProfile2Activity.class));
                Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = (new Intent(getApplicationContext(), SingleProfile3Activity.class));
                Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = (new Intent(getApplicationContext(), CircleMenuActivity.class));
                //Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = (new Intent(getApplicationContext(), LogInActivity.class));
                //Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = (new Intent(getApplicationContext(), GridViewActivity.class));
                //Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = (new Intent(getApplicationContext(), RegistrationActivity.class));
                //Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });


    }
}
