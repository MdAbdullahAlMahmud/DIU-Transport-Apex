package com.socalledengineers.diutransportapex;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.firebase.auth.FirebaseAuth;
import com.mkrlabs.customstatusbar.CustomStatusBar;
import com.socalledengineers.diutransportapex.ui.login.LoginActivity;

public class SplashActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CustomStatusBar.transparentStatusBarWithIcon(this,false);
        setContentView(R.layout.activity_splash);

        mAuth = FirebaseAuth.getInstance();
        new Handler(Looper.myLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mAuth.getCurrentUser()!=null){
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();

                }else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                    finish();
                }
            }
        },2500);
    }
}