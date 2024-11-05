package com.br.bancodedados;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ConsultaDadosActivity extends Activity {
    TextView txtnome, txttelefone, txtemail, txtstatus_registro;

    SQLiteDatabase db;

    ImageView imgPrimeiro, imgAnterior, imgProximo, imgUltimo;

    int indice;

    Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_dados_ativity);

        txtnome = (TextView) findViewById(R.id.txtnome);
        txttelefone = (TextView) findViewById(R.id.txttelefone);
        txtemail = (TextView) findViewById(R.id.txtemail);
        txtstatus_registro = (TextView) findViewById(R.id.txtstatus_registro);

        txtnome.setText("");
        txttelefone.setText("");
        txtemail.setText("");

        imgPrimeiro = (ImageView) findViewById(R.id.imgprimeiro);
        imgAnterior = (ImageView) findViewById(R.id.imganterior);
        imgProximo = (ImageView) findViewById(R.id.imgproximo);
        imgUltimo = (ImageView) findViewById(R.id.imgultimo);

        try {
            db = openOrCreateDatabase("bando_dados",Context.MODE_PRIVATE, null);

            c = db.query("usuario", new String[]{"nome","telefone","email"},null,null,null,null,null);

            if(c.getCount() > 0){
                c.moveToFirst();
                indice = 1;

                txtnome.setText(c.getString(0));
                txttelefone.setText(c.getString(1));
                txtemail.setText(c.getString(3));

                txtstatus_registro.setText(indice + " / " + c.getCount());
            }else{
                txtstatus_registro.setText("nenhum registro");
            }

            imgPrimeiro.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view){
                    if (c.getCount() > 0){
                        c.moveToFirst();
                        indice = 1;
                        txtnome.setText(c.getString(0));
                        txttelefone.setText(c.getString(1));
                        txtemail.setText(c.getString(3));

                        txtstatus_registro.setText(indice + " / " + c.getCount());
                    }
                }
            });
            imgAnterior.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if (c.getCount() > 0){
                        indice--;
                        c.moveToPrevious();
                        txtnome.setText(c.getString(0));
                        txttelefone.setText(c.getString(1));
                        txtemail.setText(c.getString(3));

                        txtstatus_registro.setText(indice + " / " + c.getCount());
                    }
                }
            });
            imgProximo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0){
                    if (c.getCount() > 0){
                        indice++;
                        c.moveToNext();
                        txtnome.setText(c.getString(0));
                        txttelefone.setText(c.getString(1));
                        txtemail.setText(c.getString(3));

                        txtstatus_registro.setText(indice + " / " + c.getCount());
                    }
                }
            });
            imgUltimo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v){
                    if (c.getCount() > 0){
                        c.moveToLast();
                        txtnome.setText(c.getString(0));
                        txttelefone.setText(c.getString(1));
                        txtemail.setText(c.getString(3));

                        txtstatus_registro.setText(indice + " / " + c.getCount());
                    }
                }
            });
        }
        catch (Exception e){
            MostrarMensagem("erro : " + e.toString());
        }
    }
    public void MostrarMensagem(String str){
        AlertDialog.Builder dialogo = new
        AlertDialog.Builder(ConsultaDadosActivity.this);

        dialogo.setTitle("aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("ok",null);
        dialogo.show();
    }
}