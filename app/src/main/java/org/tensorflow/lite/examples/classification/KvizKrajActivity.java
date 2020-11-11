package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;
import org.tensorflow.lite.examples.classification.Domen.Biblioteka;
import org.tensorflow.lite.examples.classification.Domen.Korisnik;

import java.io.IOException;
import java.io.InputStream;

public class KvizKrajActivity extends AppCompatActivity {

    TextView txtPoeni;
    TextView txtBrTacnih;
    Button btnPonovo;
    Button btnPocetak;
    //GifImageView giv;

    Korisnik k;

    @Override
    public void onBackPressed() {
        //moveTaskToBack(true); //izlazi iz aplikacije
        Intent setIntent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(setIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kviz_kraj);

        txtPoeni = findViewById(R.id.txtBrPoena);
        txtBrTacnih = findViewById(R.id.txtBrTacnih);
        btnPonovo = findViewById(R.id.btnPonovo);
        btnPocetak = findViewById(R.id.btnPocetak);
        //giv = findViewById(R.id.gif);

        k = KvizActivity.korisnik;

        txtPoeni.setText(k.getOsvojenBrPoena() + "/" + k.getUkBrPoena());
        txtBrTacnih.setText(k.getBrTacnih() + "/" + k.getUkBrPitanja());

        YoYo.with(Techniques.Bounce).duration(1000).repeat(1).playOn(txtPoeni);
        //YoYo.with(Techniques.Bounce).duration(700).repeat(1).playOn(txtBrTacnih);

        btnPonovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setIntent = new Intent(getApplicationContext(),PopUpActivity.class);
                startActivity(setIntent);
            }
        });

        btnPocetak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent setIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(setIntent);
            }
        });

    }
}