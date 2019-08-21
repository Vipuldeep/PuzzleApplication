package com.example.jc497971.ourpuzzleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OptionsActivity extends AppCompatActivity {

    Button btnforthree, btnfortwo;
    Bundle bundle;
    //creates a copy and helps in calling edittext from the mainactivity
    String name;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        bundle = getIntent().getExtras();
        //getextras() will retrieve the values passed by putextra
        name = bundle.getString("name");
        display = (TextView) findViewById(R.id.textView);
        display.setText(name.toString());
        btnforthree = (Button) findViewById(R.id.third);
        btnforthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionsActivity.this, ThirdOptionsActivity.class);
                intent.putExtra("name",name); // passing the name and retrieve them using getExtra
                startActivity(intent);
            }
        });

        btnfortwo = (Button) findViewById(R.id.second);
        btnfortwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OptionsActivity.this, SecondOptionsActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }
}
