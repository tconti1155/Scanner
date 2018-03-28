package com.contiandsons.thomas.scanner;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import java.io.File;

/**
 * Created by Thomas on 4/8/2017.
 *
 * This activity creates the database to store the bar codes and information
 */


public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Scanner.db"; // Assigning the database
    public static final String ITEMS_TABLE_NAME = "gamble"; // Naming the table
    public static final String ITEMS_COLUMN_ID = "id"; // Column for id of items in the database
    public static final String ITEMS_COLUMN_LOCATION = "location"; // Creating a location column
    public static final String ITEMS_COLUMN_SUBLOCALA = "sub_local_a"; // Creating a the first barcode column
    public static final String ITEMS_COLUMN_SUBLOCALB = "sub_local_b"; // Creating the second barcode column
    public static final String ITEMS_COLUMN_DESCRIPTION = "description"; // Creating the description column

    // Creating the table in the database.
    public static final String CREATE_TABLE_1 = "create table gamble"+
            "(id integer primary key, location text, sub_local_a long, sub_local_b long, description text)";


    public Database(Context context){
        super(context, DATABASE_NAME, null,1);
    }

    // Creating the database
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE_1);
    }

    // Upgrading the information in the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("drop table if exists gamble ");
        onCreate(db);
    }

    // Closing the table
    public boolean tableExist(){
        File dbtest = new File("/data/data/com.contiandsons.thomas.scanner/databases/Scanner.db");
        if (dbtest.exists()) {
            return true;
        } else {
            return false;
        }
    }

    // Inserting information into the table
    public boolean insertReg(String local,String subLocalA, long subLocalB,String descrip){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contextValues = new ContentValues();

        contextValues.put("location",local);
        contextValues.put("sub_local_a",subLocalA);
        contextValues.put("sub_local_b", subLocalB);
        contextValues.put("description",descrip);

        db.insert("gamble",null,contextValues);
        return true;
    }

    // Calling for the information from the table in order by Description
    public Cursor getOrderByDescription()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble order by description asc",null);

        return res;

    }

    // Calling for the information from the table by order of location
    public Cursor getOrderByLocation(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble order by location asc",null);

        return res;
    }

    // Calling for the information from the table by order of the first barcode column
    public Cursor getOrderByBarcodeOne(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble order by sub_local_a asc", null);

        return res;
    }

    // Calling for the information from the table by order of the second barcode column
    public Cursor getOrderbyBarcodeTwo(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble order by sub_local_b asc", null);

        return res;
    }

    // Calling for the information of a specific  barcode in the table
    public Cursor getBarcodeOneInfo(String code)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble where sub_local_a=" + code + "", null);
        return res;
    }

    // Calling for the information of a specific Description
    public Cursor searchDescription(String Description){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble where description like '%" + Description + "%'", null);
        return res;
    }

    // Looking for barcodes with similar numbers in the first barcode column
    public Cursor searchBarcodeOne(String barcode){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble where sub_local_a like '%"+ barcode + "%'",null);
        return res;
    }

    // Looking for barcodes with similar numbers in the second barcode column
    public Cursor searchBarcodeTwo(String barcode){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble where sub_local_b like '%"+ barcode + "%'",null);
        return res;
    }

    // Looking for items with same location
    public Cursor searchLocation(String location){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble where location like '%"+ location + "%'",null);
        return res;
    }

    // Calling for the whole list in the table
    public Cursor getWholeList(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from gamble",null);
        return res;
    }

    // Updating the description of an item
    public void updateDescription(int id, String description)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("description",description);

        db.update("gamble",values,"id = ?",new String[]{Integer.toString(id)});
    }

    // Updating the location of an item
    public long updateLocation(int id, String location){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("location",location);

        return db.update("gamble",values,"id = ?", new String[]{Integer.toString(id)});

    }

    // Removing an item from the table
    public void removeItem(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(ITEMS_TABLE_NAME, "description = ?",new String[]{name});
    }

}
