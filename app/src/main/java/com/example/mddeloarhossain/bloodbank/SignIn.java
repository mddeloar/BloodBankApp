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



public class SignIn extends AppCompatActivity implements View.OnClickListener {

    //MyDatabaseHelper mydatabaseHelper;
    private EditText usernameLogInEdittext, passwordLogInEdittext;
    private CardView  SignInRegisterRequestButton, SignInButton;
    //private TextView SignIntextView;
    private static String call="1";
    private FirebaseAuth mAuth;
    private ProgressBar progressbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        this.setTitle("Sign In");

        mAuth = FirebaseAuth.getInstance();

        progressbar = findViewById(R.id.progressbarId);

        usernameLogInEdittext = findViewById(R.id.usernameLogInId);
        passwordLogInEdittext = findViewById(R.id.passwordLogInId);
        SignInButton = findViewById(R.id.SignInButtonId);
        //SignIntextView = findViewById(R.id.SignInRtextViewId);
        SignInRegisterRequestButton = findViewById(R.id.LogInDonorRegisterButtonCardViewId);
        //GoToArtistsButton = findViewById(R.id.GoToArtistsLayout);

        SignInButton.setOnClickListener(this);
        SignInRegisterRequestButton.setOnClickListener(this);
        //GoToArtistsButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.SignInButtonId:
                //Toast.makeText(getApplicationContext(), "Onclick Correct", Toast.LENGTH_SHORT).show();
                userLogIn();
                break;
            case R.id.LogInDonorRegisterButtonCardViewId:
                Intent intent = (new Intent(getApplicationContext(), SignUp.class));
                Toast.makeText(getApplicationContext(), "Welcome to Sign Up", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                break;

        }

    }

    private void userLogIn() {
        String email=usernameLogInEdittext.getText().toString().trim();
        String password=passwordLogInEdittext.getText().toString().trim();
        //Toast.makeText(getApplicationContext(), "Enter userLogIn Correct", Toast.LENGTH_SHORT).show();



        if(email.isEmpty())
        {
            usernameLogInEdittext.setError("Enter an Email address");
            usernameLogInEdittext.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            usernameLogInEdittext.setError("Enter an valid Email address");
            usernameLogInEdittext.requestFocus();
            return;
        }

        if(password.isEmpty())
        {
            passwordLogInEdittext.setError("Enter an Password");
            passwordLogInEdittext.requestFocus();
            return;
        }
        if(password.length()<6)
        {
            passwordLogInEdittext.setError("Minimum Length of Password should be 6");
            passwordLogInEdittext.requestFocus();
            return;
        }

        progressbar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressbar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = (new Intent(getApplicationContext(), DonorRegistration.class));
                    intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                    //Toast.makeText(getApplicationContext(), "Welcome donor", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Register is successful.", Toast.LENGTH_SHORT).show();
                    //Toasty.success(getApplicationContext(), "Log In Success!", Toast.LENGTH_SHORT, true).show();

                } else {

                    Toast.makeText(getApplicationContext(), "Log In Unsuccessful.", Toast.LENGTH_SHORT).show();
                    //Toasty.warning(getApplicationContext(), "Beware of the dog.", Toast.LENGTH_SHORT, true).show();


                }
            }
        });
    }

}

