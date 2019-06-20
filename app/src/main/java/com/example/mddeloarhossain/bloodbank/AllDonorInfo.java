package com.example.mddeloarhossain.bloodbank;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AllDonorInfo extends AppCompatActivity {

    private ListView listView;
    private List<Donor> donorList;
    private CustomAdapter1 customAdapter;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_donor_info);



        databaseReference = FirebaseDatabase.getInstance().getReference("donors");
        //Toast.makeText(getApplicationContext(), "In DetailsDonor Activity", Toast.LENGTH_SHORT).show();

        donorList = new ArrayList<>();
        customAdapter = new CustomAdapter1(AllDonorInfo.this,donorList);

        listView = findViewById(R.id.ListViewId);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String selectedValue = adapterView.getItemAtPosition(i).toString();
                Intent intent = (new Intent(AllDonorInfo.this, SingleProfileActivity.class));
                Toast.makeText(AllDonorInfo.this, "Donor's Details", Toast.LENGTH_SHORT).show();
                //intent.putExtra("tag",IdNumber);
                intent.putExtra("tag",selectedValue);
                startActivity(intent);



            }
        });



    }

    @Override
    protected void onStart() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                donorList.clear();
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren())
                {
                    Donor donor= dataSnapshot1.getValue(Donor.class);
                    donorList.add(donor);
                }

                listView.setAdapter(customAdapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
}
