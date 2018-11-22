package com.example.caioalmeida.appemprestimos.Adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.caioalmeida.appemprestimos.Model.Item
import com.example.caioalmeida.appemprestimos.R
import kotlinx.android.synthetic.main.list_itens.view.*

class ListaItensAdapter(private val context: Context?, private val itens: List<Item>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.list_itens, parent, false)

        var item = itens[position]

        view.textNomeItem.text = item.nome
        if(item.situacao){
            view.textStatus.text = "Emprestado"
            view.textStatus.setTextColor(Color.parseColor("#167916"))
        } else {
            view.textStatus.text = "Expirado"
            view.textStatus.setTextColor(Color.parseColor("#b51d17"))
        }
        view.imageItem.setImageResource(item.imagem)

        return view
    }

    override fun getItem(position: Int): Item {
        return itens[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return itens.size
    }
}