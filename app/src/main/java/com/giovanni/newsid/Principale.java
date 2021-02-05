package com.giovanni.newsid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;

public class Principale extends AppCompatActivity {
    TextView ricettaView;
    Button sitoCibo;
    ImageView cibo;
    String aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principale);
        ricettaView = (TextView) findViewById(R.id.tvRicetta);
        getIntent().getStringExtra("Immagine");
        sitoCibo=(Button) findViewById(R.id.btnLink);
        ricettaView.setText(getIntent().getStringExtra("Ricetta"));
        sitoCibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoUrl("https://www.themealdb.com/meal.php?c="+getIntent().getStringExtra("Id"));
            }
        });


    }

    private void gotoUrl(String s) {
        Uri a = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,a));
    }
}