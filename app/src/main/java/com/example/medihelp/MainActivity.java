package com.example.medihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //code for moving to doctors appointment activity================================================================
        TextView drAppointment = (TextView) findViewById(R.id.doctor);

        // Set a click listener on that View
        drAppointment.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent drAppointmentIntent = new Intent(MainActivity.this, DoctorsAppointment.class);
                startActivity(drAppointmentIntent);
            }
        });

        //code for moving to Pharma activity================================================================
        TextView pharma = (TextView) findViewById(R.id.pharma);

        // Set a click listener on that View
        pharma.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers View is clicked on.
            @Override
            public void onClick(View view) {
                Intent pharmaIntent = new Intent(MainActivity.this, PharmaActivity.class);
                startActivity(pharmaIntent);
            }
        });

    }
}