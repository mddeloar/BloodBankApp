package com.example.mddeloarhossain.bloodbank;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DonorRegistration extends AppCompatActivity {

    private RadioGroup RradioGroup,RbecomeDonor;
    private RadioButton RgenderButton,RbecomeDonorButton;
    private EditText RNameEditText, RCityEditText, RLocationEditText, RBloodGroupEditText, RBirthDateEditText, RContactNoEditText, REmailEditText, RPasswordEditText;
    String[] Rbloodgroup, Rgendergroup;
    private Spinner Rspinner, RGenderSpinner;
    private CardView saveButton;
    private static String call="1";
    DatabaseReference databaseReference;
    Spinner spinnerGenre;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_registration);


        databaseReference = FirebaseDatabase.getInstance().getReference("donors");
        RradioGroup = findViewById(R.id.RegistrationRadioGroupId);
        ///becomeDonor = findViewById(R.id.BecomeDonorRadioGroupId);

        saveButton = findViewById(R.id.SaveDonorDataButtonId);
        RNameEditText = findViewById(R.id.NameId);
        RCityEditText = findViewById(R.id.AreaId);

        RLocationEditText = findViewById(R.id.DistrictId);
        RBirthDateEditText = findViewById(R.id.AgeId);
        RContactNoEditText = findViewById(R.id.ContactNumberId);

        Rbloodgroup = getResources().getStringArray(R.array.BloodGroup);
        Rgendergroup = getResources().getStringArray(R.array.Gender);

        Rspinner  = findViewById(R.id.spinnerBloodGroup);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.blood_group_sample,R.id.bloodGroupSampleId,Rbloodgroup);
        Rspinner.setAdapter(adapter);
        RGenderSpinner =  findViewById(R.id.spinnerGender);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,R.layout.blood_group_sample,R.id.bloodGroupSampleId,Rgendergroup);
        RGenderSpinner.setAdapter(adapter1);
        //spinnerGenre = (Spinner) findViewById(R.id.spinnerBloodGroup);
        //spinnerGenre = (Spinner) findViewById(R.id.spinnerBloodGroup);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{

                    //int selectedId = RradioGroup.getCheckedRadioButtonId();
                    //RgenderButton = findViewById(selectedId);
                    //Here Gender is available in gender variable in below
                    //String gender = RgenderButton.getText().toString();

                    /*int selectedBecomeDonorId = becomeDonor.getCheckedRadioButtonId();
                    becomeDonorButton = findViewById(selectedBecomeDonorId);
                    //Here Gender is available in gender variable in below
                    String YesOrNo = becomeDonorButton.getText().toString();
                    int inTheList;
                    if(YesOrNo.equals("Yes"))
                    {
                        inTheList=1;
                    }
                    else {
                        inTheList=0;
                    }*/
                    String inTheList="Yes";


                    String name = RNameEditText.getText().toString();
                    String city = RCityEditText.getText().toString();
                    String location = RLocationEditText.getText().toString();
                    ///String bloodgroup = BloodGroupEditText.getText().toString();
                    String age = RBirthDateEditText.getText().toString();
                    String contactnumber = RContactNoEditText.getText().toString();
                    String bloodgroup = Rspinner.getSelectedItem().toString();
                    String gender = RGenderSpinner.getSelectedItem().toString();


                    if(name.isEmpty())
                    {
                        RNameEditText.setError("Enter a name address");
                        RNameEditText.requestFocus();
                        return;
                    }
                    if(city.isEmpty())
                    {
                        RCityEditText.setError("Enter a name address");
                        RCityEditText.requestFocus();
                        return;
                    }
                    if(location.isEmpty())
                    {
                        RLocationEditText.setError("Enter a name address");
                        RLocationEditText.requestFocus();
                        return;
                    }
                    if(age.isEmpty())
                    {
                        RBirthDateEditText.setError("Enter a name address");
                        RBirthDateEditText.requestFocus();
                        return;
                    }

                    if(bloodgroup=="Blood Group")
                    {
                        Toast.makeText(getApplicationContext(), "Blood Group is Needed.", Toast.LENGTH_LONG).show();
                        return;
                    }
                    if(gender=="Gender")
                    {
                        Toast.makeText(getApplicationContext(), "Gender is Needed.", Toast.LENGTH_LONG).show();
                        return;
                    }


                        //long rowId = mydatabaseHelper.InsertData(name, city, location, bloodgroup, gender, birthdate, contactnumber, email, password, inTheList);


                        String key = databaseReference.push().getKey();
                        Donor donor = new Donor(name, city, location, age, contactnumber, bloodgroup, gender, inTheList);
                        databaseReference.child(key).setValue(donor);
                        //int i=databaseReference.child(key).setValue(student);
                        Toast.makeText(getApplicationContext(), "Successfully Data Stored", Toast.LENGTH_LONG).show();


                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "Please enter your information.", Toast.LENGTH_SHORT).show();


                }

            }

        });



    }
}
