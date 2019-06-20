package com.example.mddeloarhossain.bloodbank;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

public class FloatActionButton extends AppCompatActivity {

    private FloatingActionButton fab, fab1, fab2, fab3, fab4, fab5, fab6, fab7;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_action_button);

        fab = findViewById(R.id.FloatActionButtonId);
        fab1 = findViewById(R.id.FloatActionButton1Id);
        fab2 = findViewById(R.id.FloatActionButton2Id);
        fab3 = findViewById(R.id.FloatActionButton3Id);
        fab4 = findViewById(R.id.FloatActionButton4Id);
        fab5 = findViewById(R.id.FloatActionButton5Id);
        fab6 = findViewById(R.id.FloatActionButton6Id);
        fab7 = findViewById(R.id.FloatActionButton7Id);

        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);

        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateFab();
                /*Intent intent = (new Intent(getApplicationContext(), FullScreenActivity.class));
                startActivity(intent);*/
            }
        });
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateFab();
                Toast.makeText(getApplicationContext(), "Fab one is clicked", Toast.LENGTH_LONG).show();
                /*Intent intent = (new Intent(getApplicationContext(), FullScreenActivity.class));
                startActivity(intent);*/
            }
        });
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateFab();
                Toast.makeText(getApplicationContext(), "Fab two is clicked", Toast.LENGTH_LONG).show();
            }
        });
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateFab();
                Toast.makeText(getApplicationContext(), "Fab Three is clicked", Toast.LENGTH_LONG).show();
            }
        });
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateFab();
                Toast.makeText(getApplicationContext(), "Fab Four is clicked", Toast.LENGTH_LONG).show();
            }
        });
        fab5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                animateFab();
                Toast.makeText(getApplicationContext(), "Fab five is clicked", Toast.LENGTH_LONG).show();
            }
        });
    }


    private void animateFab()
    {
        if(isOpen)
        {
            fab.startAnimation(rotateForward);
            fab1.startAnimation(fabClose);
            fab2.startAnimation(fabClose);
            fab3.startAnimation(fabClose);
            fab4.startAnimation(fabClose);
            fab5.startAnimation(fabClose);
            fab6.startAnimation(fabClose);
            fab7.startAnimation(fabClose);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            fab4.setClickable(false);
            fab5.setClickable(false);
            fab6.setClickable(false);
            fab7.setClickable(false);
            isOpen=false;
        }
        else {
            fab.startAnimation(rotateBackward);
            fab1.startAnimation(fabOpen);
            fab2.startAnimation(fabOpen);
            fab3.startAnimation(fabOpen);
            fab4.startAnimation(fabOpen);
            fab5.startAnimation(fabOpen);
            fab6.startAnimation(fabOpen);
            fab7.startAnimation(fabOpen);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            fab4.setClickable(true);
            fab5.setClickable(true);
            fab6.setClickable(true);
            fab7.setClickable(true);
            isOpen=true;
        }
    }



}
