package com.example.caioalmeida.moedasfacil

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.caioalmeida.moedasfacil.Model.Requisicao
import kotlinx.android.synthetic.main.activity_conversao_real.*

class ConversaoRealActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversao_real)

        val requisicao = Requisicao()

        var textoMoeda = ""
        var simbolo = ""

        val moedas = arrayListOf("Dólar Comercial", "Dólar Turismo", "Dólar Canadense", "Euro",
            "Libra", "Peso Argentino", "Bitcoin")

        spinMoedaReal.adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, moedas)
        spinMoedaReal.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                textResultadoConversao.text = "Por favor, selecione uma Moeda!"
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var texto = moedas.get(position)
                when(texto) {
                    "Dólar Comercial" -> {
                        textoMoeda  = "USD"
                        simbolo = "$"
                    }
                    "Dólar Turismo" -> {
                        textoMoeda  = "USDT"
                        simbolo = "$"
                    }
                    "Dólar Canadense" -> {
                        textoMoeda  = "CAD"
                        simbolo = "$"
                    }
                    "Euro" -> {
                        textoMoeda  = "EUR"
                        simbolo = "€"
                    }
                    "Libra" -> {
                        textoMoeda  = "GBP"
                        simbolo = "£"
                    }
                    "Peso Argentino" -> {
                        textoMoeda  = "ARS"
                        simbolo = "$"
                    }
                    "Bitcoin" -> {
                        textoMoeda  = "BTC"
                        simbolo = "฿"
                    }
                }
            }
        }

        buttonConversaoReal.setOnClickListener {
            var valor = 0.0

            var valorEdit = editConversaoReal.text.toString().toDouble()

            valor = requisicao.requisicaoMoeda(textoMoeda, this)

            var conversao = valorEdit / valor

            textResultadoConversao.text = simbolo + " " + conversao

        }
    }
}