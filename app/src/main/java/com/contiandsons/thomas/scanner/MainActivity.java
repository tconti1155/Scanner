package com.contiandsons.thomas.scanner;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity implements View.OnClickListener{
    private Button scanBtn;
    private Button sortBtn;
    private Button createList;
    private Button exitBtn;
    private Button checkOutBtn;
    private Button infoBtn;
    private Cursor cursor;
    private Boolean checking = false;
    private int loadSwitch = 0;
    public Database db = new Database(this);
    public CheckDatabase dbc = new CheckDatabase(this);
    public DisplayWholeList displayWholeList = new DisplayWholeList();
    public SortFragment sortFragment  = new SortFragment();
    public RemoveItem removeItem = new RemoveItem();
    public SearchChoice searchChoice = new SearchChoice();
    public Fragment fragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scanBtn = (Button)findViewById(R.id.button);
        sortBtn = (Button)findViewById(R.id.button2);
        createList = (Button)findViewById(R.id.button3);
        exitBtn = (Button)findViewById(R.id.button4);
        checkOutBtn = (Button)findViewById(R.id.button5);
        infoBtn = (Button)findViewById(R.id.button6);


        scanBtn.setOnClickListener(this);
        sortBtn.setOnClickListener(this);
        createList.setOnClickListener(this);
        exitBtn.setOnClickListener(this);
        checkOutBtn.setOnClickListener(this);

        fragment = getSupportFragmentManager().findFragmentByTag("displayWholeList");



    }

    public void onClick(View v){
        if(v.getId() == R.id.button){
            Intent intent = new Intent(MainActivity.this,Scanner.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.button2){
            if(displayWholeList.clearGrid()==true) {
                displayWholeList.loadFiles();
                displayWholeList.getWholeList(db.getWholeList());
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_main, displayWholeList)
                        .commit();
            }

        }
        else if(v.getId() == R.id.button3)
        {
             getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_main, sortFragment)
                        .commit();
        }
        else if(v.getId()== R.id.button4)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_main,removeItem)
                    .commit();
        }
        else if(v.getId()==R.id.button5)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_main,searchChoice)
                    .commit();
        }
    }
}
