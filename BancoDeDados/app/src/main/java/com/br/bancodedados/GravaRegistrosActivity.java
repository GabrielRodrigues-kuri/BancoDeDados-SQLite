package com.br.bancodedados;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.*;

public class GravaRegistrosActivity extends AppCompatActivity {

    Button btcadastrar;

    EditText ednome;
    EditText edtelefone;
    EditText edemail;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grava_registros2);

        btcadastrar = (Button) findViewById(R.id.btcadastrar);

        ednome = (EditText) findViewById(R.id.ednome);
        edtelefone = (EditText) findViewById(R.id.edtelefone);
        edemail = (EditText) findViewById(R.id.edemail);

        try {
            db = openOrCreateDatabase("banco_de_dados",Context.MODE_PRIVATE, null);
        }
        catch (Exception e)
        {
            MostrarMensagem("erro" + e.toString());
        }
        btcadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = ednome.getText().toString();
                String telefone = edtelefone.getText().toString();
                String email = edemail.getText().toString();

                ContentValues valor = new ContentValues();

                valor.put("nome", nome);
                valor.put("telefone", telefone);
                valor.put("email", email);
                try {
                    db.insert("usuario",null,valor);
                    MostrarMensagem("dados cadastrados com sucesso");
                }
                catch (Exception e)
                {
                    MostrarMensagem("Erro : " + e.toString());
                }
            }
        });
    }
    public void MostrarMensagem(String str){
        AlertDialog.Builder dialogo = new
        AlertDialog.Builder(GravaRegistrosActivity.this);

        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("ok", null);
        dialogo.show();
    }
}