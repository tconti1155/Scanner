package com.contiandsons.thomas.scanner;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by Thomas on 6/11/2017.
 */

public class SearchChoice extends Fragment implements View.OnClickListener{

    ImageButton search, returnMain;
    CheckBox searchName,searchBarcode1,searchLocation;
    EditText searchText;
    Database database;
    private Context context;
    DisplayWholeList displayWholeList = new DisplayWholeList();

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
        displayWholeList.clearGrid();
        displayWholeList.loadFiles();
        if(view.getId()==R.id.search){
            if(searchName.isChecked())
            {
                displayWholeList.getWholeList(database.searchName(searchText.getText().toString()));
            }
            else if(searchBarcode1.isChecked())
            {
                displayWholeList.getWholeList(database.searchBarcodeOne(searchText.getText().toString()));
            }
            else if(searchLocation.isChecked())
            {
                displayWholeList.getWholeList(database.searchLocation(searchText.getText().toString()));
            }
            nextFragment();
        }
        else if(view.getId()==R.id.returnMain2){
            Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
        }
    }
    public void nextFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(((ViewGroup)getView().getParent()).getId(),displayWholeList);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }

    public void SearchChoice(){context = getActivity().getApplicationContext();}


}
