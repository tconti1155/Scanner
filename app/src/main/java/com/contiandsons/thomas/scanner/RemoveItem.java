package com.contiandsons.thomas.scanner;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Thomas on 6/4/2017.
 */

public class RemoveItem extends Fragment implements View.OnClickListener{

    Button returnMenu, removeItem;
    EditText removeItemText;
    Database database;
    private Context context;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = layoutInflater.inflate(R.layout.remove_items, container, false);
        database = new Database(getActivity());

        removeItem = (Button) view.findViewById(R.id.returnMain1);
        returnMenu = (Button) view.findViewById(R.id.removeItem);
        removeItemText = (EditText) view.findViewById(R.id.removeItemText);

        removeItem.setOnClickListener(this);
        returnMenu.setOnClickListener(this);
        removeItemText.setOnClickListener(this);

        return view;

    }

    public void onClick(View view){
        if(view.getId()==R.id.returnMain1) {
            Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
        else if(view.getId()== R.id.removeItem)
        {
           try{
                database.removeItem(removeItemText.getText().toString());

            }catch(Exception e) {
                Toast.makeText(getActivity(),"Item was not Removed",Toast.LENGTH_LONG).show();
            }
            finally {
               Toast.makeText(getActivity(),removeItemText.getText().toString() + " has been removed",Toast.LENGTH_LONG).show();
           }

        }
    }
    public void RemoveItem(){
        context=getActivity().getApplicationContext();
    }
}
