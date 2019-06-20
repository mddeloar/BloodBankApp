package com.example.mddeloarhossain.bloodbank;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    //MyDatabaseHelper mydatabaseHelper;
    private EditText usernameEdittext, passwordEdittext;
    private CardView logUpButton, LogInButton ;
    private TextView textView;
    private static String call="1";
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        this.setTitle("Sign Up");

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressbarId);
        usernameEdittext = findViewById(R.id.usernameId);
        passwordEdittext = findViewById(R.id.passwordId);
        logUpButton = findViewById(R.id.DonorLogUpCardViewId);
        textView = findViewById(R.id.textViewId);
        LogInButton = findViewById(R.id.DonorRegisterButtonCardViewId);
        //AddDetails = findViewById(R.id.DetailsDonorRegisterButtonCardViewId);

        logUpButton.setOnClickListener(this);
        LogInButton.setOnClickListener(this);
        //AddDetails.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.DonorLogUpCardViewId:
                userRegister();
                break;
            case R.id.DonorRegisterButtonCardViewId:
                Intent intent = (new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Welcome donor", Toast.LENGTH_SHORT).show();
                startActivity(intent);

                break;




        }

    }

    private void userRegister() {
        String email=usernameEdittext.getText().toString().trim();
        String password=passwordEdittext.getText().toString().trim();


        if(email.isEmpty())
        {
            usernameEdittext.setError("Enter an Email address");
            usernameEdittext.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            usernameEdittext.setError("Enter an valid Email address");
            usernameEdittext.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            passwordEdittext.setError("Enter an Password");
            passwordEdittext.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            passwordEdittext.setError("Minimum Length of Password should be 6");
            passwordEdittext.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = (new Intent(getApplicationContext(), DonorRegistration.class));
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //Toast.makeText(getApplicationContext(), "Welcome donor", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    //Toast.makeText(getApplicationContext(), "Register is successful.", Toast.LENGTH_SHORT).show();

                } else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(), "Username is already registered.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "Error:"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(getApplicationContext(), "Register is not successful.", Toast.LENGTH_SHORT).show();


                }
            }
        });

    }

}

