package com.example.jc497971.myutilityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    // onclickbutton is a listener class
    // intent is a message object use to transfer or jump between two applications
    public void onClickButton(View v){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
        //here android launches the second activity.
    }
}
