package org.tensorflow.lite.examples.classification.Domen;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import org.tensorflow.lite.examples.classification.MainActivity;

import java.util.List;

public class Biblioteka {

    public static List<Pitanje> listaPitanja = null;
    public static String nivo = null;

    public static void ucitajPitanja() {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Pitanje");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null){
                    if (objects.size() > 0) {
                        //Pitanje pitanje = new Pitanje();
                        for (int j = 0; j < objects.size(); j++)
                        {
                            ParseObject p = objects.get(j);
                            Pitanje pitanje = new Pitanje();
                            pitanje.setRbr(p.getInt("rbr"));
                            pitanje.setNaziv(p.getString("naziv"));
                            pitanje.setTacanOdg(p.getString("tacanOdg"));
                            pitanje.setNivo(p.getString("nivo"));
                            pitanje.setOdgovori(p.getList("odgovori"));
                            listaPitanja.add(pitanje);
                        }
                        //Toast.makeText(Biblioteka.this,"uspesno ucitana pitanja",Toast.LENGTH_LONG).show();
                    } else {
                        //Toast.makeText(MainActivity.this,"nema objekata",Toast.LENGTH_LONG).show();
                    }
                    //mDialog.dismiss();
                } else {
                    //Toast.makeText(MainActivity.this,"Nesto nije okej",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
