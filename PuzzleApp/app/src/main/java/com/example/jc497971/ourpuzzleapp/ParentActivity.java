package com.example.jc497971.ourpuzzleapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ParentActivity extends AppCompatActivity {

    DBHelper dbHelper;
    String scorePDetails = "";
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parent);
        //calling dbhelper from there.
        dbHelper = new DBHelper(this);
        //onclick of the button.
        t1 = (TextView) findViewById(R.id.statsboard);
        Cursor cursor = dbHelper.getAllPlayers();
        //calling the loop for all the information that is been stored in run of android to show it on screen in textview
        if (cursor != null){
            cursor.moveToFirst();
            do{

                String username = cursor.getString(cursor.getColumnIndex(DBHelper.USERNAME_COL));
                String duration = cursor.getString(cursor.getColumnIndex(DBHelper.DURATION_COL));
                String level = cursor.getString(cursor.getColumnIndex(DBHelper.LEVEL_COL));
                String date = cursor.getString(cursor.getColumnIndex(DBHelper.DATE_COL));

                Log.i("player", " "+ username+" "+duration+" "+level+" "+date+" ");
                scorePDetails += "PLAYER: "+ username+ "\n TIME PLAYED: "+duration+",\n DATE: "+date+" "+"\n";

            }while (cursor.moveToNext());

        }
        //textview will set all the text in textview named scoredetails
        t1.setText(scorePDetails);

    }
}
