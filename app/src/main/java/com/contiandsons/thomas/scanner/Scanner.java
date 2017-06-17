package com.contiandsons.thomas.scanner;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentResult;

import com.google.zxing.integration.android.IntentIntegrator;


/**
 * Created by Thomas on 4/8/2017.
 */

public class Scanner extends FragmentActivity implements View.OnClickListener{
    private Button scanBtn, resultsBtn;
    private EditText local, descripition;
    public Cursor cursor;
    public Database db = new Database(this);
    public ScanFragment scanFragment = new ScanFragment();
    public Fragment fragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner);

        scanBtn = (Button) findViewById(R.id.scan_button);
        resultsBtn = (Button) findViewById(R.id.results);
        local = (EditText) findViewById(R.id.editText);
        descripition = (EditText) findViewById(R.id.editText2);
        scanBtn.setOnClickListener(this);
        resultsBtn.setOnClickListener(this);
        fragment = getSupportFragmentManager().findFragmentByTag("scanFragment");}

        public void onClick(View v) {
            if (v.getId() == R.id.scan_button) {
                IntentIntegrator scanIntergrator = new IntentIntegrator(this);
                scanIntergrator.initiateScan();
            } else {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_scan, scanFragment)
                        .commit();
            }
        }




    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
       long i = 0;
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if(scanningResult != null)
        {

        }
        else{
            Toast toast = Toast.makeText(getApplicationContext(),
            "No Scan Data Received!", Toast.LENGTH_SHORT);
            toast.show();
        }
        String scanContent = scanningResult.getContents();
        i = Long.parseLong(scanContent);
        db.insertReg(local.getText().toString(),i,0,descripition.getText().toString());

        scanFragment.get(db.getBarcodeOneInfo(i));


    }
}
