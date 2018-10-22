package com.example.caioalmeida.moedasfacil.Activities

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.caioalmeida.moedasfacil.Model.Requisicao
import com.example.caioalmeida.moedasfacil.R
import kotlinx.android.synthetic.main.activity_cotacao.*

class CotacaoActivity : Activity() {


    var textoMoeda: String = ""
    var cotacaoMoeda: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cotacao)

        val requisicao = Requisicao()

        val moedas = arrayOf("Dólar Comercial", "Dólar Turismo", "Dólar Canadense", "Euro",
            "Libra", "Peso Argentino", "Bitcoin")

        spinMoeda.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, moedas)

        spinMoeda.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                textCotacaoMoeda.setText("Por favor, selecione uma Moeda!")            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //textCotacaoMoeda.text = moedas.get(position)

                textoMoeda = moedas.get(position)
                when(textoMoeda) {
                    "Dólar Comercial" -> textoMoeda  = "USD"
                    "Dólar Turismo" -> textoMoeda  = "USDT"
                    "Dólar Canadense" -> textoMoeda  = "CAD"
                    "Euro" -> textoMoeda  = "EUR"
                    "Libra" -> textoMoeda  = "GBP"
                    "Peso Argentino" -> textoMoeda  = "ARS"
                    "Bitcoin" -> textoMoeda  = "BTC"
                }
            }
        }

        buttonCotacaoMoeda.setOnClickListener {
            cotacaoMoeda = requisicao.requisicaoCotacao(textoMoeda, this@CotacaoActivity)
            textCotacaoMoeda.text = cotacaoMoeda
        }
    }
}
