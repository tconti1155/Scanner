package com.contiandsons.thomas.scanner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Thomas on 6/15/2017.
 */

public class SortFragment extends Fragment implements View.OnClickListener {

    Button sortLocation,sortName,sortBarcodeOne,sortBarcodeTwo,returnMain;
    Database database;
    DisplayWholeList displayWholeList = new DisplayWholeList();
    public Context context;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.sort_items,container,false);

        database = new Database(getActivity());

        sortLocation = (Button) view.findViewById(R.id.sort_by_location);
        sortName = (Button) view.findViewById(R.id.sort_by_name);
        sortBarcodeOne = (Button) view.findViewById(R.id.sort_by_barcode_one);
        sortBarcodeTwo = (Button) view.findViewById(R.id.sort_by_barcode_two);
        returnMain = (Button) view.findViewById(R.id.returnMain3);

        sortLocation.setOnClickListener(this);
        sortName.setOnClickListener(this);
        sortBarcodeOne.setOnClickListener(this);
        sortBarcodeTwo.setOnClickListener(this);
        returnMain.setOnClickListener(this);

        return view;
    }

    public void onClick(View view)
    {

        displayWholeList.clearGrid();
        displayWholeList.loadFiles();
      /*  FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();*/
        if(view.getId() == R.id.sort_by_name)
        {
            displayWholeList.getSortedList(database.getOrderByName());
            nextFragment();
        }
        else if(view.getId()== R.id.sort_by_barcode_one)
        {
            displayWholeList.getSortedList(database.getOrderByBarcodeOne());
            nextFragment();
        }
        else if(view.getId()==R.id.sort_by_barcode_two)
        {
            displayWholeList.getSortedList(database.getOrderbyBarcodeTwo());
            nextFragment();
        }
        else if(view.getId() == R.id.sort_by_location)
        {

            displayWholeList.getSortedList(database.getOrderByLocation());
            nextFragment();
        }

    }

    public void SortFragment(){context = getActivity().getApplicationContext();}

    public void nextFragment(){
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(((ViewGroup)getView().getParent()).getId(),displayWholeList);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}
