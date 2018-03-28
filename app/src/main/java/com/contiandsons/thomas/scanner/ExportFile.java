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
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Thomas on 6/18/2017.
 *
 * This Fragment allows the user to export the Database table
 */

public class ExportFile extends Fragment implements View.OnClickListener{
    String fileName = "MyDatabase.odt";         // Creating a filename for Document file
    String fileName1 = "MyDatabase.ods";        // Creating a filename for excel table
    FileOutputStream fileOutputStream;          // For outputting files
    ImageButton docFile, sheetsFile, returnMain; // Creating buttons for doc, sheets, return to main
    File file;                                  // File variable
    Database database;                          // Database variable
    Cursor cursor;                              // Cursor variable
    Context context;                            // Context variable


    // Setting up image buttons
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

    // Sets up outputting document files, sheet files or returning to main menu
    public void onClick(View view){

        // For when the doc button is pressed
        if(view.getId()==R.id.doc_file){
            try {
                DocFile();
            }
            // Letting user know if the file can not be saved
            catch (Exception e){
                Toast.makeText(getActivity(),"File was unable to be saved", Toast.LENGTH_LONG).show();
            }
            // Letting the user know where the file is located
            finally {
                Toast.makeText(getActivity(),"File named: MyDatabase.doc was created in Downloads Folder", Toast.LENGTH_LONG).show();
            }
        }
        // For when the Excel button is pressed
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
        // For when the return to main menu button is pushed
        else if(view.getId()==R.id.returnMain5){
            Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
        }

    }

    public void ExportFile() {
        context = getActivity().getApplicationContext();
    }


    // Exporting to a document file
    public void DocFile() {
        try {

            // Writing the beginning of the document file
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

            // Taking the files from the database and writing them to document file
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

   // Exporting the Excel file
    public void ExcelFile(){
        try {
            // Writing the beginning of the excel file
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


            // Taking the files from the database and writing them to excel file
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


