package com.contiandsons.thomas.scanner;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

import java.io.File;

import static android.R.attr.name;

/**
 * Created by Thomas on 4/8/2017.
 */

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Scanner.db";
    public static final String ITEMS_TABLE_NAME = "gamble";
    public static final String ITEMS_COLUMN_ID = "id";
    public static final String ITEMS_COLUMN_LOCATION = "location";
    public static final String ITEMS_COLUMN_SUBLOCALA = "sub_local_a";
    public static final String ITEMS_COLUMN_SUBLOCALB = "sub_local_b";
    public static final String ITEMS_COLUMN_DESCRIPTION = "description";

    public static final String CREATE_TABLE_1 = "create table gamble"+
            "(id integer primary key, location text, sub_local_a double, sub_local_b double, description text)";

    public Database(Context context){
        super(context, DATABASE_NAME, null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists gamble ");
        onCreate(db);
    }
    public boolean tableExist(){
        File dbtest = new File("/data/data/com.contiandsons.thomas.scanner/databases/Scanner.db");
        if (dbtest.exists()) {
            return true;
        } else {
            return false;
        }
    }


    public boolean insertReg(String local,long subLocalA, long subLocalB,String descrip){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contextValues = new ContentValues();

        contextValues.put("location",local);
        contextValues.put("sub_local_a",subLocalA);
        contextValues.put("sub_local_b", subLocalB);
        contextValues.put("description",descrip);

        db.insert("gamble",null,contextValues);
        return true;
    }

    public Cursor getOrderByName()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble order by description asc",null);

        return res;

    }
    public Cursor getOrderByLocation(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble order by location asc",null);

        return res;
    }

    public Cursor getOrderByBarcodeOne(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble order by sub_local_a asc", null);

        return res;
    }

    public Cursor getOrderbyBarcodeTwo(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble order by sub_local_b asc", null);

        return res;
    }


    public Cursor getBarcodeOneInfo(long code)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble where sub_local_a=" + code + "", null);
        return res;
    }

    public Cursor searchName(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble where description like '%" + name + "%'", null);
        return res;
    }

    public Cursor searchBarcodeOne(String barcode){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble where sub_local_a like '%"+ barcode + "%'",null);
        return res;
    }

    public Cursor searchBarcodeTwo(String barcode){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble where sub_local_b like '%"+ barcode + "%'",null);
        return res;
    }

    public Cursor searchLocation(String location){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble where location like '%"+ location + "%'",null);
        return res;
    }

    public Cursor getWholeList(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble",null);
        return res;
    }

    public void updateDescription(int id, String description)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("description",description);

        db.update("gamble",values,"id = ?",new String[]{Integer.toString(id)});
    }

    public long updateLocation(int id, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("location",location);

        return db.update("gamble",values,"id = ?", new String[]{Integer.toString(id)});

    }

    public void removeItem(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ITEMS_TABLE_NAME, "description = ?",new String[]{name});
    }

}
