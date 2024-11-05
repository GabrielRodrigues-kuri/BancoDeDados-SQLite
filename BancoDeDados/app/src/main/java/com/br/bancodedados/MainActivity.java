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
    Button btcadrastrados2;
    Button btconsultardados;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btcriarbanco = (Button) findViewById(R.id.btcriarbanco);
        btcadrastrados = (Button) findViewById(R.id.btcadastrardados);
        btcadrastrados2 = (Button) findViewById(R.id.btcadastrar2);
        btconsultardados = (Button) findViewById(R.id.btconsultardados);

        btcadrastrados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activity_grava_registros = new Intent (MainActivity.this,
                        activity_grava_registros.class) ;
                MainActivity.this.startActivities(new Intent[]{activity_grava_registros});
            }
        });

        btcadrastrados2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GravaRegistrosActivity = new Intent (MainActivity.this,
                        GravaRegistrosActivity.class) ;
                MainActivity.this.startActivities(new Intent[]{GravaRegistrosActivity});
            }
        });

        btconsultardados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ConsultaDadosActivity = new Intent (MainActivity.this,
                        ConsultaDadosActivity.class) ;
                MainActivity.this.startActivities(new Intent[]{ConsultaDadosActivity});
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