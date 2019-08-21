package com.example.jc497971.ourpuzzleapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    DBHelper dbHelper;
    String scoreDetails = "";
    TextView t;
    Button parentcheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        //calling dbhelper from there.
        dbHelper = new DBHelper(this);
        //onclick of the button.
        t = (TextView) findViewById(R.id.scoreboard);
        Cursor cursor = dbHelper.getAllPlayers();
        //calling the loop fpr all the information that is been stored in run of android to show it on screen in textview
        if (cursor != null){
            cursor.moveToFirst();
            do{

                String username = cursor.getString(cursor.getColumnIndex(DBHelper.USERNAME_COL));
                String duration = cursor.getString(cursor.getColumnIndex(DBHelper.DURATION_COL));
                String level = cursor.getString(cursor.getColumnIndex(DBHelper.LEVEL_COL));
                String date = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COL));
                String image_name = cursor.getString(cursor.getColumnIndex(DBHelper.IMAGE_NAME_COL));

                Log.i("player", " "+ username+" "+duration+" "+level+" "+date+" "+image_name+" ");
                scoreDetails += "Player: "+ username+", Timeperiod: "+duration+", level: "+level+", date: "+date+", Image selected: "+image_name+" "+"\n";


            }while (cursor.moveToNext());

        }
        //textview will set all the text in textview named scoredetails
        t.setText(scoreDetails);

        parentcheck = (Button) findViewById(R.id.parentcheck);
        parentcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreActivity.this, ParentActivity.class);
                startActivity(intent);
            }
        });
    }
}
