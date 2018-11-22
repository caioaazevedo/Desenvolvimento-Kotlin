package com.example.caioalmeida.appemprestimos.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.caioalmeida.appemprestimos.Model.Pessoa
import com.example.caioalmeida.appemprestimos.R
import kotlinx.android.synthetic.main.list_pessoas.view.*

class ListaPessoasAdapter(private val context: Context?, private val pessoas: MutableList<Pessoa>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.list_pessoas, parent, false)

        val pessoa = pessoas[position]

        view.textNomePessoa.text = pessoa.nome
        view.textTelefonePessoa.text = pessoa.telefone.toString()

        return view
    }

    override fun getItem(position: Int): Pessoa {
        return pessoas[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return pessoas.size
    }
}