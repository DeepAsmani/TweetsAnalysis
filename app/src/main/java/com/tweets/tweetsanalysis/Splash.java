package com.tweets.tweetsanalysis;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ExpandedMenuView;
import android.view.Window;
import android.view.WindowManager;

public class Splash extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
                setContentView(R.layout.splash_layout);

        Thread splash = new Thread() {
            @Override
            public void run() {
                try {
                    super.run();
                    sleep(2000);

                } catch (Exception e) {
                } finally {
                    Intent i = new Intent(Splash.this, MainActivity.class);
                    startActivity(i);
                    finish();

                }
            }
        };
        splash.start();
    }
}
