package com.example.caioalmeida.moedasfacil

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.caioalmeida.moedasfacil.Model.Requisicao
import kotlinx.android.synthetic.main.activity_conversao_moedas.*

class ConversaoMoedasActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversao_moedas)

        val requisicao = Requisicao()

        var textoMoeda = ""

        val moedas = arrayListOf("Dólar Comercial", "Dólar Turismo", "Dólar Canadense", "Euro",
            "Libra", "Peso Argentino", "Bitcoin")

        spinMoedaOutra.adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, moedas)
        spinMoedaOutra.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                textResultadoConversaoMoeda.text = "Por favor, selecione uma Moeda!"
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var texto = moedas.get(position)
                when(texto) {
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

        buttonConversaoMoeda.setOnClickListener {
            var valor = 0.0

            var valorEdit = editConversaoMoeda.text.toString().toDouble()

            valor = requisicao.requisicaoMoeda(textoMoeda, this)

            var conversao = valorEdit * valor

            textResultadoConversaoMoeda.text = "R$ " + conversao

        }
    }
}
