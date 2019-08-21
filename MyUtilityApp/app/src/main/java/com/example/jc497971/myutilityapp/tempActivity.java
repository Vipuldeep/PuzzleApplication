package com.example.jc497971.myutilityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class tempActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);
        //final ensures that i would not change values while coding
        final EditText editCelsius = (EditText) findViewById(R.id.editCelsius);
        final EditText editFarhenhiet = (EditText) findViewById(R.id.editFarhenhiet);
        //R is a Class in android that are having the id’s of all the view’s.
        //findViewById is a method that finds the view from the layout resource file that are attached with current Activity.
        final EditText ReverseFarhenhiet = (EditText) findViewById(R.id.ReverseFarhenhiet);
        final EditText ReverseCelsius = (EditText) findViewById(R.id.ReverseCelsius);
        Button buttonConvert = (Button)findViewById(R.id.buttonConvert2);
        Button buttonConvert3 = (Button)findViewById(R.id.buttonConvert3);
        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (editCelsius.getText().toString().isEmpty())
                //isempty() is used to check whether the value is not empty.
                //The toString() method returns the string representation of the object
                {
                    Toast.makeText(getApplicationContext(), "please enter again", Toast.LENGTH_SHORT).show();
                } else {
                    double celsius = Double.valueOf(editCelsius.getText().toString());
                    double farhenhiet = ((celsius * 9) / 5) + 32;
                    editFarhenhiet.setText(String.valueOf(farhenhiet));
                }
            }
        });
        buttonConvert3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (ReverseFarhenhiet.getText().toString().isEmpty())
                //isempty() is used to check whether the value is not empty.
                //The toString() method returns the string representation of the object
                {
                    Toast.makeText(getApplicationContext(), "please enter again", Toast.LENGTH_SHORT).show();
                } else {
                    //The double variable can hold very large numbers.
                    double Rfarhenhiet = Double.valueOf(ReverseFarhenhiet.getText().toString());
                    double Rcelsius = ((Rfarhenhiet - 32) * 5 / 9);
                    ReverseCelsius.setText(String.valueOf(Rcelsius));
                }
            }
        });
    }
}
