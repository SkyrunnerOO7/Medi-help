package com.example.medihelp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PharmaActivity extends AppCompatActivity {
    int Dose = 0;
    String name;
    String remark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharma);
    }

    public void submitOrder(View view) {
        /** Taking name from the user and show edit text*/
        EditText nameField = (EditText) findViewById(R.id.name_view);
        String name = nameField.getText().toString();

        /** Taking Remark from the user and show edit text*/
        EditText remarkField = (EditText) findViewById(R.id.remark_view);
        String remark = remarkField.getText().toString();

        String priceMessage = createOrderSummary(name, remark);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Medicine Summary ");
        intent.putExtra(Intent.EXTRA_TEXT,priceMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

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
