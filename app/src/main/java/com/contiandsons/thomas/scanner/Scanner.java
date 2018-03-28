package com.contiandsons.thomas.scanner;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentResult;       // Used for getting the results
import com.google.zxing.integration.android.IntentIntegrator;  // Used to call the camera for scanning


/**
 * Created by Thomas on 4/8/2017.
 *
 * This Activity using the xzing library to scan bar codes and saves the data
 */

public class Scanner extends FragmentActivity implements View.OnClickListener{
    private ImageButton scanBtn, resultsBtn, returnMain;        // Image Button variables
    private EditText local, descripition;                       // Edit text variables
    public Database db = new Database(this);            // Database variable
    public ScanFragment scanFragment = new ScanFragment();      // Scan variable
    public Fragment fragment = null;                            // Fragment Variable


    // Creating Image buttons and setting up the onclicks
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.scanner);

        scanBtn = (ImageButton) findViewById(R.id.scan_button);
        resultsBtn = (ImageButton) findViewById(R.id.results);
        returnMain = (ImageButton) findViewById(R.id.returnMain6);
        local = (EditText) findViewById(R.id.editText);
        descripition = (EditText) findViewById(R.id.editText2);

        scanBtn.setOnClickListener(this);
        resultsBtn.setOnClickListener(this);
        returnMain.setOnClickListener(this);

        fragment = getSupportFragmentManager().findFragmentByTag("scanFragment");}


        // Actions to be taken when a button is clicked
        public void onClick(View v) {

            // Calls for the camera so tge barcode can be scanned
            if (v.getId() == R.id.scan_button) {
                try {
                    IntentIntegrator scanIntergrator = new IntentIntegrator(this);
                    scanIntergrator.initiateScan();
                }
                catch(Exception e)
                {
                    // Lets the user know if the scan is not possible
                    Toast.makeText(Scanner.this,"Scan was not possible!",Toast.LENGTH_LONG).show();
                }
            }

            // Brings up the results from the scan
            else if(v.getId()== R.id.results){
                try{
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.fragment_scan, scanFragment)
                            .commit();}
                catch(Exception e)
                {
                    // Lets the user know the check was not possible
                    Toast.makeText(Scanner.this,"Scan check was not possible!",Toast.LENGTH_LONG).show();
                }

            }

            // Returns to main menu
            else if(v.getId()==R.id.returnMain6){
                Intent intent = new Intent(Scanner.this,MainActivity.class);
                startActivity(intent);
            }
        }

    // Takes the information from the scan and uses moves into the database
    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {

        // Retrieving the results from scan
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);


        // Tries to input data into the database and scanFragment
        try
        {
            db.insertReg(local.getText().toString(),scanningResult.getContents(),0,descripition.getText().toString());

            scanFragment.get(db.getBarcodeOneInfo(scanningResult.getContents()));
        }
        catch (Exception e) {

            // Letting the user know the data was not scanned
            Toast toast = Toast.makeText(getApplicationContext(),
            "No Scan Data Received!", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
