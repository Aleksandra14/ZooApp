package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import org.tensorflow.lite.examples.classification.Domen.Zivotinja;

/*import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;*/

import java.util.Arrays;

import static org.tensorflow.lite.examples.classification.MainActivity.izbor;

public class SaznajViseActivity extends AppCompatActivity {

    TextView txtNaziv;
    ImageButton imgSlika;
    TextView txtPorodica;
    TextView txtZivotniVek;
    TextView txtStaniste;
    TextView txtRasprostranjenost;
    TextView txtTekst;
    TextView txtTekst2;
    TextView txtZanimljivost;
    TextView txtZanimljivost2;
    //Button btnNazad;
    int i;
    Bitmap bitmap;

    Zivotinja zivotinja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saznaj_vise);

        Bundle b = getIntent().getExtras();
        String vrednost = b.getString("prvi");
        String prvo = vrednost.substring(0,1).toUpperCase();
        String ostalo = vrednost.substring(1).toLowerCase();
        vrednost = prvo + ostalo;

        txtNaziv = findViewById(R.id.txtNaziv);
        imgSlika = findViewById(R.id.imgSlika);
        txtPorodica = findViewById(R.id.txtPorodica);
        txtZivotniVek = findViewById(R.id.txtZivotniVek);
        txtStaniste = findViewById(R.id.txtStaniste);
        txtRasprostranjenost = findViewById(R.id.txtRasprostranjenost);
        txtTekst2 = findViewById(R.id.txtTekst2);
        txtZanimljivost2 = findViewById(R.id.txtZanimljivost2);

        zivotinja = new Zivotinja();

        //btnNazad = findViewById(R.id.btnNazad);

        txtNaziv.setText(vrednost);

        final ProgressDialog mDialog = new ProgressDialog(SaznajViseActivity.this);
        mDialog.setMessage("Molimo Vas sačekajte...");
        mDialog.show();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Zivotinja");
        query.whereEqualTo("naziv", vrednost);
        query.getFirstInBackground(new GetCallback<ParseObject>() {
            public void done(ParseObject z, ParseException e) {
                if (e == null) {
                    zivotinja.setNaziv(z.getString("naziv"));
                    zivotinja.setSlika(z.getString("slika"));
                    zivotinja.setPorodica(z.getString("porodica"));
                    zivotinja.setZivotniVek(z.getString("zivotniVek"));
                    zivotinja.setStaniste(z.getString("staniste"));
                    zivotinja.setRasprostranjenost(z.getString("rasprostranjenost"));
                    zivotinja.setTekst(z.getString("tekst"));
                    zivotinja.setZanimljivost(z.getString("zanimljivost"));

                    ParseFile image = (ParseFile) z.getParseFile("slika");
                    if (image != null) {
                        image.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] data, ParseException e) {
                                if (e == null) {
                                    bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                                    if (bitmap != null) {
                                        Log.e("parse file ok", " null");
                                        //imgSlika.setImageBitmap(Bitmap.createScaledBitmap(bmp, (display.getWidth() / 5), (display.getWidth() /50), false));
                                        imgSlika.setImageBitmap(bitmap);
                                        mDialog.dismiss();
                                        //Toast.makeText(SaznajViseActivity.this,zivotinja.getNaziv(),Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Log.e("paser after downloade", " null");
                                }
                            }
                        });
                    } else {
                        Log.e("parse file", " null");
                        //imgSlika.setPadding(10, 10, 10, 10);
                    }

                    txtPorodica.append(zivotinja.getPorodica());
                    txtZivotniVek.append(zivotinja.getZivotniVek());
                    txtStaniste.append(zivotinja.getStaniste());
                    txtRasprostranjenost.append(zivotinja.getRasprostranjenost());
                    txtTekst2.setText(zivotinja.getTekst());
                    txtZanimljivost2.setText(zivotinja.getZanimljivost());

                    /*SpannableString content = new SpannableString("Content");
                    content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
                    txtPorodica.append(content);*/

                } else {
                    // Something is wrong
                    mDialog.dismiss();
                    Toast.makeText(SaznajViseActivity.this,"Nesto nije okej",Toast.LENGTH_LONG).show();
                }
            }
        });


        imgSlika.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Toast.makeText(getContext(),"Dugi klik",Toast.LENGTH_LONG).show();
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(SaznajViseActivity.this);
                alertDialog.setTitle("Želite da sačuvate sliku?");

                alertDialog.setPositiveButton("Da", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if( (MediaStore.Images.Media.insertImage(getContentResolver(),bitmap,"naslov","opis")) != null )
                            Snackbar.make(findViewById(android.R.id.content), "Slika je sačuvana :)", Snackbar.LENGTH_LONG).show();
                        else
                            Toast.makeText(getApplicationContext(),"Slika nije sačuvana :(", Toast.LENGTH_SHORT).show();
                    }
                });

                alertDialog.setNegativeButton("Ne",null);

                AlertDialog alert = alertDialog.create();
                alert.show();

                return true;
            }
        });

        /*btnNazad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ClassifierActivity.class);
                startActivity(i);
            }
        });*/

    }
}