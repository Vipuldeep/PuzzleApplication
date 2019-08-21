package com.example.jc497971.ourpuzzleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondOptionsActivity extends AppCompatActivity {

    Button twooptionone, twooptiontwo, twooptionthree;
    Bundle bundle;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_options);
        twooptionone = (Button) findViewById(R.id.twooptionone);
        //getextras() will retrieve the values passed by putextra
        bundle = getIntent().getExtras();
        name = bundle.getString("name");
        twooptionone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondOptionsActivity.this, TwoOptiononeActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        twooptiontwo = (Button) findViewById(R.id.twooptiontwo);
        twooptiontwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondOptionsActivity.this, TwoOptiontwoActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        twooptionthree = (Button) findViewById(R.id.twooptionthree);
        twooptionthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondOptionsActivity.this, TwoOptionthreeActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }
}
