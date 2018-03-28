package com.contiandsons.thomas.scanner;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by Thomas on 5/9/2017.
 *
 * This fragment displays the results from scanning activity
 */

public class ScanFragment extends Fragment implements  View.OnClickListener{
    private TextView location, contentText,descripText;     //Creating  Text View variables
    private ImageButton returnBtn, returnMainBtn;           // Creating Image Button Variables
    private Context context;                                // Context Variable
    private String localText, descrip;                      // String variables for displaying information
    private long barcode1;                                  // Long Variable for displaying a barcode
    public Database db = new Database(context);             //Database variable


    @Nullable
    @Override

    // Creating the Buttons and texts views
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.results, container, false);
        location = (TextView) view.findViewById(R.id.local);
        contentText = (TextView) view.findViewById(R.id.scan_content);
        descripText = (TextView) view.findViewById(R.id.descript);
        returnBtn = (ImageButton) view.findViewById(R.id.results);
        returnMainBtn =(ImageButton) view.findViewById(R.id.returnMain);

        location.setText("Location: " + localText);
        descripText.setText("Description: " + descrip);
        contentText.setText("Barcode: " + barcode1);
        returnBtn.setOnClickListener(this);
        returnMainBtn.setOnClickListener(this);
        return view;
    }

        // Called for when buttons are pressed
        public void onClick(View view){

            // Calling back to Scan Activity
            if(view.getId() == R.id.results) {
                Intent intent = new Intent(getActivity(), Scanner.class);
                startActivity(intent);
            }

            // Return to main menu
            else{
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }

            }





    // For setting up names for each columns
    public void get(Cursor cursor){
        if(db.tableExist()== true) {
            cursor.moveToFirst();
            localText = cursor.getString(cursor.getColumnIndex("location"));
            descrip = cursor.getString(cursor.getColumnIndex("description"));
            barcode1 = cursor.getLong(cursor.getColumnIndex("sub_local_a"));
        }
    }

    // Used for being able to go back Main and Scan Activity
    public void ScanFragment(){
        context=getActivity().getApplicationContext();
    }


}

