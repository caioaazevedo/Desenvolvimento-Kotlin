package com.example.caioalmeida.moedasfacil

import android.app.Activity
import android.os.Bundle
import com.example.caioalmeida.moedasfacil.Model.Moedas
import kotlinx.android.synthetic.main.activity_info_moedas.*

class InfoMoedasActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_moedas)

        val moeda = intent.getSerializableExtra("obj") as Moedas

        imageInfo.setImageResource(moeda.bandeira)
        textInfoNome.text = "Nome: " + moeda.nome
        textInfoSimbolo.text = "Símbolo: " + moeda.simbolo
        textInfoCodigo.text = "Código: " + moeda.codigo
        textInfoDescricao.text = "Descrição: " + moeda.descricao
    }
}
