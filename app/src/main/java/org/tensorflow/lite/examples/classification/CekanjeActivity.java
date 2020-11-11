package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;
import org.tensorflow.lite.examples.classification.Domen.Biblioteka;

import java.io.IOException;
import java.io.InputStream;

public class CekanjeActivity extends AppCompatActivity {

    GifImageView giv2;
    //int ukBrPoena;
    boolean prekinuto;
    String[] nizGifova1 = {"rakun.gif","tigar.gif"};
    String[] nizGifova2 = {"noj.gif","zirafa2.gif"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cekanje);

        giv2 = findViewById(R.id.gif2);
        prekinuto = false;

        double pola = (double) KvizActivity.korisnik.getUkBrPoena() / 2;
        String gif = "";

        if(KvizActivity.korisnik.getOsvojenBrPoena() < pola) {
            int indeks = (int)(Math.random()*nizGifova1.length);
            gif = nizGifova1[indeks];
        }
        else {
            int indeks = (int)(Math.random()*nizGifova2.length);
            gif = nizGifova2[indeks];
        }

        try {
            InputStream inputStream = getAssets().open(gif);
            byte[] bytes = IOUtils.toByteArray(inputStream);
            giv2.setBytes(bytes);
            giv2.startAnimation();

        } catch (IOException e) {
            e.printStackTrace();
        }

        giv2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                prekinuto = true;
                startActivity(new Intent(getApplicationContext(),KvizKrajActivity.class));
                return false;
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!prekinuto){
                    startActivity(new Intent(getApplicationContext(),KvizKrajActivity.class));
                    finish();
                }
            }
        },3000);

    }
}