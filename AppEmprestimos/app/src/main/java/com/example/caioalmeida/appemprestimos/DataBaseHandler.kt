package com.example.caioalmeida.appemprestimos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.caioalmeida.appemprestimos.Model.Pessoa

val DATABASE_NAME = "my-db"
val TABLE_NAME = "pessoas"
val COL_NAME = "nome"
val COL_TELEFONE = "telefone"
val COL_CEP = "cep"
val COL_ENDERECO = "endereco"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {

        val createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            COL_TELEFONE + " INTEGER PRIMARY KEY, " + COL_NAME + " VARCHAR(256), " +
            COL_CEP + " INTEGER, " + COL_ENDERECO + " VARCHAR(256))"

        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertPessoa(pessoa: Pessoa){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_TELEFONE, pessoa.telefone)
        cv.put(COL_NAME, pessoa.nome)
        cv.put(COL_CEP, pessoa.cep)
        cv.put(COL_ENDERECO, pessoa.endereco)
        val result = db.insert(TABLE_NAME, null, cv)
        if (result == -1.toLong()){
            Toast.makeText(context, "Erro ao Cadastrar!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, pessoa.nome + " foi cadastrado(a)!", Toast.LENGTH_LONG).show()
        }
    }

    fun getAllPessoa(): MutableList<Pessoa>{
        var list: MutableList<Pessoa> = ArrayList()

        val db = readableDatabase
        val query = "Select * from " + TABLE_NAME
        val result = db.rawQuery(query, null)

        if(result.moveToFirst()){
            do {
                var pessoa = Pessoa()
                pessoa.telefone = result.getString(result.getColumnIndex(COL_TELEFONE)).toLong()
                pessoa.nome = result.getString(result.getColumnIndex(COL_NAME))
                pessoa.cep = result.getString(result.getColumnIndex(COL_CEP)).toInt()
                pessoa.endereco = result.getString(result.getColumnIndex(COL_ENDERECO))
                list.add(pessoa)

            }while (result.moveToNext())
        }

        result.close()
        db.close()

        return list

    }

    fun getPessoaByTelefone(tel: Long): Pessoa{
        val db = readableDatabase
        val query = "Select * from " + TABLE_NAME + " where " + COL_TELEFONE + "=" + tel
        val result = db.rawQuery(query, null)

        var pessoa = Pessoa()

        if(result.moveToFirst()) {
            pessoa.telefone = result.getString(result.getColumnIndex(COL_TELEFONE)).toLong()
            pessoa.nome = result.getString(result.getColumnIndex(COL_NAME))
            pessoa.cep = result.getString(result.getColumnIndex(COL_CEP)).toInt()
            pessoa.endereco = result.getString(result.getColumnIndex(COL_ENDERECO))
        }

        result.close()
        db.close()

        return pessoa

    }

    fun updatePessoa(pessoa: Pessoa){
        val db = this.writableDatabase
        var telefonePessoa = pessoa.telefone
        var cv = ContentValues()
        cv.put(COL_TELEFONE, pessoa.telefone)
        cv.put(COL_NAME, pessoa.nome)
        cv.put(COL_CEP, pessoa.cep)
        cv.put(COL_ENDERECO, pessoa.endereco)
        val result = db.update(TABLE_NAME, cv, COL_TELEFONE + "=" + telefonePessoa, null)
        if (result == -1){
            Toast.makeText(context, "Erro ao Cadastrar!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, pessoa.nome + " foi editado(a)!", Toast.LENGTH_LONG).show()
        }
    }

    fun deletePessoa(telefone: Long){
        val db = writableDatabase

        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COL_TELEFONE + "=" + telefone)

        Toast.makeText(context, "Pessoa deletada com Sucesso!", Toast.LENGTH_LONG).show()

        db.close()

    }

}