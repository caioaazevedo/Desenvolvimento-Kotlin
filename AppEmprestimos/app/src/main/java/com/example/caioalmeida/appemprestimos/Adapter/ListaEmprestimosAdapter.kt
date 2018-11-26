package com.example.caioalmeida.appemprestimos.Adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.caioalmeida.appemprestimos.DataBaseHandler
import com.example.caioalmeida.appemprestimos.Model.Emprestimo
import com.example.caioalmeida.appemprestimos.Model.Item
import com.example.caioalmeida.appemprestimos.Model.Pessoa
import com.example.caioalmeida.appemprestimos.R
import kotlinx.android.synthetic.main.list_emprestimos.view.*
import kotlinx.android.synthetic.main.list_itens.view.*
import java.text.SimpleDateFormat
import java.util.*

class ListaEmprestimosAdapter(private val context: Context?, private val emprestimos: MutableList<Emprestimo>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.list_emprestimos, parent, false)

        var emprestimo = emprestimos[position]

        var db = DataBaseHandler(context!!)

        val formatoData = "dd/MM/yyyy"
        val formato = SimpleDateFormat(formatoData)
        val data = formato.parse(emprestimo.dataDevolucao)

        if(data.time > Calendar.getInstance().time.time){
            db.updateSituacaoEmprestimo(emprestimo.id)
        }

        var pessoa = db.getPessoaById(emprestimo.idPessoa)

        var item = db.getItemById(emprestimo.idItem)

        view.textNomePessoaEmp.text = pessoa.nome
        view.textNomeItemEmp.text = item.nome
        if(emprestimo.situacao == 1){
            view.viewSituacaoEmp.setBackgroundColor(Color.parseColor("#167916"))

        } else {
            view.viewSituacaoEmp.setBackgroundColor(Color.parseColor("#b51d17"))
        }

        return view
    }

    override fun getItem(position: Int): Emprestimo {
        return emprestimos[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return emprestimos.size
    }
}