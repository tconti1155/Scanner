package com.contiandsons.thomas.scanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by Thomas on 6/11/2017.
 *
 * This fragment allows the user search items in the database
 */

public class SearchChoice extends Fragment implements View.OnClickListener{

    ImageButton search, returnMain;                         // Image button variables
    CheckBox searchName,searchBarcode1,searchLocation;      // Check box variables
    EditText searchText;                                    // Edit text variable
    Database database;                                      // Database variable
    private Context context;                                // Context variable
    DisplayWholeList displayWholeList = new DisplayWholeList(); // Display Whole List Variable


    // Creating buttons, check boxes and onclicks
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = layoutInflater.inflate(R.layout.search_items,container,false);

        database = new Database(getActivity());

        searchName = (CheckBox) view.findViewById(R.id.search_by_name);
        searchBarcode1 = (CheckBox) view.findViewById(R.id.search_by_barcode_one);
        searchLocation = (CheckBox) view.findViewById(R.id.search_by_location);

        returnMain = (ImageButton) view.findViewById(R.id.returnMain2);
        search = (ImageButton) view.findViewById(R.id.search);

        searchText = (EditText) view.findViewById(R.id.search_text);

        searchName.setOnClickListener(this);
        searchBarcode1.setOnClickListener(this);
        searchLocation.setOnClickListener(this);
        returnMain.setOnClickListener(this);
        search.setOnClickListener(this);
        searchText.setOnClickListener(this);


        return view;
    }

    public void onClick(View view)
    {
        // Setting up the display grid
        displayWholeList.clearGrid();
        displayWholeList.loadFiles();

        // Searching by description
        if(view.getId()==R.id.search){
            if(searchName.isChecked())
            {
                displayWholeList.getWholeList(database.searchDescription(searchText.getText().toString()));
            }

            //Searches using the first bar code column
            else if(searchBarcode1.isChecked())
            {
                displayWholeList.getWholeList(database.searchBarcodeOne(searchText.getText().toString()));
            }

            //Searches by location
            else if(searchLocation.isChecked())
            {
                displayWholeList.getWholeList(database.searchLocation(searchText.getText().toString()));
            }
            nextFragment();
        }

        // Returns to the main menu
        else if(view.getId()==R.id.returnMain2){
            Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
        }
    }


    // This is used to be able display the DisplayWholeList fragment

    public void nextFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(((ViewGroup)getView().getParent()).getId(),displayWholeList);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    // Used for going to back to the Main Activity
    public void SearchChoice(){context = getActivity().getApplicationContext();}


}
