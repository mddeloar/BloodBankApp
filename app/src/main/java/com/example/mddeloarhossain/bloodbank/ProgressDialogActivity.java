package com.example.mddeloarhossain.bloodbank;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

public class ProgressDialogActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    private CheckBox checkBox;
    String[] Rbloodgroup, Rgendergroup;
    private Spinner Rspinner, RGenderSpinner;
    Button button;
    private AutoCompleteTextView autoCompleteTextView;
    private String[] bloodGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_dialog);

        progressDialog = new ProgressDialog(ProgressDialogActivity.this);
        progressDialog.setTitle("Hello World!");
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        button = findViewById(R.id.showButtonId);

        checkBox = findViewById(R.id.checkboxId);
        checkBox.setChecked(true);

        Rbloodgroup = getResources().getStringArray(R.array.BloodGroup);

        Rspinner  = findViewById(R.id.spinnerBloodGroup);
        //Rspinner.setTag("AB+");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.blood_group_sample,R.id.bloodGroupSampleId,Rbloodgroup);
        Rspinner.setAdapter(adapter);

        bloodGroup = getResources().getStringArray(R.array.BloodGroup);
        autoCompleteTextView = findViewById(R.id.autoOCmpleteTextViewId);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bloodGroup);
        autoCompleteTextView.setThreshold(1);
        autoCompleteTextView.setAdapter(adapter1);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value = autoCompleteTextView.getText().toString();
                Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bloodgroup = Rspinner.getSelectedItem().toString();
                if(bloodgroup.equals("Blood Group"))
                {
                    Toast.makeText(getApplicationContext(), "Blood Group is Needed.", Toast.LENGTH_LONG).show();
                    //Rbloodgroup.equals()
                    //return;
                     //== "blood group"
                    //Rspinner.setTag("AB+");
                }else {
                    Toast.makeText(getApplicationContext(), "Blood Group :"+bloodgroup, Toast.LENGTH_LONG).show();

                }

            }
        });




    }
}
