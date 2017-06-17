package com.contiandsons.thomas.scanner;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Thomas on 6/16/2017.
 */

public class UpdateFragment extends Fragment implements View.OnClickListener{

    Button updateName,updateLocation;
    EditText oldText, newText;
    Database database;
    Context context;
    Cursor cursor;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = layoutInflater.inflate(R.layout.update_item,container,false);
        database = new Database(getActivity());

        updateName = (Button) view.findViewById(R.id.update_name);
        updateLocation = (Button) view.findViewById(R.id.update_location);

        oldText = (EditText) view.findViewById(R.id.old_text);
        newText = (EditText) view.findViewById(R.id.update_text);

        updateName.setOnClickListener(this);
        updateLocation.setOnClickListener(this);

        oldText.setOnClickListener(this);
        newText.setOnClickListener(this);


        return view;

    }

    public void onClick(View view){
        if(view.getId()==R.id.update_name){
            pullDataName();
            Toast.makeText(getActivity(),"Description has been updated with:" +
            newText.getText().toString(),Toast.LENGTH_LONG).show();
        }
        else{
            pullDataLocation();
            Toast.makeText(getActivity(),"Location has been updated with:" +
                    newText.getText().toString(),Toast.LENGTH_LONG).show();
        }

    }

    public void UpdateFragment(){context = getActivity().getApplicationContext();}

    public void pullDataName(){
        cursor = database.searchName(oldText.getText().toString());
        cursor.moveToFirst();
        database.updateDescription(cursor.getInt(cursor.getColumnIndex("id")),newText.getText().toString());
    }

    public  void pullDataLocation(){
        cursor = database.searchLocation(oldText.getText().toString());
        cursor.moveToFirst();
        database.updateLocation(cursor.getInt(cursor.getColumnIndex("id")),newText.getText().toString());
    }
}
