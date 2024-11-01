package com.br.bancodedados;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.*;

public class activity_grava_registros extends AppCompatActivity {

    Button btcadrastrar;
    EditText ednome, edtelefone, edemail;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grava_registros);
        btcadrastrar = (Button) findViewById(R.id.btcadastrar);
        ednome = (EditText) findViewById(R.id.ednome);
        edemail = (EditText) findViewById(R.id.edemail);
        edtelefone = (EditText) findViewById(R.id.edtelefone);

        try {
            db = openOrCreateDatabase("banco_dados",
                    Context.MODE_PRIVATE, null);
        }catch (Exception e){
            MostraMensagem("erro :" + e.toString());
        }
        btcadrastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View arg0) {
                String nome = ednome.getText() .toString();
                String telefone = edtelefone.getText() .toString();
                String email =  edemail.getText() .toString();
                try {
                    db.execSQL("insert into usuarios(nome,telefone ,email) values ('"+ nome + "','"+ telefone + "','"+ email + "') ");
                    MostraMensagem("Dados cadastrados com sucesso");
                }catch (Exception e){
                    MostraMensagem("erro :" + e.toString());
                }
            }
        });
    }
    public void MostraMensagem(String str){
        AlertDialog.Builder dialogo = new AlertDialog.Builder(activity_grava_registros.this);
        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("ok", null);
        dialogo.show();
    }
}