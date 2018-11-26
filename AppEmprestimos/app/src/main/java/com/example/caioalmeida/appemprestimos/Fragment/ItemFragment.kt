package com.example.caioalmeida.appemprestimos.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.caioalmeida.appemprestimos.Activity.AddItemActivity
import com.example.caioalmeida.appemprestimos.Activity.AddPessoaActivity
import com.example.caioalmeida.appemprestimos.Activity.ItemInfoActivity
import com.example.caioalmeida.appemprestimos.Adapter.ListaItensAdapter
import com.example.caioalmeida.appemprestimos.Adapter.ListaPessoasAdapter
import com.example.caioalmeida.appemprestimos.DataBaseHandler
import com.example.caioalmeida.appemprestimos.Model.Item
import com.example.caioalmeida.appemprestimos.R
import kotlinx.android.synthetic.main.activity_add_item.*
import kotlinx.android.synthetic.main.fragment_item.*
import kotlinx.android.synthetic.main.fragment_pessoa.*
import java.util.*

class ItemFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataAtual = Calendar.getInstance()

        var dia = dataAtual.get(Calendar.DAY_OF_MONTH)
        var mes = dataAtual.get(Calendar.MONTH)
        var ano = dataAtual.get(Calendar.YEAR)

        var data = dia.toString() + "/" + mes.toString() + "/" + ano.toString()


        var db = DataBaseHandler(context!!)
        var lista_itens = db.getAllItem()

        val adapter = ListaItensAdapter(context, lista_itens)
        listItens.adapter = adapter

        listItens.setOnItemClickListener { parent, view, position, id ->
            val item = lista_itens[position]
            var intent = Intent(context, ItemInfoActivity::class.java)
            intent.putExtra("item", item)
            startActivity(intent)
        }

        btnAdicionarItens.setOnClickListener {
            var intent = Intent(context, AddItemActivity::class.java)
            startActivity(intent)
        }
    }
}