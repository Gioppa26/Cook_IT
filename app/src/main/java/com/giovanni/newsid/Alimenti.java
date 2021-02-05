package com.giovanni.newsid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.UiAutomation;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import com.android.volley.*;
import com.android.volley.toolbox.*;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

public class Alimenti extends AppCompatActivity {
    SearchView ricercaAlimenti;
    ListView lista;
    CheckBox uovo,parmigiano,mozzarella,burro,yogurt,speck,pecorino,melanzane,carote,zucchine;
    Button inviaAlimenti;
    TextView prova;
    private ArrayList<String> mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alimenti);

        ricercaAlimenti=(SearchView) findViewById(R.id.ricerca);
        lista=(ListView)findViewById(R.id.ricercalist);
        uovo=(CheckBox) findViewById(R.id.cbUova);
        mozzarella=(CheckBox) findViewById(R.id.cbMozzarella);
        parmigiano=(CheckBox) findViewById(R.id.cbParmigiano);
        burro=(CheckBox) findViewById(R.id.cbBurro);
        speck=(CheckBox) findViewById(R.id.cbSpeck);
        yogurt=(CheckBox) findViewById(R.id.cbYogurt);
        pecorino=(CheckBox) findViewById(R.id.cbPecorino);
        melanzane=(CheckBox) findViewById(R.id.cbMelanzane);
        carote=(CheckBox) findViewById(R.id.cbMelanzane);
        melanzane=(CheckBox) findViewById(R.id.cbMelanzane);
        melanzane=(CheckBox) findViewById(R.id.cbMelanzane);
        inviaAlimenti=(Button) findViewById(R.id.conferma);
        prova=(TextView) findViewById(R.id.textView6);
        mResult = new ArrayList<>();

        uovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uovo.isChecked())
                {
                    mResult.add("Uovo");
                }
                else
                    mResult.remove("Uovo");
            }

    });
        mozzarella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mozzarella.isChecked())
                {
                    mResult.add("mozzarella");
                }
                else
                    mResult.remove("mozzarella");
            }

        });
        parmigiano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(parmigiano.isChecked())
                {
                    mResult.add("Chicken");
                }
                else
                    mResult.remove("Chicken");
            }

        });
        burro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(burro.isChecked())
                {
                    mResult.add("Burro");
                }
                else
                    mResult.remove("Burro");
            }

        });
        speck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(speck.isChecked())
                {
                    mResult.add("Speck");
                }
                else
                    mResult.remove("Speck");
            }

        });
        yogurt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(yogurt.isChecked())
                {
                    mResult.add("Yogurt");
                }
                else
                    mResult.remove("Yogurt");
            }

        });
        pecorino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pecorino.isChecked())
                {
                    mResult.add("Pecorino");
                }
                else
                    mResult.remove("Pecorino");
            }

        });
        melanzane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(melanzane.isChecked())
                {
                    mResult.add("Melanzane");
                }
                else
                    mResult.remove("Melanzane");
            }

        });
        melanzane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(melanzane.isChecked())
                {
                    mResult.add("Melanzane");
                }
                else
                    mResult.remove("Melanzane");
            }

        });
        inviaAlimenti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    String postUrl = "https://www.themealdb.com/api/json/v1/1/filter.php?i=";
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    JSONObject postData = new JSONObject();
                    JSONArray cibi = new JSONArray();
                        for(int i=0;i<mResult.size();i++)
                        {
                            postUrl=postUrl+(mResult.get(i));
                        }
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, postUrl, null, new Response.Listener<JSONObject>()
                    {
                        @Override
                        public void onResponse(JSONObject response) {
                            //fai risultato
                            try {
                                JSONArray ricette =  response.getJSONArray("meals");
                                Random n = new Random();
                                int aux=n.nextInt(ricette.length());
                                Intent a = new Intent(Alimenti.this, Principale.class);
                                a.putExtra("Ricetta",ricette.getJSONObject(aux).getString("strMeal"));
                                a.putExtra("Immagine",ricette.getJSONObject(aux).getString("strMealThumb"));
                                a.putExtra("Id",ricette.getJSONObject(aux).getString("idMeal"));
                                startActivity(a);
                            } catch (Exception e) {}

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //manta toast errore
                        }
                    });
                    requestQueue.add(jsonObjectRequest);

                StringBuilder stringBuilder = new StringBuilder();
                for(String s:mResult)
                {
                    stringBuilder.append(s).append("\n");
                }
                prova.setText(stringBuilder.toString());
                prova.setEnabled(false);
            }
        });

    }
}