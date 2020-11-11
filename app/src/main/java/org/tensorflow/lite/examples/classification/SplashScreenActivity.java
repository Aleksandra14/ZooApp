package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;


public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*EasySplashScreen easySplashScreen = new EasySplashScreen(SplashScreenActivity.this)
                .withFullScreen().withTargetActivity(MainActivity.class).withSplashTimeOut(5000)
                .withBackgroundColor(Color.parseColor("#1a1b29"))
                .withHeaderText("ZooApp")
                .withFooterText("Master")
                .withBeforeLogoText("before logo")
                .withAfterLogoText("after logo")
                .withLogo(R.mipmap.ic_launcher_round);

        easySplashScreen.getHeaderTextView().setTextColor(Color.WHITE);
        easySplashScreen.getFooterTextView().setTextColor(Color.WHITE);

        View essView = easySplashScreen.create();
        setContentView(essView);*/

    }
}