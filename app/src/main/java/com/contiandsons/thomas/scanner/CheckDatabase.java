package com.contiandsons.thomas.scanner;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

import static android.R.attr.name;


/**
 * Created by Thomas on 5/28/2017.
 */

public class CheckDatabase extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Checker.db";
    public static final String CHECKS_TABLE_NAME = "checks";
    public static final String CHECKS_COLUMN_ID = "id";
    public static final String CHECKS_COLUMN_NAME = "check_name";
    public static final String CHECKS_COLUMN_CHECK = "checker";
    public static final String CREATE_TABLE_1 = "create table checks" + "(id integer primary key, check_name text, checker text)";
    public CheckDatabase(Context context){
        super(context, DATABASE_NAME, null,1);
    }

    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_1);

    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS checks");
        onCreate(db);
    }

    public boolean tableExist(){
        File dbtest = new File("/data/data/com.contiandsons.thomas.scanner/databases/Checker.db");
        if (dbtest.exists()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean insertCheck(String name, int check){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("check_name",name);
        contentValues.put("checker",check);

        db.insert("checks",null,contentValues);
        return true;
    }

    public Cursor getCheck(int name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from checks where id=" + name + "", null);
        return res;
    }

    public void updateCheck(int id, int check, int checkNum ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("checker",checkNum);
        db.update("checks", contentValues, "id=?", new String[]{Integer.toString(checkNum)});
    }

}
