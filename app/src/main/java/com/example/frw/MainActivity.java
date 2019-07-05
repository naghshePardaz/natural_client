package com.example.frw;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.frw.request.SharedPref;

public class MainActivity extends AppCompatActivity {

    private Handler mSplashScreeHandler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isJwtExists = SharedPref.isValueExists(getApplicationContext(), "token");
        final Intent intent = isJwtExists ? new Intent(this, ProfileActivity.class) : new Intent(this, LoginActivity.class);
        mSplashScreeHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    startActivity(intent);
                    finish();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }, 5000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSplashScreeHandler.removeCallbacksAndMessages(null);
    }
}