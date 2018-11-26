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

        var db = DataBaseHandler(context!!)
        var lista_pessoas = db.getAllPessoa()

        val adapter = ListaPessoasAdapter(context, lista_pessoas)
        listPessoas.adapter = adapter

        listPessoas.setOnItemClickListener { parent, view, position, id ->
            val pessoa = lista_pessoas[position]
            var intent = Intent(context, PessoaInfoActivity::class.java)
            intent.putExtra("pessoa", pessoa)
            startActivity(intent)
        }

        btnAdicionarPessoas.setOnClickListener {
            var intent = Intent(context, AddPessoaActivity::class.java)
            startActivity(intent)

        }
    }
}