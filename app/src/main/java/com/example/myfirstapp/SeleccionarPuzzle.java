package com.example.myfirstapp;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class SeleccionarPuzzle extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seleccion_main);
        Button ayuda=findViewById(R.id.help);
        Button volver=findViewById(R.id.botonvolver);
        ayuda.setOnClickListener(this);
        volver.setOnClickListener(this);

        AssetManager am;
        am = getAssets();

        try {
            final String[] files  = am.list("img"); //todas la imagenes para armar

            GridView grid = findViewById(R.id.tableropuzzles);
            grid.setAdapter(new Redimensionador(this)); //adapta las imagenes para mostrarla en tablero
            grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getApplicationContext(), CreaPuzzle.class);//envia al juego la imagen seleccionada
                    //por el jugador
                    intent.putExtra("assetName", files[i % files.length]);
                    startActivity(intent);
                }
            });
        } catch (
                IOException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT);
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.help:
                Intent intent = new Intent(this, Help.class);
                startActivity(intent);

                break;

            case R.id.botonvolver:
                finish();
                break;
        }

    }
}