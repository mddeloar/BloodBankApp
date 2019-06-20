package com.example.mddeloarhossain.bloodbank;

        import android.content.ContentResolver;
        import android.content.Intent;
        import android.net.Uri;
        import android.support.annotation.NonNull;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.webkit.MimeTypeMap;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.ProgressBar;
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
        import com.squareup.picasso.Picasso;

public class ImageUploadWithDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button chooseButton, saveButton, displayButton;
    private ImageView imageView;
    private EditText imageNameEditText, nameEditText, districtEditText;
    private ProgressBar progressBar;
    private Uri imageUri;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    StorageTask uploadTask;

    private static final int IMAGE_REQUEST = 1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_upload_with_details);


        databaseReference = FirebaseDatabase.getInstance().getReference("donorsimageswithdetails");
        storageReference = FirebaseStorage.getInstance().getReference("donorsimageswithdetails");


        chooseButton = findViewById(R.id.ChooseImageButton);
        saveButton = findViewById(R.id.saveImageButton);
        displayButton = findViewById(R.id.displayImageButton);
        progressBar = findViewById(R.id.progressbarId);

        imageView = findViewById(R.id.imageViewId);
        imageNameEditText = findViewById(R.id.imageNameEditTextId);
        nameEditText = findViewById(R.id.NameEditTextId);
        districtEditText = findViewById(R.id.DistrictEditTextId);

        chooseButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        displayButton.setOnClickListener(this);



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

            case R.id.displayImageButton:

                Intent intent = (new Intent(getApplicationContext(), ImageShowWithDetailsActivity.class));
                startActivity(intent);

                break;
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

        final String imageName = imageNameEditText.getText().toString().trim();
        final String name = nameEditText.getText().toString().trim();
        if(imageName.isEmpty()){
            imageNameEditText.setError("Enter the image name");
            imageNameEditText.requestFocus();
            return;
        }
        if(name.isEmpty()){
            nameEditText.setError("Enter the image name");
            nameEditText.requestFocus();
            return;
        }
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

                        UploadWithDetails upload = new UploadWithDetails(imageName, name, downloadUri.toString());

                        String uploadId = databaseReference.push().getKey();
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



    }



}
