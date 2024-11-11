package com.br.bancodedados;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.os.Bundle;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

public class ExcluirDadosActivity extends AppCompatActivity {


    TextView txtnome, txttelefone, txtemail,txtstatus_registro;
    SQLiteDatabase db;
    ImageView imgprimeiro, imganterior, imgproximo, imgultimo;
    int indice;
    int numereg;
    Cursor c;
    Button btexcluirdados;

    DialogInterface.OnClickListener diExcluirRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excluir_dados);

        txtnome = (TextView) findViewById(R.id.txtnome);
        txttelefone = (TextView) findViewById(R.id.txttelefone);
        txtemail = (TextView) findViewById(R.id.txtemail);
        txtstatus_registro = (TextView) findViewById(R.id.txtstatus_registro);

        txtnome.setText("");
        txttelefone.setText("");
        txtemail.setText("");

        imgprimeiro = (ImageView) findViewById(R.id.imgprimeiro);
        imganterior = (ImageView) findViewById(R.id.imganterior);
        imgproximo = (ImageView) findViewById(R.id.imgproximo);
        imgultimo = (ImageView) findViewById(R.id.imgultimo);

        btexcluirdados = (Button) findViewById(R.id.btexcluirdados);

        db = openOrCreateDatabase("banco_dados",Context.MODE_PRIVATE, null);

        CarregarDados();

        if(c.getCount() > 0){
            c.moveToFirst();
            indice = 1;
            numereg = c.getInt(0);
            txtnome.setText(c.getString(1));
            txttelefone.setText(c.getString(2));
            txtemail.setText(c.getString(3));
            txtstatus_registro.setText(indice + " / " + c.getCount());
        }else {
            txtstatus_registro.setText("nennhum registro");
        }

        imgprimeiro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (c.getCount() > 0){
                    if (c.getCount() > 1 ){
                        c.moveToFirst();
                        indice = 1;
                        numereg = c.getInt(0);
                        txtnome.setText(c.getString(1));
                        txttelefone.setText(c.getString(2));
                        txtemail.setText(c.getString(3));

                        txtstatus_registro.setText(indice + " / " + c.getCount());
                    }
                }
            }});
        imganterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (c.getCount() > 0){
                    indice--;
                    c.moveToPrevious();
                    numereg = c.getInt(0);
                    txtnome.setText(c.getString(1));
                    txttelefone.setText(c.getString(2));
                    txtemail.setText(c.getString(3));

                    txtstatus_registro.setText(indice + " / " + c.getCount());
                }
            }
        });
        imgproximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0){
                if (c.getCount() > 0){
                    indice++;
                    c.moveToNext();
                    numereg = c.getInt(0);
                    txtnome.setText(c.getString(1));
                    txttelefone.setText(c.getString(2));
                    txtemail.setText(c.getString(3));

                    txtstatus_registro.setText(indice + " / " + c.getCount());
                }
            }
        });
        imgultimo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if (c.getCount() > 0){
                    c.moveToLast();
                    numereg = c.getInt(0);
                    txtnome.setText(c.getString(1));
                    txttelefone.setText(c.getString(2));
                    txtemail.setText(c.getString(3));

                    txtstatus_registro.setText(indice + " / " + c.getCount());
                }
            }
        });
        diExcluirRegistro = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                db.execSQL("delete from usuarios where = " + numereg);
                CarregarDados();
                mostrarmensagem("dados Excluidos com sucesso");
            }
        };
        btexcluirdados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (c.getCount() > 0){
                    AlertDialog.Builder dialogo = new AlertDialog.Builder(ExcluirDadosActivity.this);
                    dialogo.setTitle("confirmar");
                    dialogo.setMessage("deseja excluir esse Registro ?");
                    dialogo.setNeutralButton("não", null);
                    dialogo.setPositiveButton("sim", diExcluirRegistro);
                    dialogo.show();
                }else {
                    mostrarmensagem("não existe registro para excluir");
                }
            }
        });
    }
//    catch(Exception e){
//        mostrarmensagem("erro :" + e.toString());
//    }
    public void CarregarDados(){
        c = db.query("usuarios", new String[]
                {"numereg","nome","telefone","email"},
                null,null,null,null,null);

        if (c.getCount() > 0){
            c.moveToFirst();
            indice = 1;
            numereg = c.getInt(0);
            txtnome.setText(c.getString(1));
            txttelefone.setText(c.getString(2));
            txtemail.setText(c.getString(3));

            txtstatus_registro.setText(indice + " / " + c.getCount());
        }else{
            txtstatus_registro.setText("nenhum Registro");
        }
    }
    public void mostrarmensagem(String str) {
        androidx.appcompat.app.AlertDialog.Builder dialogo = new
                androidx.appcompat.app.AlertDialog.Builder(ExcluirDadosActivity.this);

        dialogo.setTitle("Aviso");
        dialogo.setMessage(str);
        dialogo.setNeutralButton("ok", null);
        dialogo.show();
    }
}