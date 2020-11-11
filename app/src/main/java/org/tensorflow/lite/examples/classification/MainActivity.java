package org.tensorflow.lite.examples.classification;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import org.tensorflow.lite.examples.classification.Domen.Biblioteka;
import org.tensorflow.lite.examples.classification.Domen.Pitanje;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static int izbor = 0;

    ImageButton btnMuzika;
    MediaPlayer mediaPlayer;

    @Override
    public void onBackPressed() {
        moveTaskToBack(true); //izlazi iz aplikacije
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnMuzika = findViewById(R.id.btnMuzika);
        mediaPlayer = MediaPlayer.create(this,R.raw.baby_shark);

        if(Biblioteka.listaPitanja == null){
            Biblioteka.listaPitanja = new ArrayList<Pitanje>();
            final ProgressDialog mDialog = new ProgressDialog(MainActivity.this);
            mDialog.setMessage("Molimo Vas sačekajte...");
            mDialog.show();
            Biblioteka.ucitajPitanja();
            mDialog.dismiss();
        }

        btnMuzika.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer == null){
                    mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.baby_shark);
                    mediaPlayer.start();
                }
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                    btnMuzika.setBackgroundResource(R.drawable.ic_baseline_pause_24);
                }else {
                    mediaPlayer.pause();
                    btnMuzika.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);
                }
            }
        });

        //oslobadjamo resurs kada se zavrsi pesma
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(MainActivity.this,"Pesma se završila.",Toast.LENGTH_LONG).show();
                releaseMediaPlayer();
            }
        });
    }

    public void skeniraj(View view) {
        Intent i = new Intent(getApplicationContext(),ClassifierActivity.class);
        izbor = 1;
        startActivity(i);
    }

    public void pogodi(View view) {
        Intent i = new Intent(getApplicationContext(),ClassifierActivity.class);
        izbor = 2;
        startActivity(i);
    }

    public void igrajKviz(View view) {
        startActivity(new Intent(getApplicationContext(),PopUpActivity.class));
    }

    private void releaseMediaPlayer(){
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
            btnMuzika.setBackgroundResource(R.drawable.ic_baseline_play_arrow_24);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }
}
