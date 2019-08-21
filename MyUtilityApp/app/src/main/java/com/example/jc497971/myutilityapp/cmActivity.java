package com.example.jc497971.myutilityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class cmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cm);
        //final ensures that i would not change values while coding
        final EditText editCentimeters = (EditText) findViewById(R.id.editCentimeters);
        final EditText editInches = (EditText) findViewById(R.id.editInches);
        final EditText ReverseInches = (EditText) findViewById(R.id.ReverseCentimeters);
        //R is a Class in android that are having the id’s of all the view’s.
        //findViewById is a method that finds the view from the layout resource file that are attached with current Activity.
        final EditText ReverseCentimeters = (EditText) findViewById(R.id.ReverseInches);
        Button buttonConvert1 = (Button)findViewById(R.id.buttonConvert1);
        Button buttonConvert = (Button)findViewById(R.id.buttonConvert);
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (editCentimeters.getText().toString().isEmpty())
                //isempty() is used to check whether the value is not empty.
                //The toString() method returns the string representation of the object
                {
                    Toast.makeText(getApplicationContext(), "please enter again", Toast.LENGTH_SHORT).show();
                } else {
                    double centimeters = Double.valueOf(editCentimeters.getText().toString());
                    double inches = centimeters * 0.393700787;
                    editInches.setText(String.valueOf(inches));
                }
            }
        });
        buttonConvert1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (ReverseCentimeters.getText().toString().isEmpty())
                //isempty() is used to check whether the value is not empty.
                //The toString() method returns the string representation of the object
                {
                    Toast.makeText(getApplicationContext(), "please enter again", Toast.LENGTH_SHORT).show();
                } else {
                    //The double variable can hold very large numbers.
                    double RCentimeters = Double.valueOf(ReverseCentimeters.getText().toString());
                    double RInches = (RCentimeters / 0.393700787);
                    ReverseInches.setText(String.valueOf(RInches));
                }
            }
        });
    }
}
