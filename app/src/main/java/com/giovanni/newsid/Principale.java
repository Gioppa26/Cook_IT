package com.giovanni.newsid;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;

public class Principale extends AppCompatActivity {
    TextView ricettaView;
    ImageView cibo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principale);
        ricettaView = (TextView) findViewById(R.id.tvRicetta);
        getIntent().getStringExtra("Immagine");
        getIntent().getStringExtra("Id");

        ricettaView.setText(getIntent().getStringExtra("Ricetta"));


    }
}