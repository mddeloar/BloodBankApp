package com.example.mddeloarhossain.bloodbank;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.GridView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private GridView gridView;
    private CardView profileCardView;
    int[] images = {R.drawable.people, R.drawable.search, R.drawable.hospital, R.drawable.note4, R.drawable.ambulance2, R.drawable.profile1, R.drawable.edit, R.drawable.message1, R.drawable.profile, R.drawable.note3 };
    String[] gridNames;
    private ProgressDialog dialog;
    /*int[] fimages = {R.drawable.ic_call_blackk_24dp,R.drawable.ic_local_post_office_black_24dp };
    String[] fgridNames;*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //islogin();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        dialog = new ProgressDialog(this);

        gridNames = getResources().getStringArray(R.array.GridItemName);
        //fgridNames = getResources().getStringArray(R.array.fGridItemName);

        gridView = findViewById(R.id.gridViewId);
        profileCardView = findViewById(R.id.ProfileCardViewId);
        //fgridView = findViewById(R.id.fgridViewId);


        profileCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = (new Intent(getApplicationContext(), DonorProfile.class));
                Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_LONG).show();
                //intent.putExtra("tag",value);
                startActivity(intent);

            }
        });

        /*firstAdapter fadapter = new firstAdapter(this, fgridNames, fimages);
        gridView.setAdapter(fadapter);*/
        CustomAdapter adapter = new CustomAdapter(this, gridNames, images);
        gridView.setAdapter(adapter);






        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value  = gridNames[i];
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
                Intent intent;
                switch (i){
                    case 0:
                        intent = (new Intent(getApplicationContext(), BloodDonorsActivity.class));
                        //Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
                        //Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_LONG).show();
                        //intent.putExtra("tag",value);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = (new Intent(getApplicationContext(), AllDonorInfo.class));
                        startActivity(intent);
                        break;
                    case 2:
                        intent = (new Intent(getApplicationContext(), NavigationDrawerActivity.class));
                        startActivity(intent);
                        break;
                    case 3:
                        intent = (new Intent(getApplicationContext(), FullScreenActivity.class));
                        startActivity(intent);
                        break;
                    case 4:
                        intent = (new Intent(getApplicationContext(), Registration1Activity.class));
                        startActivity(intent);
                        break;
                    case 5:
                        intent = (new Intent(getApplicationContext(), BottomNavigationBarActivity.class));
                        startActivity(intent);
                        break;
                    case 6:
                        intent = (new Intent(getApplicationContext(), AutoCompleteTextViewActivity.class));
                        startActivity(intent);
                        break;
                    case 7:
                        //intent = (new Intent(getApplicationContext(), ImageUploadActivity.class));
                        intent = (new Intent(getApplicationContext(), ImageUploadWithDetailsActivity.class));
                        startActivity(intent);
                        break;




                }
            }
        });

        /*fgridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value  = fgridNames[i];
                Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
            }
        });*/

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_layout,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        /*if(item.getItemId()==R.id.SignOutMenuItemId)
        {
            FirebaseAuth.getInstance().signOut();
            finish();
            Intent intent = (new Intent(getApplicationContext(), SignIn.class));
            Toast.makeText(getApplicationContext(), "Successfully Sign Out", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }*/

        if(item.getItemId()==R.id.SignInMenuItemId)
        {

            Intent intent = (new Intent(getApplicationContext(), SignIn.class));
            Toast.makeText(getApplicationContext(), "Sign In Clicked.", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        if(item.getItemId()==R.id.SignUpMenuItemId)
        {

            Intent intent = (new Intent(getApplicationContext(), SignUp.class));
            Toast.makeText(getApplicationContext(), "Sign Up Clicked.", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    /*private void islogin(){

        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        if(sharedPreferences.contains("usernameKey") && sharedPreferences.contains("passwordKey")){

            Intent intent = (new Intent(getApplicationContext(), FullScreenActivity.class));
            Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }



    }*/




}
