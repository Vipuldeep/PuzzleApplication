package com.example.jc497971.ourpuzzleapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ThirdOptionsActivity extends AppCompatActivity {

    Button threeoptionone, threeoptiontwo, threeoptionthree;
    Bundle bundle;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_options);
        threeoptionone = (Button) findViewById(R.id.threeoptionone);
        bundle = getIntent().getExtras();
        //getextras() will retrieve the values passed by putextra
        name = bundle.getString("name");
        threeoptionone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdOptionsActivity.this, ThreeOptiononeActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        threeoptiontwo = (Button) findViewById(R.id.threeoptiontwo);
        threeoptiontwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdOptionsActivity.this, ThreeOptiontwoActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        threeoptionthree = (Button) findViewById(R.id.threeoptionthree);
        threeoptionthree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThirdOptionsActivity.this, ThirdOptionthreeActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }
}
