package com.xx3.lepoox.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.xx3.lepoox.R;

public class SplashScreenActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN_DURATION = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent showMainMenu = new Intent(SplashScreenActivity.this, MainMenuActivity.class);
                startActivity(showMainMenu);
                finish();

            }

        }, SPLASH_SCREEN_DURATION);

    }

}
