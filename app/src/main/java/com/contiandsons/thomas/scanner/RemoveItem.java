package com.contiandsons.thomas.scanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * Created by Thomas on 6/4/2017.
 *
 * This Fragment class is for removing an item form the database
 */

public class RemoveItem extends Fragment implements View.OnClickListener{

    ImageButton returnMenu, removeItem;     // Creating button variables
    EditText removeItemText;                // Creating Edit text variables
    Database database;                      // Creating a database variable
    private Context context;                // Context variable


    // Setting up buttons and onclicks
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = layoutInflater.inflate(R.layout.remove_items, container, false);
        database = new Database(getActivity());

        removeItem = (ImageButton) view.findViewById(R.id.returnMain1);
        returnMenu = (ImageButton) view.findViewById(R.id.removeItem);
        removeItemText = (EditText) view.findViewById(R.id.removeItemText);

        removeItem.setOnClickListener(this);
        returnMenu.setOnClickListener(this);
        removeItemText.setOnClickListener(this);

        return view;

    }

    // Actions to be taken when a button is clicked
    public void onClick(View view){

        // Return to main menu
        if(view.getId()==R.id.returnMain1) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }

        // Removing an item
        else if(view.getId()== R.id.removeItem)
        {
           try{
               // Try to see if a item can be removed
                database.removeItem(removeItemText.getText().toString());

            }catch(Exception e) {
               // Letting the user know the item is not there to be remvoed
                Toast.makeText(getActivity(),"Item was not Removed",Toast.LENGTH_LONG).show();
            }
            finally {
               // Letting the user know the item has been removed
               Toast.makeText(getActivity(),removeItemText.getText().toString() + " has been removed",Toast.LENGTH_LONG).show();
           }

        }
    }

    // Able to call back to the main activity
    public void RemoveItem(){
        context=getActivity().getApplicationContext();
    }
}
