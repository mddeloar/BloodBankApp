package com.example.mddeloarhossain.bloodbank;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

public class CircleMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_menu);


        CircleMenu circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.ic_add, R.drawable.ic_cancel)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.profile1)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.hospital)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.deloars)
                .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.note3)
                .addSubMenu(Color.parseColor("#FF6A00"), R.drawable.lovely_time)
                .addSubMenu(Color.parseColor("#FF4BB6EF"), R.drawable.family_time)
                .addSubMenu(Color.parseColor("#dcf408"), R.drawable.message1)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {
                        Intent intent;
                        switch (index) {
                            case 0:
                                intent = (new Intent(getApplicationContext(), SingleProfileActivity.class));
                                //Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                break;
                            case 1:
                                intent = (new Intent(getApplicationContext(), SingleProfile2Activity.class));
                                //Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            case 6:
                                break;
                            default:
                                Toast.makeText(getApplicationContext(), "Notihing", Toast.LENGTH_SHORT).show();
                        }
                    }

                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {}

            @Override
            public void onMenuClosed() {}

        });
    }
}
