package com.example.caioalmeida.appemprestimos.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.caioalmeida.appemprestimos.Activity.AddPessoaActivity
import com.example.caioalmeida.appemprestimos.Activity.PessoaInfoActivity
import com.example.caioalmeida.appemprestimos.Adapter.ListaPessoasAdapter
import com.example.caioalmeida.appemprestimos.DataBaseHandler
import com.example.caioalmeida.appemprestimos.R
import kotlinx.android.synthetic.main.fragment_pessoa.*

class PessoaFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_pessoa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        val lista_pessoas = listOf(
//            Pessoa(
//                nome = "Caio",
//                cep = 71919000,
//                telefone = 936565,
//                endereco = "Rua 18, Alameda Norte, lote 12"
//            ), Pessoa(
//                nome = "Marlene",
//                cep = 815698,
//                telefone = 91436554,
//                endereco = "Rua 35 Norte, Lote 05"
//            ), Pessoa(
//                nome = "Arnaldo",
//                cep = 1818100,
//                telefone = 7777777,
//                endereco = "Rua dos Bobos, numero 7"
//                )
//
//        )

//        val pessoa = Entities.Pessoa(0, "Default", 0, "Default")
//
//        MyApplication.database?.pessoaDao()?.insertPessoa(pessoa)
//
//        var lista_pessoas = MyApplication.database?.pessoaDao()?.getAllPessoas()

        var db = DataBaseHandler(context!!)
        var lista_pessoas = db.getAllPessoa()

        val adapter = ListaPessoasAdapter(context, lista_pessoas)
        listPessoas.adapter = adapter

        listPessoas.setOnItemClickListener { parent, view, position, id ->
            val pessoa = lista_pessoas[position]
            var intent = Intent(context, PessoaInfoActivity::class.java)
            intent.putExtra("pessoa", pessoa.telefone)
            startActivity(intent)
        }

        btnAdicionarPessoas.setOnClickListener {
            var intent = Intent(context, AddPessoaActivity::class.java)
            startActivity(intent)

        }
    }
}