package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.tomer.fadingtextview.FadingTextView;

import java.util.concurrent.TimeUnit;

public class OdbrojavanjeActivity extends AppCompatActivity {

    private FadingTextView fadingTextView;

    boolean prekinuto;

    @Override
    public void onBackPressed() {
        //moveTaskToBack(true); //izlazi iz aplikacije
        prekinuto = true;
        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odbrojavanje);

        fadingTextView = findViewById(R.id.fading_text_view);
        prekinuto = false;

        fadingTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                prekinuto = true;
                startActivity(new Intent(getApplicationContext(),KvizActivity.class));
                return false;
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!prekinuto){
                    fadingTextView.stop();
                    startActivity(new Intent(getApplicationContext(),KvizActivity.class));
                }
            }
        }, 5800);

    }

}