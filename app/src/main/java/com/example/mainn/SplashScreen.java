package com.example.mainn;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity
{
    ImageView ic;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ic = findViewById(R.id.ic);

        Animation an = AnimationUtils.loadAnimation(this, R.anim.splash);
        ic.startAnimation(an);

        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                finish();
            }
        }, 500);
    }
}
