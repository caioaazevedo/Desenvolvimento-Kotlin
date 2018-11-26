package com.example.caioalmeida.appemprestimos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.caioalmeida.appemprestimos.Model.Emprestimo
import com.example.caioalmeida.appemprestimos.Model.Item
import com.example.caioalmeida.appemprestimos.Model.Pessoa

val DATABASE_NAME = "my-db"
val TABLE_NAME_PESSOA = "pessoas"
val COL_ID_PESSOA = "idPessoa"
val COL_NAME_PESSOA = "nome"
val COL_TELEFONE_PESSOA = "telefone"
val COL_CEP_PESSOA = "cep"
val COL_ENDERECO_PESSOA = "endereco"

val TABLE_NAME_ITEM = "item"
val COL_ID_ITEM = "idItem"
val COL_NAME_ITEM = "nome"
val COL_IMAGEM = "imagem"
val COL_STATUS_ITEM = "situacaoItem"

val TABLE_NAME_EMPRESTIMO = "emprestimo"
val COL_ID_EMPRESTIMO = "idEmprestimo"
val COL_ID_PESSOA_F = "id_pessoa_fk"
val COL_ID_ITEM_F = "id_item_fk"
val COL_DATA_EMPRESTIMO = "data_emprestimo"
val COL_DATA_DEVOLUCAO = "data_devolucao"
val COL_STATUS = "situacaoEmprestimo"


val createTablePessoa = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_PESSOA + " (" +
        COL_ID_PESSOA + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_TELEFONE_PESSOA +
        " INTEGER, " + COL_NAME_PESSOA + " VARCHAR(256), " + COL_CEP_PESSOA + " INTEGER, "+
        COL_ENDERECO_PESSOA + " VARCHAR(256));"

val createTableItem = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_ITEM + " (" +
        COL_ID_ITEM + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME_ITEM + " VARCHAR(256), " +
        COL_IMAGEM + " BLOB, " + COL_STATUS_ITEM + " INTEGER);"

val createTableEmprestimo = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_EMPRESTIMO + " (" +
        COL_ID_EMPRESTIMO + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_ID_PESSOA + " INTEGER , " +
        COL_ID_ITEM + " INTEGER, " + COL_DATA_EMPRESTIMO + " VARCHAR(256), " + COL_DATA_DEVOLUCAO + " VARCHAR(256), " +
        COL_STATUS + " INTEGER, " + "FOREIGN KEY(" + COL_ID_PESSOA + ") REFERENCES " + TABLE_NAME_PESSOA +
        "(" + COL_ID_PESSOA_F + "), " + "FOREIGN KEY(" + COL_ID_ITEM + ") REFERENCES " + TABLE_NAME_ITEM +
        "(" + COL_ID_ITEM_F + "));"

class DataBaseHandler(var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1){
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(createTablePessoa)
        db?.execSQL(createTableItem)
        db?.execSQL(createTableEmprestimo)
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

