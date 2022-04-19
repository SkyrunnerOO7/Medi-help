package com.example.medihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class DoctorsAppointment extends AppCompatActivity {

    //for adding the photo of the prescription
    Button attachment;
    TextView tvAttachment;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_appointment);

        //for the adding the photo of the prescription
        tvAttachment = findViewById(R.id.tvAttachment_dr);
        attachment = findViewById(R.id.photo_attachment);
        //attachment button listener
        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });
    }

    public void openFolder() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            URI = data.getData();
            tvAttachment.setText(URI.getLastPathSegment());
            tvAttachment.setVisibility(View.VISIBLE);
        }
    }


    public void submit_appointment(View view) {
        /** Taking name from the user and show edit text*/
        EditText nameField = (EditText) findViewById(R.id.name_appointment);
        String name = nameField.getText().toString();

        /** Taking age from the user and show edit text*/
        EditText ageField = (EditText) findViewById(R.id.age);
        String age = ageField.getText().toString();

        /** Taking gender from the user and show edit text*/
        String gender = "male";

        /** Taking Symptoms from the user and show edit text*/
        EditText symptomsField = (EditText) findViewById(R.id.current_decease);
        String symptoms = symptomsField.getText().toString();

        /** Taking existingHealthIssue from the user and show edit text*/
        EditText existingHeathIssueField = (EditText) findViewById(R.id.permanent_decease);
        String existingHealthIssue = existingHeathIssueField.getText().toString();

        /** Taking contact from the user and show edit text*/
        EditText contactField = (EditText) findViewById(R.id.contact);
        String contact = contactField.getText().toString();

        /** Taking Remark from the user and show edit text*/
        EditText remarkField = (EditText) findViewById(R.id.remark_appointment);
        String remark = remarkField.getText().toString();




        String priceMessage = createOrderSummary(name, age, gender, symptoms, existingHealthIssue, contact, remark);

        /*Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Medicine Summary ");
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);
        */

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, priceMessage
        );
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);

        /*//new image
        //for the adding the photo of the prescription
        if (URI != null) {
            intent.putExtra(Intent.EXTRA_STREAM, URI);
        }

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }*/

    }

    /**
     * CreateMedicineSummary method
     *
     * @return priceMessage
     */
    private String createOrderSummary(String name,String age, String gender, String symptoms, String existingHealthIssue, String contact, String remark) {

        String priceMessage = "Name: " + name;

        priceMessage = priceMessage + "\n Age: " + age;

        priceMessage = priceMessage + "\n Gender: " + gender;

        priceMessage = priceMessage + "\n Symptoms: " + symptoms;

        priceMessage = priceMessage + "\n Existing Health Issue: " + existingHealthIssue;

        priceMessage = priceMessage + "\n Contact: " + contact;

        priceMessage = priceMessage + "\n Remark: " + remark;


        return priceMessage;
    }

}




















