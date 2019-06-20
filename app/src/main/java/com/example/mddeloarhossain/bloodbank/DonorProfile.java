package com.example.mddeloarhossain.bloodbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DonorProfile extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private CardView LoadDonorInfo, ChangePassword, DeleteAccount;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_profile);

        mAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);
        //LoadDonorInfo = findViewById(R.id.DonorInfoId);
        ChangePassword = findViewById(R.id.ChangePasswordId);
        DeleteAccount = findViewById(R.id.DeleteAccountId);

        /*LoadDonorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = (new Intent(getApplicationContext(), DonorDetails.class));
                //Toast.makeText(getApplicationContext(), "Successfully Sign Out", Toast.LENGTH_SHORT).show();
                //startActivity(intent);
            }
        });*/

        ChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = (new Intent(getApplicationContext(), ChangePasswordActivity.class));
                //Toast.makeText(getApplicationContext(), "Successfully Sign Out", Toast.LENGTH_SHORT).show();
                //startActivity(intent);
            }
        });
        DeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //finish();
                //Intent intent = (new Intent(getApplicationContext(), MainActivity.class));
                //Toast.makeText(getApplicationContext(), "Successfully Sign Out", Toast.LENGTH_SHORT).show();
                //startActivity(intent);
                deleteAccount(view);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId()==R.id.SignOutMenuItemId)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = (new Intent(getApplicationContext(), MainActivity.class));
            Toast.makeText(getApplicationContext(), "Successfully Sign Out", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
    public void deleteAccount(View v)
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){

            dialog.setMessage("Deleting your account, please wait !!!");
            dialog.show();

            user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        dialog.dismiss();
                        finish();
                        Toast.makeText(getApplicationContext(), "Your account is deleted", Toast.LENGTH_SHORT).show();
                        Intent intent = (new Intent(getApplicationContext(), MainActivity.class));
                        //Toast.makeText(getApplicationContext(), "Successfully Sign Out", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }else {
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Account could not be deleted", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }
}
