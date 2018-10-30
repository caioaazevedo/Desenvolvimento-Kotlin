package com.example.caioalmeida.financas.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import com.example.caioalmeida.financas.R
import com.example.caioalmeida.financas.model.Transacao
import com.example.caioalmeida.financas.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes = listOf(Transacao(BigDecimal(20.5), "Comida", Calendar.getInstance()),
            Transacao(BigDecimal(100.0), "Economia", Calendar.getInstance()))

//        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, transacoes)

        val adapter = ListaTransacoesAdapter(transacoes, this)
        lista_transacoes_listview.setAdapter(adapter)
    }
}