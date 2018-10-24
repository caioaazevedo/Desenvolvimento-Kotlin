package com.example.caioalmeida.moedasfacil.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.caioalmeida.moedasfacil.Model.Moedas
import com.example.caioalmeida.moedasfacil.R

class MoedasAdapter(context: Context, lista: ArrayList<Moedas>) : BaseAdapter() {

    private val contexto: Context
    private val moedas: ArrayList<Moedas>

    init {
        contexto = context
        moedas = lista
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(contexto)
        val convertView = layoutInflater.inflate(R.layout.moedas_list, parent, false)

        var moeda = Moedas()

        moeda = moedas.get(position)

        val image = convertView.findViewById<ImageView>(R.id.imageBandeira)
        image.setImageResource(moeda.bandeira)

        val nome = convertView.findViewById<TextView>(R.id.textNome)
        nome.text = moeda.nome

        val codigo = convertView.findViewById<TextView>(R.id.textCodigo)
        codigo.text = moeda.codigo

        return convertView
    }

    override fun getItem(position: Int): Any {
        return moedas.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return moedas.count()
    }
}