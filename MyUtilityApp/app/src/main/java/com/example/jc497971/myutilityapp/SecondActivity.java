package com.example.jc497971.myutilityapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class SecondActivity extends AppCompatActivity {
    String color_codes[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button setting_btn = (Button) findViewById(R.id.button5);
        //onclicklistener listens and jumps to settingsActivity
        setting_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivityForResult(intent,1);
            }
        });
        color_codes = getResources().getStringArray(R.array.color_codes);
    }
    public void onClickButton1(View v){
        //links and jump to cmActivity
        Intent intent = new Intent(this,cmActivity.class);
        startActivity(intent);
    }
    public void onClickButton2(View v){
        //links and jump to tempActivity
        Intent intent = new Intent(this,tempActivity.class);
        startActivity(intent);
    }
    public void onClickButton3(View v){
        //links and jump to LitreActivity
        Intent intent = new Intent(this,LitreActivity.class);
        startActivity(intent);
    }
    public void onClickButton4(View v){
        //links and jump to settingActivity
        Intent intent = new Intent(this,SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //spinner takes the request from the user for color and responds back with a color.
        super.onActivityResult(requestCode, resultCode, data);
        //Relative layout because relative layout is used and id is provided.
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.main_relative_layout);
        //I used relative layeout the is why upper line says Realative layout. I call it by ID that is been provided by me in .xml file.
        if(requestCode == 1)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                Bundle bundle = data.getExtras();
                if(bundle != null)
                {
                    int index = (int)bundle.get("index");
                    Log.i("code", color_codes[index]);
                    int color_code = Integer.parseInt(color_codes[index], 16);
                    Log.i("color code", Integer.toString(color_code));
                    layout.setBackgroundColor(0xff000000 + color_code);
                }
            }

        }
    }
}
