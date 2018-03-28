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
import android.widget.ImageButton;

/**
 * Created by Thomas on 6/15/2017.
 * A fragment to show a sorted list of the database
 */

public class SortFragment extends Fragment implements View.OnClickListener {

    ImageButton sortLocation,sortName,sortBarcodeOne,returnMain;    // Creating image buttons
    Database database;                                              // Calling for the database
    DisplayWholeList displayWholeList = new DisplayWholeList();     // Calling for the DisplayWholeList class
    public Context context;


    // Creating the images buttons and onclicks
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.sort_items,container,false);

        database = new Database(getActivity());

        sortLocation = (ImageButton) view.findViewById(R.id.sort_by_location);
        sortName = (ImageButton) view.findViewById(R.id.sort_by_name);
        sortBarcodeOne = (ImageButton) view.findViewById(R.id.sort_by_barcode_one);
        returnMain = (ImageButton) view.findViewById(R.id.returnMain3);

        sortLocation.setOnClickListener(this);
        sortName.setOnClickListener(this);
        sortBarcodeOne.setOnClickListener(this);
        returnMain.setOnClickListener(this);

        return view;
    }


    // Setting tasks for when buttons are clicked
    public void onClick(View view)
    {
        // Setting up the grid for the list
        displayWholeList.clearGrid();
        displayWholeList.loadFiles();

        // For sorting by description
        if(view.getId() == R.id.sort_by_name)
        {
            displayWholeList.getSortedList(database.getOrderByDescription());
            nextFragment();
        }

        // For sorting by the first barcode
        else if(view.getId()== R.id.sort_by_barcode_one)
        {
            displayWholeList.getSortedList(database.getOrderByBarcodeOne());
            nextFragment();
        }
        // For sorting by location
        else if(view.getId() == R.id.sort_by_location)
        {

            displayWholeList.getSortedList(database.getOrderByLocation());
            nextFragment();
        }

        // For returning to main menu
        else if(view.getId()==R.id.returnMain3){
            Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
        }

    }

    // Used for calling back to the main activity
    public void SortFragment(){context = getActivity().getApplicationContext();}

    // Called for using the DisplayWholeList fragment
    public void nextFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(((ViewGroup)getView().getParent()).getId(),displayWholeList);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
