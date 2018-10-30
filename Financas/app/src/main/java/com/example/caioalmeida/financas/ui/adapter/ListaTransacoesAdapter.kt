package com.example.caioalmeida.financas.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.caioalmeida.financas.R
import com.example.caioalmeida.financas.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*
import java.text.SimpleDateFormat

class ListaTransacoesAdapter (transacoes: List<Transacao>,
                              context: Context): BaseAdapter() {

    private val transacoes = transacoes
    private val contexto = context

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(contexto).inflate(R.layout.transacao_item, parent, false)

        val transacao = transacoes[position]

        view.transacao_valor.text = transacao.valor.toString()
        view.transacao_categoria.text = transacao.categoria

        val formatoData = "dd/MM/yyyy"
        val formato = SimpleDateFormat(formatoData)
        val dataFormatada = formato.format(transacao.data.time)
        view.transacao_data.text = dataFormatada

        return view
    }

    override fun getItem(position: Int): Transacao {
        return transacoes[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }
}