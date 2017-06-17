package com.contiandsons.thomas.scanner;

import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Thomas on 5/9/2017.
 */

public class ScanFragment extends Fragment implements  View.OnClickListener{
    private TextView location, contentText,descripText;
    private Button returnBtn, returnMainBtn;
    private Context context;
    private String localText, descrip;
    private long barcode1;
    public Database db = new Database(context);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.results, container, false);
        location = (TextView) view.findViewById(R.id.local);
        contentText = (TextView) view.findViewById(R.id.scan_content);
        descripText = (TextView) view.findViewById(R.id.descript);
        returnBtn = (Button) view.findViewById(R.id.results);
        returnMainBtn =(Button) view.findViewById(R.id.returnMain);

        location.setText("Location: " + localText);
        descripText.setText("Description: " + descrip);
        contentText.setText("Barcode: " + barcode1);
        returnBtn.setOnClickListener(this);
        returnMainBtn.setOnClickListener(this);
        return view;
    }
        public void onClick(View view){
            if(view.getId() == R.id.results) {
                Intent intent = new Intent(getActivity(), Scanner.class);
                startActivity(intent);
            }
            else{
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }

            }




    public void get(Cursor cursor){
        if(db.tableExist()== true) {
            cursor.moveToFirst();
            localText = cursor.getString(cursor.getColumnIndex("location"));
            descrip = cursor.getString(cursor.getColumnIndex("description"));
            barcode1 = cursor.getLong(cursor.getColumnIndex("sub_local_a"));
        }
    }
    public void ScanFragment(){
        context=getActivity().getApplicationContext();
    }


}

