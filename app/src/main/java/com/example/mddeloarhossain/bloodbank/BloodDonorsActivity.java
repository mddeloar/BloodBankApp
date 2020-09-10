package com.example.mddeloarhossain.bloodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class BloodDonorsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private  AdapterRegistrationForShow imageAdapter;
    private List<AdapterRegistration> uploadList;
    DatabaseReference databaseReference;
    //private ProgressBar progressBar;
    private FirebaseStorage firebaseStorage;
    ProgressDialog progressDialog;

    public static final String DONOR_ID = "com.example.mddeloarhossain.bloodbankm.donorid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_donors);

        progressDialog = new ProgressDialog(BloodDonorsActivity.this);
        progressDialog.setTitle("Hello World!");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        recyclerView = findViewById(R.id.recyclerImageViewId);
        //progressBar = findViewById(R.id.RecyclerprogressbarId);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        uploadList = new ArrayList<>();

        firebaseStorage = FirebaseStorage.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("donorswithdetails");
        //databaseReference = FirebaseDatabase.getInstance().getReference("donorsimages");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {


                uploadList.clear();

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    AdapterRegistration upload = dataSnapshot1.getValue(AdapterRegistration.class);
                    upload.setKey(dataSnapshot1.getKey());
                    uploadList.add(upload);

                }
                imageAdapter = new AdapterRegistrationForShow(BloodDonorsActivity.this, uploadList);
                recyclerView.setAdapter(imageAdapter);
                progressDialog.dismiss();

                imageAdapter.setOnItemClickListener(new AdapterRegistrationForShow.OnItemClickListener() {
                    @Override
                    public void OnItemClick(int position) {
                        String text = uploadList.get(position).getImageName();
                        Toast.makeText(getApplicationContext(),text+" is selected : "+position,Toast.LENGTH_SHORT).show();
                        Intent intent = (new Intent(getApplicationContext(), ProfileActivity.class));
                        AdapterRegistration selectedItem = uploadList.get(position);
                        final String key = selectedItem.getKey();
                        intent.putExtra(DONOR_ID, key);
                        Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
                        startActivity(intent);

                    }

                    @Override
                    public void onDoAnyTask(int position) {
                        Toast.makeText(getApplicationContext(),"onDoAnyTask is selected.",Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onDelete(int position) {
                        AdapterRegistration selectedItem = uploadList.get(position);
                        final String key = selectedItem.getKey();

                        StorageReference storageReference = firebaseStorage.getReferenceFromUrl(selectedItem.getImageUrl());
                        storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                databaseReference.child(key).removeValue();

                                Toast.makeText(getApplicationContext(),"Image is deleted successfully.",Toast.LENGTH_SHORT).show();

                            }
                        });

                        //Toast.makeText(getApplicationContext(),"onDelete is selected.",Toast.LENGTH_SHORT).show();

                    }
                });


                //progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(getApplicationContext(),"Error: "+databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                //progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu_layout,menu);

        MenuItem menuItem = menu.findItem(R.id.SerchViewId);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                imageAdapter.getFilter().filter(s);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

}
