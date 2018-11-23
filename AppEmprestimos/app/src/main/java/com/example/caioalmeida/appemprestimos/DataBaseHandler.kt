package com.example.caioalmeida.appemprestimos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.caioalmeida.appemprestimos.Model.Item
import com.example.caioalmeida.appemprestimos.Model.Pessoa

val DATABASE_NAME = "my-db"
val TABLE_NAME_PESSOA = "pessoas"
val COL_NAME_PESSOA = "nome"
val COL_TELEFONE_PESSOA = "telefone"
val COL_CEP_PESSOA = "cep"
val COL_ENDERECO_PESSOA = "endereco"

val TABLE_NAME_ITEM = "item"
val COL_ID_ITEM = "id"
val COL_NAME_ITEM = "nome"
val COL_IMAGEM = "imagem"
val COL_STATUS_ITEM = "situacao"

val createTablePessoa = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_PESSOA + " (" +
        COL_TELEFONE_PESSOA + " INTEGER PRIMARY KEY, " + COL_NAME_PESSOA + " VARCHAR(256), " +
        COL_CEP_PESSOA + " INTEGER, " + COL_ENDERECO_PESSOA + " VARCHAR(256));"

val createTableItem = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_ITEM + " (" +
        COL_ID_ITEM + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME_ITEM + " VARCHAR(256), " +
        COL_IMAGEM + " BLOB, " + COL_STATUS_ITEM + " INTEGER);"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(createTablePessoa)
        db?.execSQL(createTableItem)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertPessoa(pessoa: Pessoa){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_TELEFONE_PESSOA, pessoa.telefone)
        cv.put(COL_NAME_PESSOA, pessoa.nome)
        cv.put(COL_CEP_PESSOA, pessoa.cep)
        cv.put(COL_ENDERECO_PESSOA, pessoa.endereco)
        val result = db.insert(TABLE_NAME_PESSOA, null, cv)
        if (result == -1.toLong()){
            Toast.makeText(context, "Erro ao Cadastrar!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, pessoa.nome + " foi cadastrado(a)!", Toast.LENGTH_LONG).show()
        }
    }

    fun insertItem(item: Item){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME_ITEM, item.nome)
        cv.put(COL_IMAGEM, item.imagem)
        cv.put(COL_STATUS_ITEM, item.situacao)
        val result = db.insert(TABLE_NAME_ITEM, COL_IMAGEM, cv)
        if (result == -1.toLong()){
            Toast.makeText(context, "Erro ao Cadastrar!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, item.nome + " foi cadastrado(a)!", Toast.LENGTH_LONG).show()
        }

        db.execSQL("INSERT INTO " + TABLE_NAME_ITEM + " (" + COL_NAME_ITEM+ ", " + COL_IMAGEM + ", " +
                COL_STATUS_ITEM + ") VALUES (" + item.nome + ", " + item.imagem + ", " + item.situacao + ");")

        db.close()
    }

    fun getAllPessoa(): MutableList<Pessoa>{
        var list: MutableList<Pessoa> = ArrayList()

        val db = readableDatabase
        val query = "Select * from " + TABLE_NAME_PESSOA
        val result = db.rawQuery(query, null)

        if(result.moveToFirst()){
            do {
                var pessoa = Pessoa()
                pessoa.telefone = result.getString(result.getColumnIndex(COL_TELEFONE_PESSOA)).toLong()
                pessoa.nome = result.getString(result.getColumnIndex(COL_NAME_PESSOA))
                pessoa.cep = result.getString(result.getColumnIndex(COL_CEP_PESSOA)).toInt()
                pessoa.endereco = result.getString(result.getColumnIndex(COL_ENDERECO_PESSOA))
                list.add(pessoa)

            }while (result.moveToNext())
        }

        result.close()
        db.close()

        return list

    }

    fun getAllItem(): MutableList<Item>{
        var list: MutableList<Item> = ArrayList()

        val db = readableDatabase
        val query = "Select * from " + TABLE_NAME_ITEM
        val result = db.rawQuery(query, null)

        if(result.moveToFirst()){
            do {

                var item = Item()
                item.id = result.getString(result.getColumnIndex(COL_ID_ITEM)).toInt()
                item.nome = result.getString((result.getColumnIndex((COL_NAME_ITEM))))
                item.imagem = result.getString(result.getColumnIndex(COL_IMAGEM))
                item.situacao = result.getString(result.getColumnIndex(COL_STATUS_ITEM)).toInt()

            }while (result.moveToNext())
        }

        result.close()
        db.close()

        return list

    }

    fun getPessoaByTelefone(tel: Long): Pessoa{
        val db = readableDatabase
        val query = "Select * from " + TABLE_NAME_PESSOA + " where " + COL_TELEFONE_PESSOA + "=" + tel
        val result = db.rawQuery(query, null)

        var pessoa = Pessoa()

        if(result.moveToFirst()) {
            pessoa.telefone = result.getString(result.getColumnIndex(COL_TELEFONE_PESSOA)).toLong()
            pessoa.nome = result.getString(result.getColumnIndex(COL_NAME_PESSOA))
            pessoa.cep = result.getString(result.getColumnIndex(COL_CEP_PESSOA)).toInt()
            pessoa.endereco = result.getString(result.getColumnIndex(COL_ENDERECO_PESSOA))
        }

        result.close()
        db.close()

        return pessoa

    }

    fun updatePessoa(pessoa: Pessoa){
        val db = this.writableDatabase
        var telefonePessoa = pessoa.telefone
        var cv = ContentValues()
        cv.put(COL_TELEFONE_PESSOA, pessoa.telefone)
        cv.put(COL_NAME_PESSOA, pessoa.nome)
        cv.put(COL_CEP_PESSOA, pessoa.cep)
        cv.put(COL_ENDERECO_PESSOA, pessoa.endereco)
        val result = db.update(TABLE_NAME_PESSOA, cv, COL_TELEFONE_PESSOA + "=" + telefonePessoa, null)
        if (result == -1){
            Toast.makeText(context, "Erro ao Cadastrar!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, pessoa.nome + " foi editado(a)!", Toast.LENGTH_LONG).show()
        }
    }

    fun deletePessoa(telefone: Long){
        val db = writableDatabase

        db.execSQL("DELETE FROM " + TABLE_NAME_PESSOA + " WHERE " + COL_TELEFONE_PESSOA + "=" + telefone)

        Toast.makeText(context, "Pessoa deletada com Sucesso!", Toast.LENGTH_LONG).show()

        db.close()

    }

}