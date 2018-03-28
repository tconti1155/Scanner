package com.contiandsons.thomas.scanner;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Thomas on 6/16/2017.
 *
 * This Fragment updates any information that is in a line in the database
 */

public class UpdateFragment extends Fragment implements View.OnClickListener{

    ImageButton updateName,updateLocation, returnMain;      // Image button variables
    EditText oldText, newText;                              // Edit text variables
    Database database;                                      // Database variables
    Context context;                                        // Context variables
    Cursor cursor;                                          // Cursor variable
    long temp;                                              // Long variable

   // Creating the update view and buttons
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = layoutInflater.inflate(R.layout.update_item,container,false);
        database = new Database(getActivity());

        temp = 0 ;

        updateName = (ImageButton) view.findViewById(R.id.update_name);
        updateLocation = (ImageButton) view.findViewById(R.id.update_location);
        returnMain = (ImageButton) view.findViewById(R.id.returnMain4);

        oldText = (EditText) view.findViewById(R.id.old_text);
        newText = (EditText) view.findViewById(R.id.update_text);

        updateName.setOnClickListener(this);
        updateLocation.setOnClickListener(this);
        returnMain.setOnClickListener(this);

        oldText.setOnClickListener(this);
        newText.setOnClickListener(this);


        return view;

    }

    // Actions taken using  based on button presses
    public void onClick(View view){

        //Update the description of an item
        if(view.getId()==R.id.update_name){
            pullDataName();
            Toast.makeText(getActivity(),"Description has been updated with: " +
            newText.getText().toString(),Toast.LENGTH_LONG).show();
        }
        // Update the location of an item
        else if(view.getId()==R.id.update_location){
            pullDataLocation();
            Toast.makeText(getActivity(),"Location has been updated with: " +
                    newText.getText().toString() + " " + temp,Toast.LENGTH_LONG).show();
        }

        // Return to main menu
        else{
            Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
        }

     }

     // Used for going back to Main class
    public void UpdateFragment(){context = getActivity().getApplicationContext();}

    // Pulling information from database table description column then inserting new information
    public void pullDataName(){
        cursor = database.searchDescription(oldText.getText().toString());
        cursor.moveToFirst();
        database.updateDescription(cursor.getInt(cursor.getColumnIndex("id")),newText.getText().toString());
    }

    // Pulling information from database table location colunm then inserting new information
    public  void  pullDataLocation(){
        cursor = database.searchLocation(oldText.getText().toString());
        cursor.moveToFirst();
        temp = database.updateLocation(cursor.getInt(cursor.getColumnIndex("id")),newText.getText().toString());
    }
}
