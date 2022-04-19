package com.example.medihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class PharmaActivity extends AppCompatActivity {
    int Dose = 0;


    //for adding the photo of the prescription=========================================================
    Button attachment;
    TextView tvAttachment;
    Uri URI = null;
    private static final int PICK_FROM_GALLERY = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharma);

        //for the adding the photo of the prescription
        tvAttachment = findViewById(R.id.tvAttachment);
        attachment = findViewById(R.id.btAttachment);
        //attachment button listener
        attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFolder();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            URI = data.getData();
            tvAttachment.setText(URI.getLastPathSegment());
            tvAttachment.setVisibility(View.VISIBLE);
        }
    }

    public void submitOrder(View view) {
        /** Taking name from the user and show edit text*/
        EditText nameField = (EditText) findViewById(R.id.name_view);
        String name = nameField.getText().toString();

        /** Taking Remark from the user and show edit text*/
        EditText remarkField = (EditText) findViewById(R.id.remark_view);
        String remark = remarkField.getText().toString();

        String priceMessage = createOrderSummary(name, remark);

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        sendIntent.setType("text/plain");

        Intent shareIntent = Intent.createChooser(sendIntent, null);
        startActivity(shareIntent);
        /*Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Medicine Summary ");
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);

        //new image
        //for the adding the photo of the prescription
        if (URI != null) {
            intent.putExtra(Intent.EXTRA_STREAM, URI);
        }

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }*/

    }

    public void openFolder() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.putExtra("return-data", true);
        startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
    }
    /**
     * CreateMedicineSummary method
     *
     * @return priceMessage
     */
    private String createOrderSummary(String name, String remark) {

        String priceMessage = "Name: " + name;

        priceMessage = priceMessage + "\n Dose: " + Dose;

        priceMessage = priceMessage + "\n Remark: " + remark;

        priceMessage = priceMessage + "\n Thank you!";

        return priceMessage;
    }

    public void increment(View view) {
        if (Dose == 100) { // Show an error message as a toast
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();

            // Exit this method early because there's nothing left to do
            return;
        }
        Dose = Dose + 1;
        displayQuantity(Dose);

    }

    public void decrement(View view) {
        if (Dose == 1) {
            // Show an error message as a toast
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
            // Exit this method early because there's nothing left to do
            return;
        }
        Dose = Dose - 1;
        displayQuantity(Dose);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int dose) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + dose);
    }




    }
