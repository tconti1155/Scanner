package com.contiandsons.thomas.scanner;

import android.app.Activity;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private ImageButton scanBtn;
    private ImageButton listBtn;
    private ImageButton sortList;
    private ImageButton removeBtn;
    private ImageButton searchBtn;
    private ImageButton updateBtn;
    private ImageButton fileDownload;
    boolean isMinimized;
    private static final String TAG = "MainActivity";
    private AdView mAdView;
    public Database db = new Database(this);
    public DisplayWholeList displayWholeList = new DisplayWholeList();
    public SortFragment sortFragment = new SortFragment();
    public RemoveItem removeItem = new RemoveItem();
    public SearchChoice searchChoice = new SearchChoice();
    public UpdateFragment updateFragment = new UpdateFragment();
    public ExportFile exportFile = new ExportFile();
    public Fragment fragment = null;


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

        fragment = getSupportFragmentManager().findFragmentByTag("displayWholeList");


    }

    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            Intent intent = new Intent(MainActivity.this, Scanner.class);
            startActivity(intent);
        } else if (v.getId() == R.id.button2) {
            if (displayWholeList.clearGrid() == true) {
                displayWholeList.loadFiles();
                displayWholeList.getWholeList(db.getWholeList());
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_main, displayWholeList)
                        .commit();
            }

        } else if (v.getId() == R.id.button3) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_main, sortFragment)
                    .commit();
        } else if (v.getId() == R.id.button4) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_main, removeItem)
                    .commit();
        } else if (v.getId() == R.id.button5) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_main, searchChoice)
                    .commit();
        } else if (v.getId() == R.id.button6) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_main, updateFragment)
                    .commit();
        } else if (v.getId() == R.id.button7) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_main, exportFile)
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (isMinimized) {
            startActivity(new Intent(this, SplashScreen.class));
            isMinimized = false;
            finish();
        }
    }

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

