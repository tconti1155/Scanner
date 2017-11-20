package com.contiandsons.thomas.scanner;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Thomas on 6/18/2017.
 */

public class ExportFile extends Fragment implements View.OnClickListener{
    String fileName = "MyDatabase.odt";
    String fileName1 = "MyDatabase.ods";
    FileOutputStream fileOutputStream;
    ImageButton docFile, sheetsFile, returnMain;
    File file;
    Database database;
    Cursor cursor;
    Context context;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container,  Bundle instanceSavedState) {

        View view = layoutInflater.inflate(R.layout.export_files,container,false);

        database = new Database(getActivity());

        docFile = (ImageButton) view.findViewById(R.id.doc_file);
        sheetsFile = (ImageButton) view.findViewById(R.id.Excel_File);
        returnMain = (ImageButton) view.findViewById(R.id.returnMain5);

        docFile.setOnClickListener(this);
        sheetsFile.setOnClickListener(this);
        returnMain.setOnClickListener(this);

        return view;
    }

    public void onClick(View view){
        if(view.getId()==R.id.doc_file){
            try {
                DocFile();
            }
            catch (Exception e){
                Toast.makeText(getActivity(),"File was unable to be saved", Toast.LENGTH_LONG).show();
            }
            finally {
                Toast.makeText(getActivity(),"File named: MyDatabase.doc was created in Downloads Folder", Toast.LENGTH_LONG).show();
            }
        }
        else if(view.getId()==R.id.Excel_File){
            try{
                ExcelFile();
            }
            catch (Exception e)
            {
                Toast.makeText(getActivity(),"File was unable to be saved", Toast.LENGTH_LONG).show();
            }
            finally {
                Toast.makeText(getActivity(),"File named: MyDatabase.xls was created in Downloads Folder", Toast.LENGTH_LONG).show();
            }
        }
        else if(view.getId()==R.id.returnMain5){
            Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
        }

    }

    public void ExportFile() {
        context = getActivity().getApplicationContext();
    }

    public void DocFile() {
        try {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write("Location".getBytes());
            fileOutputStream.write(" ".getBytes());
            fileOutputStream.write("Barcode One".getBytes());
            fileOutputStream.write(" ".getBytes());
            fileOutputStream.write("Description".getBytes());
            fileOutputStream.write("\n".getBytes());
            cursor = database.getWholeList();
            cursor.moveToFirst();

            if (cursor.getCount() > 0) {
                do {
                    Long temp = cursor.getLong(cursor.getColumnIndex("sub_local_a"));

                    fileOutputStream.write(cursor.getString(cursor.getColumnIndex("location")).getBytes());
                    fileOutputStream.write(" ".getBytes());
                    fileOutputStream.write(temp.toString().getBytes());
                    fileOutputStream.write(" ".getBytes());
                    fileOutputStream.write(cursor.getString(cursor.getColumnIndex("description")).getBytes());
                    fileOutputStream.write(" ".getBytes());
                } while (cursor.moveToNext());
            }
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ExcelFile(){
        try {
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName1);
            fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write("Location".getBytes());
            fileOutputStream.write("\t".getBytes());
            fileOutputStream.write("Barcode One".getBytes());
            fileOutputStream.write("\t".getBytes());
            fileOutputStream.write("Description".getBytes());
            fileOutputStream.write("\n".getBytes());
            cursor = database.getWholeList();
            cursor.moveToFirst();

            if (cursor.getCount() > 0) {
                do {
                    Long temp = cursor.getLong(cursor.getColumnIndex("sub_local_a"));

                    fileOutputStream.write(cursor.getString(cursor.getColumnIndex("location")).getBytes());
                    fileOutputStream.write("\t".getBytes());
                    fileOutputStream.write(temp.toString().getBytes());
                    fileOutputStream.write("\t".getBytes());
                    fileOutputStream.write(cursor.getString(cursor.getColumnIndex("description")).getBytes());
                    fileOutputStream.write("\n".getBytes());
                } while (cursor.moveToNext());
            }
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


