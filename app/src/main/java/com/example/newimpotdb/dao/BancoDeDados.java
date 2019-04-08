package com.example.newimpotdb.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.newimpotdb.modelo.Pessoa;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by williangcarv on 22/08/17.
 */

public class BancoDeDados extends SQLiteOpenHelper {
    public static final String NOMEDB = "dbPessoa";
    public static final String LOCALDB = "/data/data/com.example.newimpotdb/databases/";
    private static final int VERSION = 1;
    private Context mContext;
    private SQLiteDatabase mSQSqLiteDatabase;


    public BancoDeDados(Context context) {
        super(context, NOMEDB, null, VERSION);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void openDataBase(){
        String dbPath = mContext.getDatabasePath(NOMEDB).getPath();
        if (mSQSqLiteDatabase != null && mSQSqLiteDatabase.isOpen()){
            return;
        }
        mSQSqLiteDatabase = SQLiteDatabase.openDatabase(dbPath,null, SQLiteDatabase.OPEN_READWRITE);
    }


    public List<Pessoa> allPessoa(){
        openDataBase();
        mSQSqLiteDatabase = this.getWritableDatabase();
        List<Pessoa> listPessoa = new ArrayList<>();
        String sql = "SELECT * FROM Pessoa ORDER BY nomePessoa";
        Cursor cursor = mSQSqLiteDatabase.rawQuery(sql,null);
        if (cursor.getCount()>0){
            if (cursor.moveToFirst()){
                do{
                    Pessoa p = new Pessoa();
                    p.setIdPessoa(cursor.getInt(0));
                    p.setNomePessoa(cursor.getString(1));
                    listPessoa.add(p);
                }while(cursor.moveToNext());
            }
        }
        cursor.close();
        mSQSqLiteDatabase.close();
        return listPessoa;

    }

}
