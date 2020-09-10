package com.example.mddeloarhossain.bloodbank;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class ProfileActivity extends AppCompatActivity {

    private TextView DonateTextView, TutionTextView, name,city,location,bloodgroup,donationstatus,gender,tutionstatus,email,password,age,contactnumber;

    DatabaseReference databaseReference;
    //private Firebase firebase;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        databaseReference = FirebaseDatabase.getInstance().getReference("donorswithdetails").child(intent.getStringExtra(BloodDonorsActivity.DONOR_ID));
        //databaseReference = FirebaseDatabase.getInstance().getReference("donorswithdetails").child("-LhW8qZwDKJHH1Ve-08w").child("name");
        //databaseReference =new Firebase("https://bloodbank-9ba46.firebaseio.com/donorswithdetails/-LhW8qZwDKJHH1Ve-08w");
        name = findViewById(R.id.nameTextViewId);
        city = findViewById(R.id.districtTextViewId);
        location = findViewById(R.id.areaTextViewId);
        bloodgroup = findViewById(R.id.bloodGroupTextViewId);
        age = findViewById(R.id.ageTextViewId);
        contactnumber = findViewById(R.id.contactNoTextViewId);
        email = findViewById(R.id.emailTextViewId);
        donationstatus = findViewById(R.id.DonationStatusTextViewId);
        gender = findViewById(R.id.genderTextViewId);

        //name.setText(intent.getStringExtra(BloodDonorsActivity.DONOR_ID));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name11 = dataSnapshot.child("name").getValue().toString();
                String city11 = dataSnapshot.child("city").getValue().toString();
                String location11 = dataSnapshot.child("location").getValue().toString();
                String bloodgroup11 = dataSnapshot.child("bloodgroup").getValue().toString();
                String age11 = dataSnapshot.child("age").getValue().toString();
                String contactnumber11 = dataSnapshot.child("contactnumber").getValue().toString();
                String donationstatus11 = dataSnapshot.child("inTheList").getValue().toString();
                String gender11 = dataSnapshot.child("gender").getValue().toString();
                //name.setText(name11);

                name.setText(name11);
                city.setText(city11);
                location.setText(location11);
                bloodgroup.setText(bloodgroup11);
                age.setText(age11);
                contactnumber.setText(contactnumber11);
                donationstatus.setText(donationstatus11);
                gender.setText(gender11);

                /*Map<String, String> map = dataSnapshot.getValue(Map.class);

                String name11 = map.get("name");
                String city11 = map.get("city");
                String location11 = map.get("location");
                String bloodgroup11 = map.get("bloodgroup");
                String age11 = map.get("age");
                String contactnumber11 = map.get("contactnumber");
                String donationstatus11 = map.get("donationstatus");
                String gender11 = map.get("gender");

                Log.v("E_VALUE", "Name: "+name11);*/

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
