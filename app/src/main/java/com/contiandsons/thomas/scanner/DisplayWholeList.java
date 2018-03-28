package com.contiandsons.thomas.scanner;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import java.util.ArrayList;

/**
 * Created by Thomas on 5/17/2017.
 * A fragment for displaying the whole list
 */

public class DisplayWholeList extends Fragment implements View.OnClickListener{

    String location, description;                                   //Creating strings for location and description
    static final ArrayList<String> items = new ArrayList<String>();     // Creating a ArrayList of strings
    long barcode1, barcode2;                                      // Creating a long for barcode 1 and 2
    GridView gridView;                                           // Creating a gridView
    ImageButton button;                                         // Creating a image button
    Context context;


    // Creating the gridView and image button for Display whole list fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);

        gridView = (GridView) view.findViewById(R.id.gridView);
        button = (ImageButton)view.findViewById(R.id.MainMenu);

        button.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);

        gridView.setAdapter(adapter);
        return view;
    }

    // Calling for when user clicks on the button
    public void onClick(View view){
        Intent intent = new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
    }

    // Calling for the whole list in the database
    public void getWholeList(Cursor cursor) {

        cursor.moveToFirst();

            // Checking to see if there are item in the table
            if (cursor.getCount() > 0){

                // Looping through all the items in cursor
                do {

                    // Retrieving the information from cursor and assigning to locaton, barcode 1 and description
                    location = cursor.getString(cursor.getColumnIndex("location"));
                    barcode1 = cursor.getLong(cursor.getColumnIndex("sub_local_a"));
                    description = cursor.getString(cursor.getColumnIndex("description"));

                    // Adding location, barcode1 and description functions to the item arraylist
                    items.add(location);
                    items.add(Long.toString(barcode1));
                    items.add(description);


                } while (cursor.moveToNext());
        }

    }

    // For calling back to Main Activity
    public void DisplayWholeList()
    {
        context = getActivity().getApplicationContext();
    }

    // Loading the names at the top of the grid for each column
    public void loadFiles() {
        items.add("Location");
        items.add("Barcode");
        items.add("Description");

    }

    // Calling for a sorted list from table
    public void getSortedList(Cursor cursor){
        cursor.moveToFirst();

        // Checking the count
        if (cursor.getCount() > 0) {

            // Looping through all the items in cursor
            do {
                // Retrieving the information from cursor and assigning to location, barcode 1 and description
                location = cursor.getString(cursor.getColumnIndex("location"));
                barcode1 = cursor.getLong(cursor.getColumnIndex("sub_local_a"));
                description = cursor.getString(cursor.getColumnIndex("description"));

                // Adding location, barcode1 and description functions to the item arraylist
                items.add(location);
                items.add(Long.toString(barcode1));
                items.add(description);


            } while (cursor.moveToNext());
        }
    }


    // Clearing out the grid
    public boolean clearGrid(){
        items.clear();
        return true;
    }

}
