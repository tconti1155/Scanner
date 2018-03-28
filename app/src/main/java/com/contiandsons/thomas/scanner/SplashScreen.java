package com.contiandsons.thomas.scanner;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by Thomas on 8/7/2017.
 *
 * // This for Displaying the Splash Screen
 */

public class SplashScreen extends Activity{
    @Override
    protected void onStart(){
        super.onStart();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        try {
            //Pausing the screen for 1 second
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finish();
    }
}
