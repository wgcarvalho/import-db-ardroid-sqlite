package com.example.newimpotdb;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.newimpotdb.dao.BancoDeDados;
import com.example.newimpotdb.modelo.Pessoa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BancoDeDados mBancoDeDados;
    private ListView lvPessoa;
    private List<Pessoa> listPessoa = new ArrayList<Pessoa>();
    private ArrayAdapter<Pessoa> arrayAdapterPessoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();
        inicializarBancoDeDados();
        popularLista();
    }

    private void inicializarComponentes() {
        lvPessoa = (ListView) findViewById(R.id.lvPessoa);
    }

    private void popularLista() {
        mBancoDeDados = new BancoDeDados(this);
        listPessoa.clear();
        listPessoa = mBancoDeDados.allPessoa();
        arrayAdapterPessoa = new ArrayAdapter<Pessoa>(this,android.R.layout.simple_list_item_1,listPessoa);
        lvPessoa.setAdapter(arrayAdapterPessoa);
    }

    private void inicializarBancoDeDados() {
        mBancoDeDados = new BancoDeDados(this);
        File database = getApplicationContext().getDatabasePath(BancoDeDados.NOMEDB);
        if (database.exists() == false){
            mBancoDeDados.getReadableDatabase();
            if (copiaBanco(this)){
                alert("Banco copiado com sucesso");
            }else{
                alert("Erro ao copiar o banco de dados");
            }
        }
    }

    private void alert(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    private boolean copiaBanco(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(BancoDeDados.NOMEDB);
            String outFile = BancoDeDados.LOCALDB + BancoDeDados.NOMEDB;
            OutputStream outputStream = new FileOutputStream(outFile);
            byte[] buff = new byte[1024];
            int legth = 0;
            while ((legth = inputStream.read(buff))>0){
                outputStream.write(buff,0,legth);
            }
            outputStream.flush();
            outputStream.close();
            return true;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }
}
