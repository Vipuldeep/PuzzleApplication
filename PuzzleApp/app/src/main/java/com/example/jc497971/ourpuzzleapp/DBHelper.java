package com.example.jc497971.ourpuzzleapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jc497971 on 27/01/2019.
 */

public class DBHelper extends SQLiteOpenHelper {



    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME ="jc497971_vipulpuzzle.db";
    static final String TABLE_NAME = "users";
    //defined table name.
    static final String ID_COL = "_id";
    static final String USERNAME_COL = "username";
    static final String LEVEL_COL = "level";
    static final String DURATION_COL = "score";
    static final String DATE_COL = "date";
    static final String IMAGE_NAME_COL = "image";

    //database version constructor
    public DBHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION );
    }

    //blob-images, text, integer, real. sql have four datatypes.
    //implementation of on create
    //i made one table with table name "users".
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "create table " + TABLE_NAME
                + "("
                + ID_COL + " integer primary key autoincrement,"
                + USERNAME_COL + " text default 'unknown',"
                + DURATION_COL + " integer default 0,"
                + LEVEL_COL + " integer default 1," //Level = 1 or 2 or 3 only
                + DATE_COL + " timestamp default CURRENT_TIMESTAMP,"
                + IMAGE_NAME_COL + " text default 'c'"
                + ")";
        db.execSQL(create_table);
    }

    //only runs if old version is different from new version.
    //if developer puts different version like intead of 1 to 2.
    //database loses if we shift from one version to another.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " +  TABLE_NAME);
        onCreate(db);
    }

    //string usr - supplies username.
    //builds coloumn username and score.
    //and sets score to initial 0.
    public boolean insertPlayer(String usr, int duration, int level, String image_name){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME_COL, usr);
        values.put(DURATION_COL, duration);
        values.put(LEVEL_COL, level);
        values.put(IMAGE_NAME_COL, image_name);
        //inserts value in table name
        db.insert(TABLE_NAME, null, values);
        return true;
    }

    //gets a username. for that we have to write the name, that is why we use .getReadableDatabase();
    public Cursor getPlayer(String usr){
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlStr = "select * from " + TABLE_NAME + " where "
                + USERNAME_COL + " = " + "'" + usr + "'"
                + " order by "
                + ID_COL + " DESC";
        //i run it from here. it has a set of records so we use cursor to go through set of records.
        Cursor cursor = db.rawQuery(sqlStr, null);
        return cursor;
    }

    //given the username, i upgrade the score
    public boolean updatePlayer(String usr, int score){
        SQLiteDatabase db = this.getWritableDatabase();
        if(getPlayer(usr) == null){
            return false;
        }
        ContentValues values = new ContentValues();
        //supply the score from public boolean updatePlayer(String usr, int score)
        //values.put(SCORE_COL, score);
        //=? binded parameter.
        //we fill ? from " = ?", new String[]{usr}) from the string user of this function.
        values.put(DURATION_COL, score);
        db.update(TABLE_NAME, values, USERNAME_COL + " = ?", new String[]{usr});
        return true;
    }



    public Cursor getAllPlayers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlstr = "select * from " + TABLE_NAME
                + " order by " + DURATION_COL + " ASC,"
                + USERNAME_COL + " ASC";
        return db.rawQuery(sqlstr, null);
    }

}

