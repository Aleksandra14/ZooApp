package org.tensorflow.lite.examples.classification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.tensorflow.lite.examples.classification.Domen.Biblioteka;

import java.util.EventListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

public class PopUpActivity extends AppCompatActivity {

    //Spinner spiner;
    Button btnZapocni;
    String[] nivoi = { "lak", "srednji", "težak" };

    ListView listView;
    boolean stiklirano = false;
    int prethodniId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);

        //spiner = findViewById(R.id.spinner);
        btnZapocni = findViewById(R.id.btnZapocniKviz);

        listView = findViewById(R.id.listViewNivoi);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.5));
        getWindow().setBackgroundDrawableResource(R.color.belaProzirna);

        //final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, nivoi);
        //listView.setAdapter(adapter);

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nivoi);
        adapter.setDropDownViewResource(R.layout.my_spinner);
        spiner.setAdapter(adapter);
        spiner.setOnItemSelectedListener(this);*/

        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);

        btnZapocni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Biblioteka.nivo == null)
                    Toast.makeText(PopUpActivity.this,"Niste odabrali nivo.",Toast.LENGTH_LONG).show();
                else {
                    Intent i = new Intent(getApplicationContext(),OdbrojavanjeActivity.class);
                    startActivity(i);
                }

            }
        });

    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return nivoi.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view2 = getLayoutInflater().inflate(R.layout.list_view_nivoi,null);

            //TextView nivo = (TextView) view2.findViewById(R.id.txtNivo);
            Button nivo = (Button) view2.findViewById(R.id.txtNivo);

            //ImageView slika = (ImageView) view2.findViewById(R.id.imgLastMinute);

            nivo.setText(nivoi[position]);
            /*int imageRes = getResources().getIdentifier(nizSlika[position],null,PopUpActivity.this.getPackageName()); //radi
            slika.setImageResource(imageRes);*/
            //slika.setBackgroundResource(imageRes);

            nivo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!stiklirano){
                        Biblioteka.nivo = nivoi[position];
                        //Toast.makeText(PopUpActivity.this,nivoi[position],Toast.LENGTH_LONG).show();
                        //TextView tv = listView.getChildAt(i).findViewById(R.id.txtNivo);
                        Button tv = listView.getChildAt(position).findViewById(R.id.txtNivo);
                        //tv.setBackgroundResource(R.color.tirkizProzirna);
                        tv.setBackgroundResource(R.drawable.dizajn_nivo_stiklirano);
                        stiklirano = true;
                        prethodniId = position;
                    } else {
                        //int pos = listView.getSelectedItemPosition();
                        //int pos = listView.getOnItemClickListener();
                        listView.getChildAt(prethodniId).findViewById(R.id.txtNivo).setBackgroundResource(R.drawable.dizajn_nivo);
                        Biblioteka.nivo = nivoi[position];
                        //Toast.makeText(PopUpActivity.this,nivoi[position],Toast.LENGTH_LONG).show();
                        listView.getChildAt(position).findViewById(R.id.txtNivo).setBackgroundResource(R.drawable.dizajn_nivo_stiklirano);
                        prethodniId = position;
                    }
                }
            });

            return view2;
        }
    }
}