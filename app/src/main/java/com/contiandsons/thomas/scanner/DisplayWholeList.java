package com.contiandsons.thomas.scanner;

import android.content.Intent;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.GridLayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Thomas on 5/17/2017.
 */

public class DisplayWholeList extends Fragment implements View.OnClickListener{

    String location, description;
    static final ArrayList<String> items = new ArrayList<String>();
    long barcode1, barcode2;
    GridView gridView;
    TextView Location, Description, Barcode1, Barcode2;
    Button button;
    int num;
    Context context;
    Database db = new Database(context);

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_view, container, false);

        gridView = (GridView) view.findViewById(R.id.gridView);
        button = (Button)view.findViewById(R.id.MainMenu);

        button.setOnClickListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, items);

        gridView.setAdapter(adapter);
        return view;
    }

    public void onClick(View view){
        Intent intent = new Intent(getActivity(),MainActivity.class);
        startActivity(intent);
    }

    public void getWholeList(Cursor cursor) {
        int num = 0;
        cursor.moveToFirst();


            if (cursor.getCount() > 0){
                do {
                    location = cursor.getString(cursor.getColumnIndex("location"));
                    barcode1 = cursor.getLong(cursor.getColumnIndex("sub_local_a"));
                    barcode2 = cursor.getLong(cursor.getColumnIndex("sub_local_b"));
                    description = cursor.getString(cursor.getColumnIndex("description"));

                    items.add(location);

                    items.add(Long.toString(barcode1));
                    items.add(Long.toString(barcode2));
                    items.add(description);


                } while (cursor.moveToNext());
        }

    }

    public void DisplayWholeList() {
        context = getActivity().getApplicationContext();
    }

    public void loadFiles() {
        items.add("Location");
        items.add("Bar Code 1");
        items.add("Bar Code 2");
        items.add("Description");

    }

    public void getSortedList(Cursor cursor){
        cursor.moveToFirst();

        if (cursor.getCount() > 0){
            do {
                location = cursor.getString(cursor.getColumnIndex("location"));
                barcode1 = cursor.getLong(cursor.getColumnIndex("sub_local_a"));
                barcode2 = cursor.getLong(cursor.getColumnIndex("sub_local_b"));
                description = cursor.getString(cursor.getColumnIndex("description"));

                items.add(location);

                items.add(Long.toString(barcode1));
                items.add(Long.toString(barcode2));
                items.add(description);


            } while (cursor.moveToNext());
        }


    }

    public boolean clearGrid(){
        items.clear();
        return true;
    }

}
