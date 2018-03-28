package com.contiandsons.thomas.scanner;


import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageButton;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/*
    This Activity is the Main Activity It has all tge options available for users
    to scan bar codes and thus access information in different ways
 */

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private ImageButton scanBtn,listBtn,sortList,removeBtn,searchBtn,updateBtn,fileDownload;    // Creating Image Buttons
    boolean isMinimized;                                                                        // Creating a boolean to
    private static final String TAG = "MainActivity";                                           // Creating a tag for mainActivity
    private AdView mAdView;                                                                     // For creating ads
    public Database db = new Database(this);                                            // Creating variables for various fragment classes
    public DisplayWholeList displayWholeList = new DisplayWholeList();
    public SortFragment sortFragment = new SortFragment();
    public RemoveItem removeItem = new RemoveItem();
    public SearchChoice searchChoice = new SearchChoice();
    public UpdateFragment updateFragment = new UpdateFragment();
    public ExportFile exportFile = new ExportFile();
    public Fragment fragment = null;


    // Creating buttons for all users options
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        scanBtn = (ImageButton) findViewById(R.id.button);
        listBtn = (ImageButton) findViewById(R.id.button2);
        sortList = (ImageButton) findViewById(R.id.button3);
        removeBtn = (ImageButton) findViewById(R.id.button4);
        searchBtn = (ImageButton) findViewById(R.id.button5);
        updateBtn = (ImageButton) findViewById(R.id.button6);
        fileDownload = (ImageButton) findViewById(R.id.button7);



        scanBtn.setOnClickListener(this);
        listBtn.setOnClickListener(this);
        sortList.setOnClickListener(this);
        removeBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        updateBtn.setOnClickListener(this);
        fileDownload.setOnClickListener(this);

        // Getting support for calling the DisplayWholeList Class
        fragment = getSupportFragmentManager().findFragmentByTag("displayWholeList");

    }

    // Actions taken based on a button click
    public void onClick(View v) {
        // Calls the scanning activity when button is pressed
        if (v.getId() == R.id.button) {
            Intent intent = new Intent(MainActivity.this, Scanner.class);
            startActivity(intent);
        }

        // Displaying a all the items in database when button 2 is pressed
        else if (v.getId() == R.id.button2) {
            if (displayWholeList.clearGrid() == true) {
                displayWholeList.loadFiles();
                displayWholeList.getWholeList(db.getWholeList());
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_main, displayWholeList)
                        .commit();
            }

        }

        // Calls for sorting fragment
        else if (v.getId() == R.id.button3) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_main, sortFragment)
                    .commit();
        }

        // Calls for the removing item fragment
        else if (v.getId() == R.id.button4) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_main, removeItem)
                    .commit();
        }

        // Calling for the search fragment
        else if (v.getId() == R.id.button5) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_main, searchChoice)
                    .commit();
        }

        // Calls the update fragment
        else if (v.getId() == R.id.button6) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_main, updateFragment)
                    .commit();
        }

        // Calls the exporting files fragment
        else if (v.getId() == R.id.button7) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_main, exportFile)
                    .commit();
        }
    }

    // Used to start the splash screen
    @Override
    protected void onStart() {
        super.onStart();
        if (isMinimized) {
            startActivity(new Intent(this, SplashScreen.class));
            isMinimized = false;
            finish();
        }
    }

    // Used to start the splash screen
    @Override
    protected void onResume() {
        super.onResume();
        if (isMinimized) {
            startActivity(new Intent(this, SplashScreen.class));
            isMinimized = false;
            finish();
        }
    }
}

