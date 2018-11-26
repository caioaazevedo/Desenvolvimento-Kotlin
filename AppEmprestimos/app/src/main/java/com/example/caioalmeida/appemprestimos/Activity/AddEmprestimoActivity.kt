package com.example.caioalmeida.appemprestimos.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.caioalmeida.appemprestimos.Adapter.ListaItensAdapter
import com.example.caioalmeida.appemprestimos.Adapter.ListaPessoasAdapter
import com.example.caioalmeida.appemprestimos.DataBaseHandler
import com.example.caioalmeida.appemprestimos.Model.Item
import com.example.caioalmeida.appemprestimos.Model.Pessoa
import com.example.caioalmeida.appemprestimos.R
import kotlinx.android.synthetic.main.activity_add_emprestimo.*

class AddEmprestimoActivity : AppCompatActivity() {

    var pessoa : Pessoa? = null
    var item : Item? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_emprestimo)

        var db = DataBaseHandler(this)
        var lista_pessoas = db.getAllPessoa()

        var adapter = ListaPessoasAdapter(this, lista_pessoas)
        listPessoasEmprestimo.adapter = adapter

        var lista_itens = db.getAllItensDisponiveis()


        var adapterItem = ListaItensAdapter(this, lista_itens)
        listItensEmprestimo.adapter = adapterItem

        listPessoasEmprestimo.setOnItemClickListener { parent, view, position, id ->
            pessoa = lista_pessoas[position]
            textResumoEmpPessoa.text = pessoa!!.nome
        }

        listItensEmprestimo.setOnItemClickListener { parent, view, position, id ->
            item = lista_itens[position]
            textResumoEmpItem.text = item!!.nome
        }

        buttonProximoEmp.setOnClickListener {
            if(pessoa!!.nome == "" || item!!.nome == ""){
                Toast.makeText(this, "Pessoa e Itens são Obrigatórios!", Toast.LENGTH_LONG).show()
            } else {
                var intent = Intent(this, AddDataEmprestimoActivity::class.java)
                intent.putExtra("pessoa", pessoa)
                intent.putExtra("item", item)
                startActivity(intent)
            }
        }

    }
}
