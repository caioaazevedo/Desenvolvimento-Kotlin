package com.example.caioalmeida.appemprestimos.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.caioalmeida.appemprestimos.DataBaseHandler
import com.example.caioalmeida.appemprestimos.Model.Pessoa
import com.example.caioalmeida.appemprestimos.R
import kotlinx.android.synthetic.main.activity_pessoa_info.*

class PessoaInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pessoa_info)

        var pessoa = intent.extras.getSerializable("pessoa") as Pessoa

        textMostraNome.text = pessoa.nome
        textMostraTelefone.text = pessoa.telefone.toString()
        textMostraCep.text = pessoa.cep.toString()
        textMostraEndereco.text = pessoa.endereco

        buttonPessoaDeletar.setOnClickListener {
            var db = DataBaseHandler(this)
            db.deletePessoa(pessoa)
        }

        buttonPessoaEditar.setOnClickListener {
            var intent = Intent(this, EditPessoaActivity::class.java)
            startActivity(intent)
        }
    }
}
