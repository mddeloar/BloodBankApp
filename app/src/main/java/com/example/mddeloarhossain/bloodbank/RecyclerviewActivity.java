package com.example.mddeloarhossain.bloodbank;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RecyclerviewActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    ArrayList<DonorR> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        mDatabase = FirebaseDatabase.getInstance().getReference("donors");
        mDatabase.keepSynced(true);

        recyclerView = findViewById(R.id.recyclerViewId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mDatabase !=null){
            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        list = new ArrayList<>();
                        for (DataSnapshot ds : dataSnapshot.getChildren()){
                            list.add(ds.getValue(DonorR.class));
                        }
                        AdapterRecycler adapterRecycler = new AdapterRecycler(list);
                        recyclerView.setAdapter(adapterRecycler);
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(getApplicationContext(),databaseError.getMessage(),Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
    /* @Override
    protected void onStart(){
        super.onStart();
        FirebaseRecyclerAdapter<DonorR,BlogViewHolder>FirebaseRecyclerAdapter = new FirebaseRecyclerAdapter<DonorR,BlogViewHolder>
                (DonorR.class,R.layout.recycler_item,BlogViewHolder.class,mDatabase){
            @Override
            protected void populateViewHolder

        }
    }*/
}
