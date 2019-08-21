package com.example.jc497971.myutilityapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LitreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litre);
        final EditText editMililitres = (EditText) findViewById(R.id.editMililitres);
        //String vmili = editMililitres.getText().toString();
        //final ensures that i would not change values while coding
        final EditText editLitres = (EditText) findViewById(R.id.editLitres);
        //R is a Class in android that are having the id’s of all the view’s.
        //findViewById is a method that finds the view from the layout resource file that are attached with current Activity.
        final EditText ReverseLitres = (EditText) findViewById(R.id.ReverseLitres);
        final EditText ReverseMililitres = (EditText) findViewById(R.id.ReverseMililitres);
        Button buttonConvert4 = (Button)findViewById(R.id.buttonConvert4);
        Button buttonConvert5 = (Button)findViewById(R.id.buttonConvert5);
        buttonConvert4.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View arg0) {
                //isempty() is used to check whether the value is not empty.
                //The toString() method returns the string representation of the object
                if (editMililitres.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "please enter again", Toast.LENGTH_SHORT).show();
                }
             //  if (editMililitres.getText().toString().trim().length() <0)
                   //trim to avoid blank spaces
                //if(vmili.matches("null"))
              //  {
               //     Toast.makeText(getApplicationContext(), "please enter between the limit 0.5 to 2", Toast.LENGTH_SHORT).show();
                   // Toast.makeText(LitreActivity.this,"Please enter something",Toast.LENGTH_LONG).show();
              //  }
                else {
                    //The double variable can hold very large numbers.
                        double mililitres = Double.valueOf(editMililitres.getText().toString());
                        double litres = (mililitres / 1000);
                        editLitres.setText(String.valueOf(litres));
                  }

            }
        });
        buttonConvert5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                if (ReverseLitres.getText().toString().isEmpty())
                //isempty() is used to check whether the value is not empty.
                //The toString() method returns the string representation of the object
                {
                    Toast.makeText(getApplicationContext(), "please enter again", Toast.LENGTH_SHORT).show();
                } else {
                    // The double variable can hold very large numbers
                    double Rlitres = Double.valueOf(ReverseLitres.getText().toString());
                    double Rmililitres = (Rlitres * 1000);
                    ReverseMililitres.setText(String.valueOf(Rmililitres));
                }
            }
        });
    }
}