        db.close()
    }

    fun insertItem(item: Item){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_NAME_ITEM, item.nome)
        cv.put(COL_IMAGEM, item.imagem)
        cv.put(COL_STATUS_ITEM, item.situacao)
        val result = db.insert(TABLE_NAME_ITEM, null, cv)
        if (result == -1.toLong()){
            Toast.makeText(context, "Erro ao Cadastrar!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, item.nome + " foi cadastrado(a)!", Toast.LENGTH_LONG).show()
        }

        db.close()
    }

    fun insertEmprestimo(emprestimo: Emprestimo){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_ID_PESSOA, emprestimo.idPessoa)
        cv.put(COL_ID_ITEM, emprestimo.idItem)
        cv.put(COL_DATA_EMPRESTIMO, emprestimo.dataEmprestimo)
        cv.put(COL_DATA_DEVOLUCAO, emprestimo.dataDevolucao)
        cv.put(COL_STATUS, emprestimo.situacao)

        val result = db.insert(TABLE_NAME_EMPRESTIMO, null, cv)
        if (result == -1.toLong()){
            Toast.makeText(context, "Erro ao Cadastrar!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Empréstimo foi cadastrado(a)!", Toast.LENGTH_LONG).show()
        }

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
                pessoa.id = result.getString(result.getColumnIndex(COL_ID_PESSOA)).toInt()
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

    fun getPessoaById(id: Int): Pessoa{
        val db = readableDatabase
        val query = "Select * from " + TABLE_NAME_PESSOA + " where " + COL_ID_PESSOA + "=" + id
        val result = db.rawQuery(query, null)

        var pessoa = Pessoa()

        if(result.moveToFirst()) {
            pessoa.id = result.getString(result.getColumnIndex(COL_ID_PESSOA)).toInt()
            pessoa.telefone = result.getString(result.getColumnIndex(COL_TELEFONE_PESSOA)).toLong()
            pessoa.nome = result.getString(result.getColumnIndex(COL_NAME_PESSOA))
            pessoa.cep = result.getString(result.getColumnIndex(COL_CEP_PESSOA)).toInt()
            pessoa.endereco = result.getString(result.getColumnIndex(COL_ENDERECO_PESSOA))
        }

        result.close()
        db.close()

        return pessoa

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
                list.add(item)
            }while (result.moveToNext())
        }

        result.close()
        db.close()

        return list

    }

    fun getItemById(id: Int): Item{
        val db = readableDatabase
        val query = "Select * from " + TABLE_NAME_ITEM + " where " + COL_ID_ITEM + "=" + id
        val result = db.rawQuery(query, null)

        var item = Item()

        if(result.moveToFirst()) {
            item.nome = result.getString((result.getColumnIndex((COL_NAME_ITEM))))
            item.imagem = result.getString(result.getColumnIndex(COL_IMAGEM))
            item.situacao = result.getString(result.getColumnIndex(COL_STATUS_ITEM)).toInt()
        }

        result.close()
        db.close()

        return item

    }

    fun getAllItensDisponiveis(): MutableList<Item>{
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
                if(item.situacao == 1){
                    list.add(item)
                }
            }while (result.moveToNext())
        }

        result.close()
        db.close()

        return list

    }

    fun getAllEmprestimos(): MutableList<Emprestimo>{
        var list: MutableList<Emprestimo> = ArrayList()

        val db = readableDatabase
        val query = "Select * from " + TABLE_NAME_EMPRESTIMO
        val result = db.rawQuery(query, null)

        if(result.moveToFirst()){
            do {

                var emprestimo = Emprestimo()
                emprestimo.idPessoa = result.getString(result.getColumnIndex(COL_ID_PESSOA)).toInt()
                emprestimo.idItem = result.getString(result.getColumnIndex(COL_ID_ITEM)).toInt()
                emprestimo.dataEmprestimo = result.getString(result.getColumnIndex(COL_DATA_EMPRESTIMO))
                emprestimo.dataDevolucao = result.getString(result.getColumnIndex(COL_DATA_DEVOLUCAO))
                emprestimo.situacao = result.getString(result.getColumnIndex(COL_STATUS)).toInt()
                list.add(emprestimo)
            }while (result.moveToNext())
        }

        result.close()
        db.close()

        return list

    }

    fun updatePessoa(pessoa: Pessoa){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_TELEFONE_PESSOA, pessoa.telefone)
        cv.put(COL_NAME_PESSOA, pessoa.nome)
        cv.put(COL_CEP_PESSOA, pessoa.cep)
        cv.put(COL_ENDERECO_PESSOA, pessoa.endereco)
        val result = db.update(TABLE_NAME_PESSOA, cv, COL_ID_PESSOA + "=" + pessoa.id, null)
        if (result == -1){
            Toast.makeText(context, "Erro ao Cadastrar!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, pessoa.nome + " foi editado(a)!", Toast.LENGTH_LONG).show()
        }
    }

    fun updateSituacaoItem(idItem: Int){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_STATUS_ITEM, 0)
        db.update(TABLE_NAME_ITEM, cv, COL_ID_ITEM + "=" + idItem, null)
    }

    fun updateSituacaoEmprestimo(idEmprestimo:  Int){
        val db = this.writableDatabase
        var cv = ContentValues()
        cv.put(COL_STATUS, 0)
        db.update(TABLE_NAME_EMPRESTIMO, cv, COL_ID_EMPRESTIMO + "=" + idEmprestimo, null)
    }

    fun deletePessoa(pessoa: Pessoa){
        val db = writableDatabase

        db.execSQL("DELETE FROM " + TABLE_NAME_PESSOA + " WHERE " + COL_ID_PESSOA + "=" + pessoa.id)

        Toast.makeText(context, "Pessoa deletada com Sucesso!", Toast.LENGTH_LONG).show()

        db.close()

    }

    fun deleteItem(item: Item){
        val db = writableDatabase

        db.execSQL("DELETE FROM " + TABLE_NAME_ITEM + " WHERE " + COL_ID_ITEM + "=" + item.id)

        Toast.makeText(context, "Item deletado com Sucesso!", Toast.LENGTH_LONG).show()

        db.close()

    }

}