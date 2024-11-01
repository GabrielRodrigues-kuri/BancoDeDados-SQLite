package com.br.bancodedados;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    Button btcriarbanco;
    Button btcadrastrados;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btcriarbanco = findViewById(R.id.btcriarbanco);
        btcadrastrados = findViewById(R.id.btcadastrardados);

        btcadrastrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity_grava_registros = new Intent (MainActivity.this,
                        activity_grava_registros.class) ;
                MainActivity.this.startActivities(new Intent[]{activity_grava_registros});
            }
        });

        btcriarbanco.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View View){
                try {
                    db = openOrCreateDatabase( "banco_dados",
                            Context.MODE_PRIVATE, null);
                    db.execSQL("create table if not exists " +
                            " usuarios(numereg integer primary key " +
                            " autoincrement, nome text not null, telefone text" +
                            " not null, " + " email text not null) ");

                    AlertDialog.Builder dialogo = new
                            AlertDialog.Builder(MainActivity.this);
                    dialogo.setTitle("aviso")
                            .setMessage("banco de dados criado com sucesso!")
                            .setNeutralButton("ok",null)
                            .show();
                } catch (Exception e) {

                }
            }
        });
    }
}