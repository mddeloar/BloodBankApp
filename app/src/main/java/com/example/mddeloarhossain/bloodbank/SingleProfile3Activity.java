package com.example.mddeloarhossain.bloodbank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class SingleProfile3Activity extends AppCompatActivity {

    Switch aSwitch;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_profile3);

        textView = findViewById(R.id.TextViewId);
        aSwitch = findViewById(R.id.SwitchId);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b){
                    textView.setText("Can't donate now !");
                }else {
                    textView.setText("Ready to donate");
                }
            }
        });
    }
}
