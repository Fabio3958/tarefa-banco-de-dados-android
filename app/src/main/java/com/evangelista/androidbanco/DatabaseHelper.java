package com.evangelista.androidbanco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper
{
    public DatabaseHelper(@Nullable Context context) {
        super(context, "TarefaBanco", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE = "CREATE TABLE FUNCIONARIO (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOME TEXT NOT NULL," +
                "CPF TEXT NOT NULL," +
                "RG TEXT NOT NULL," +
                "DATANASCIMENTO TEXT NOT NULL," +
                "FUNCAO TEXT NOT NULL)";

        sqLiteDatabase.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS FUNCIONARIO");
        onCreate(sqLiteDatabase);

    }

    public void inserir(String nome, String cpf, String rg, String dataNascimento, String funcao) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("NOME", nome);
        cv.put("CPF", cpf);
        cv.put("RG", rg);
        cv.put("DATANASCIMENTO", dataNascimento);
        cv.put("FUNCAO", funcao);
        db.insert("FUNCIONARIO", null, cv);
        db.close();
    }

    public List<Funcionario> listar(){
        SQLiteDatabase db = getReadableDatabase();
        List<Funcionario> funcionarios = new ArrayList<>();
        String query = "select * from FUNCIONARIO";
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()){
            Funcionario funcionario = new Funcionario();
            int index;

            index = cursor.getColumnIndex("NOME");
            funcionario.setNome(cursor.getString(index));

            index = cursor.getColumnIndex("CPF");
            funcionario.setCpf(cursor.getString(index));

            index = cursor.getColumnIndex("RG");
            funcionario.setRg(cursor.getString(index));

            index = cursor.getColumnIndex("DATANASCIMENTO");
            funcionario.setDataNascimento(cursor.getString(index));

            index = cursor.getColumnIndex("FUNCAO");
            funcionario.setFuncao(cursor.getString(index));

            funcionarios.add(funcionario);
        }
        return funcionarios;
    }


    public List<Funcionario> listarEspecifico(String nome){
        SQLiteDatabase db = getReadableDatabase();
        List<Funcionario> funcionarios = new ArrayList<>();
        String query = "select * from FUNCIONARIO where nome = ?";
        String dados[] = new String[1];
        dados[0] = nome;
        Cursor cursor = db.rawQuery(query , dados);
        while(cursor.moveToNext()){
            Funcionario funcionario = new Funcionario();
            int index;

            index = cursor.getColumnIndex("NOME");
            funcionario.setNome(cursor.getString(index));

            index = cursor.getColumnIndex("CPF");
            funcionario.setCpf(cursor.getString(index));

            index = cursor.getColumnIndex("RG");
            funcionario.setRg(cursor.getString(index));

            index = cursor.getColumnIndex("DATANASCIMENTO");
            funcionario.setDataNascimento(cursor.getString(index));

            index = cursor.getColumnIndex("FUNCAO");
            funcionario.setFuncao(cursor.getString(index));

            funcionarios.add(funcionario);
        }
        cursor.close();
        return funcionarios;
    }

}
