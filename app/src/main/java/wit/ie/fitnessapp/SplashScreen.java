package wit.ie.fitnessapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import Menus.HomeMenu;

public class SplashScreen extends Activity {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);
          int secondsDelayed = 1;

            new Handler().postDelayed(new Runnable() {
                public void run() {
                    startActivity(new Intent(SplashScreen.this, HomeMenu.class));
                    finish();
                }
            }, secondsDelayed * 1000);
        }
    }