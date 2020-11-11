package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.tensorflow.lite.examples.classification.Domen.Biblioteka;
import org.tensorflow.lite.examples.classification.Domen.Korisnik;
import org.tensorflow.lite.examples.classification.Domen.Pitanje;

import java.util.ArrayList;
import java.util.List;

public class KvizActivity extends AppCompatActivity {

    TextView txtBrPitanja;
    TextView txtPitanje;
    Button btnPrethodno;
    Button btnSledece;

    RadioGroup radioGroup;
    RadioButton rbtn1;
    RadioButton rbtn2;
    RadioButton rbtn3;

    static Korisnik korisnik;
    static int trenutniBrPitanja = -1;

    @Override
    public void onBackPressed() {
        //moveTaskToBack(true); //izlazi iz aplikacije
        trenutniBrPitanja = -1;
        Intent setIntent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(setIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kviz);

        txtBrPitanja = findViewById(R.id.txtBrPitanja);
        txtPitanje = findViewById(R.id.txtPitanje);
        btnPrethodno = findViewById(R.id.btnPrethodno);
        btnSledece = findViewById(R.id.btnSledece);

        radioGroup = findViewById(R.id.radioGroup);
        rbtn1 = findViewById(R.id.rbtn1);
        rbtn2 = findViewById(R.id.rbtn2);
        rbtn3 = findViewById(R.id.rbtn3);

        korisnik = new Korisnik();
        korisnik.setNivo(Biblioteka.nivo);

        for(Pitanje t : Biblioteka.listaPitanja){
            if(t.getNivo().equals(korisnik.getNivo())){
                korisnik.getListaPitanja().add(t);
                korisnik.getListaTacnihOdgovora().add(t.getTacanOdg());
            }
        }

        korisnik.izracunajUkBrPitanja();

        if(trenutniBrPitanja < 1){
            trenutniBrPitanja = 1;
            txtBrPitanja.setText("1/" + korisnik.getUkBrPitanja());
            Pitanje trenutno = korisnik.getListaPitanja().get(trenutniBrPitanja-1);
            txtPitanje.setText(trenutno.getNaziv());
            rbtn1.setText(trenutno.getOdgovori().get(0));
            rbtn2.setText(trenutno.getOdgovori().get(1));
            rbtn3.setText(trenutno.getOdgovori().get(2));
            btnPrethodno.setVisibility(View.INVISIBLE);
        }

        btnPrethodno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //KvizActivity.super.onBackPressed();
                trenutniBrPitanja-=1;
                prikaziPrethodnoPitanje();
            }
        });

        btnSledece.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbtn1.isChecked() || rbtn2.isChecked() || rbtn3.isChecked()){
                    if(trenutniBrPitanja < korisnik.getUkBrPitanja()){
                        String cekVrednost = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                        if(korisnik.getListaOdgovoraKorisnika().size() < trenutniBrPitanja)
                            korisnik.getListaOdgovoraKorisnika().add(cekVrednost);
                        else
                            korisnik.getListaOdgovoraKorisnika().set(trenutniBrPitanja-1,cekVrednost);
                        trenutniBrPitanja+=1;
                        prikaziSledecePitanje();
                    } else {
                        String cekVrednost = ((RadioButton)findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString();
                        korisnik.getListaOdgovoraKorisnika().add(cekVrednost);
                        izracunajBrojPoena();
                    }
                } else {
                    Toast.makeText(KvizActivity.this,"Niste odgovorili na pitanje.",Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void prikaziPrethodnoPitanje() {
        String tekstBroj = String.valueOf(trenutniBrPitanja) + "/" + korisnik.getUkBrPitanja();
        txtBrPitanja.setText(tekstBroj);
        Pitanje trenutno = korisnik.getListaPitanja().get(trenutniBrPitanja-1);
        txtPitanje.setText(trenutno.getNaziv());
        rbtn1.setText(trenutno.getOdgovori().get(0));
        rbtn2.setText(trenutno.getOdgovori().get(1));
        rbtn3.setText(trenutno.getOdgovori().get(2));
        String stiklirano = korisnik.getListaOdgovoraKorisnika().get(trenutniBrPitanja-1);
        if(rbtn1.getText().equals(stiklirano)){
            rbtn1.setChecked(true);
        } else if(rbtn2.getText().equals(stiklirano))
            rbtn2.setChecked(true);
        else if(rbtn3.getText().equals(stiklirano))
            rbtn3.setChecked(true);
        if(trenutniBrPitanja == 1){
            btnPrethodno.setVisibility(View.INVISIBLE);
        } else {
            btnPrethodno.setVisibility(View.VISIBLE);
        }
        if(trenutniBrPitanja < korisnik.getUkBrPitanja())
            btnSledece.setText("Sledeće");
    }

    private void prikaziSledecePitanje() {
        String tekstBroj = trenutniBrPitanja + "/" + korisnik.getUkBrPitanja();
        txtBrPitanja.setText(tekstBroj);
        Pitanje trenutno = korisnik.getListaPitanja().get(trenutniBrPitanja-1);
        txtPitanje.setText(trenutno.getNaziv());
        rbtn1.setText(trenutno.getOdgovori().get(0));
        rbtn2.setText(trenutno.getOdgovori().get(1));
        rbtn3.setText(trenutno.getOdgovori().get(2));
        if(korisnik.getListaOdgovoraKorisnika().size() < trenutniBrPitanja)
            radioGroup.clearCheck();
        else {
            String stiklirano = korisnik.getListaOdgovoraKorisnika().get(trenutniBrPitanja-1);
            if(rbtn1.getText().equals(stiklirano)){
                rbtn1.setChecked(true);
            } else if(rbtn2.getText().equals(stiklirano))
                rbtn2.setChecked(true);
            else if(rbtn3.getText().equals(stiklirano))
                rbtn3.setChecked(true);
        }
        btnPrethodno.setVisibility(View.VISIBLE);
        if(trenutniBrPitanja == korisnik.getUkBrPitanja())
            btnSledece.setText("Kraj");
    }

    private void izracunajBrojPoena() {
        korisnik.izracunajUkBrPoena();
        trenutniBrPitanja = 0;
        Intent setIntent = new Intent(getApplicationContext(),CekanjeActivity.class);
        startActivity(setIntent);
    }

}