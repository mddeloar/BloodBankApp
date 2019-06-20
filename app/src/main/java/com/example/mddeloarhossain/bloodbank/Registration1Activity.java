package com.example.mddeloarhossain.bloodbank;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;
import com.squareup.picasso.Picasso;

public class Registration1Activity extends AppCompatActivity implements View.OnClickListener {
    private Button chooseButton, saveButton;
    private ImageView imageView;
    private EditText imageNameEditText;
    private ProgressBar progressBar;
    private Uri imageUri;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    StorageTask uploadTask;

    private RadioGroup RradioGroup,RbecomeDonor;
    private RadioButton RgenderButton,RbecomeDonorButton;
    private EditText RNameEditText, RCityEditText, RLocationEditText, RBloodGroupEditText, RBirthDateEditText, RContactNoEditText, REmailEditText, RPasswordEditText;
    String[] Rbloodgroup, Rgendergroup;
    private Spinner Rspinner, RGenderSpinner;
    //private CardView saveButton;
    private static String call="1";
    //DatabaseReference databaseReference;
    Spinner spinnerGenre;

    private CheckBox checkBox;
    //private CardView saveButton;

    private static final int IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration1);



        databaseReference = FirebaseDatabase.getInstance().getReference("donorswithdetails");
        storageReference = FirebaseStorage.getInstance().getReference("donorswithdetails");


        chooseButton = findViewById(R.id.ChooseImageButton);
        saveButton = findViewById(R.id.saveImageButton);
        //displayButton = findViewById(R.id.displayImageButton);
        progressBar = findViewById(R.id.progressbarId);

        imageView = findViewById(R.id.imageViewId);
        //imageNameEditText = findViewById(R.id.NameId);
        //nameEditText = findViewById(R.id.NameEditTextId);
        //districtEditText = findViewById(R.id.DistrictEditTextId);

        chooseButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        //displayButton.setOnClickListener(this);

        checkBox = findViewById(R.id.checkboxId);
        //saveButton = findViewById(R.id.SaveDonorDataButtonId);


        //databaseReference = FirebaseDatabase.getInstance().getReference("donors");
        RradioGroup = findViewById(R.id.RegistrationRadioGroupId);
        ///becomeDonor = findViewById(R.id.BecomeDonorRadioGroupId);

        //saveButton = findViewById(R.id.SaveDonorDataButtonId);
        RNameEditText = findViewById(R.id.NameId);
        RCityEditText = findViewById(R.id.DistrictId);

        RLocationEditText = findViewById(R.id.AreaId);
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



        CircleMenu circleMenu = (CircleMenu) findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.drawable.ic_add, R.drawable.ic_cancel)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.hospital)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.hospital)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.hospital)
                .addSubMenu(Color.parseColor("#8A39FF"), R.drawable.hospital)
                .addSubMenu(Color.parseColor("#FF6A00"), R.drawable.lovely_time)
                .addSubMenu(Color.parseColor("#FF4BB6EF"), R.drawable.hospital)
                .addSubMenu(Color.parseColor("#dcf408"), R.drawable.hospital)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {
                        Intent intent;
                        switch (index) {
                            case 0:
                                intent = (new Intent(getApplicationContext(), SingleProfileActivity.class));
                                //Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                break;
                            case 1:
                                intent = (new Intent(getApplicationContext(), SingleProfile2Activity.class));
                                //Toast.makeText(getApplicationContext(), "Welcome to your profile", Toast.LENGTH_SHORT).show();
                                startActivity(intent);
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            case 6:
                                break;
                            default:
                                Toast.makeText(getApplicationContext(), "Notihing", Toast.LENGTH_SHORT).show();
                        }
                    }

                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {}

            @Override
            public void onMenuClosed() {}

        });






    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.ChooseImageButton:
                openFileChooser();

                break;

            case R.id.saveImageButton:
                if(uploadTask !=null && uploadTask.isInProgress()){
                    Toast.makeText(getApplicationContext(),"Uploading is in progress",Toast.LENGTH_SHORT).show();

                }else{
                    saveData();

                }


                break;

           /* case R.id.displayImageButton:

                Intent intent = (new Intent(getApplicationContext(), ImageShowWithDetailsActivity.class));
                startActivity(intent);

                break;*/
        }

    }
    void openFileChooser(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            imageUri = data.getData();
            Picasso.with(this).load(imageUri).into(imageView);
        }
    }

    //getting the extesion of the image
    public String getFileExtension(Uri imageUri){
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(imageUri));

    }

    public void saveData(){



        try{

            //final String inTheList="Yes";


            final String name = RNameEditText.getText().toString();
            final String city = RCityEditText.getText().toString();
            final String location = RLocationEditText.getText().toString();
            ///String bloodgroup = BloodGroupEditText.getText().toString();
            final String age = RBirthDateEditText.getText().toString();
            final String contactnumber = RContactNoEditText.getText().toString();
            final String bloodgroup = Rspinner.getSelectedItem().toString();
            final String gender = RGenderSpinner.getSelectedItem().toString();


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


            final String key = databaseReference.push().getKey();
            /*Donor donor = new Donor(name, city, location, age, contactnumber, bloodgroup, gender, inTheList);
            databaseReference.child(key).setValue(donor);
            //int i=databaseReference.child(key).setValue(student);
            Toast.makeText(getApplicationContext(), "Successfully Data Stored", Toast.LENGTH_LONG).show();*/



            final String check;
            if(checkBox.isChecked()){
                check = "Yes";
                //Toast.makeText(getApplicationContext(),check,Toast.LENGTH_SHORT).show();
            }else {
                check = "No";
            }
            final String imageName = RNameEditText.getText().toString().trim();
            final String inTheList = check;

            if(imageName.isEmpty()){
                imageNameEditText.setError("Enter the image name");
                imageNameEditText.requestFocus();
                return;
            }
       /* if(name.isEmpty()){
            nameEditText.setError("Enter the image name");
            nameEditText.requestFocus();
            return;
        }*/
            StorageReference ref  = storageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));

            ref.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            // Get a URL to the uploaded content
                            Toast.makeText(getApplicationContext(),"Successfully image stored.",Toast.LENGTH_SHORT).show();

                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUri = urlTask.getResult();

                            AdapterRegistration upload = new AdapterRegistration(imageName, name, city, location, age, contactnumber, bloodgroup, gender, inTheList, downloadUri.toString());

                            String uploadId = key;
                            databaseReference.child(uploadId).setValue(upload);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            // Handle unsuccessful uploads
                            // ...
                            Toast.makeText(getApplicationContext()," Image not stored.",Toast.LENGTH_SHORT).show();
                        }
                    });


        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Please enter your information.", Toast.LENGTH_SHORT).show();


        }






    }



}
