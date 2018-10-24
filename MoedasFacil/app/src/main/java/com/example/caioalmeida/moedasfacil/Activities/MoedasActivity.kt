package com.example.caioalmeida.moedasfacil.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.caioalmeida.moedasfacil.Adapter.MoedasAdapter
import com.example.caioalmeida.moedasfacil.InfoMoedasActivity
import com.example.caioalmeida.moedasfacil.Model.Moedas
import com.example.caioalmeida.moedasfacil.R
import kotlinx.android.synthetic.main.activity_moedas.*

class MoedasActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_moedas)

        val lista_moedas = arrayListOf(Moedas("Dólar", "$", "USD",
            "O dólar dos Estados Unidos ou dólar americano é a moeda oficial dos " +
                    "Estados Unidos e utilizada no mundo inteiro, tanto em reservas internacionais" +
                    " como em livre circulação em alguns países.", R.drawable.bandeira_eua),
            Moedas("Dólar Canadense", "$", "CAD", "O dólar canadense ou dólar canadiano é a moeda do " +
                    "Canadá, cujo código ISO 4217 é CAD. É normalmente abreviado com o símbolo $ ou C$.",
                R.drawable.bandeira_canada), Moedas("Euro", "€", "EUR",
                "Euro é a moeda oficial da zona Euro, a qual é constituída por 19 dos 28 estados-membro " +
                        "da União Europeia.", R.drawable.bandeira_euro), Moedas("Libra Esterlina", "£", "GBP",
                "A Libra esterlina é a moeda oficial de todo o Reino Unido e é considerada uma das " +
                        "mais antigas em circulação no mundo.", R.drawable.bandeira_reino_unido),
            Moedas("Peso Argentino", "$", "ARS", "O peso argentino é a moeda atual e oficial da Argentina. " + "" +
                    "O peso argentino (atual) começou a circular no ano de 1992, de acordo com a Lei de Conversibilidade."
            , R.drawable.bandeira_argentina), Moedas("Bitcoin", "฿", "BTC",
                "Bitcoin é uma criptomoeda descentralizada, sendo uma forma de dinheiro eletrônico.", R.drawable.bitcoin),
            Moedas("Real", "R$", "BRL", "O Real é a moeda corrente oficial da " +
                    "República Federativa do Brasil que a adotou 1 de julho de 1994.", R.drawable.bandeira_brasil))

        val adapter = MoedasAdapter(this, lista_moedas)

        listMoedas.adapter = adapter

        var intent = Intent(this, InfoMoedasActivity::class.java)

        listMoedas.setOnItemClickListener { parent, view, position, id ->
            val moeda = lista_moedas.get(position)
            intent.putExtra("obj", moeda)
            startActivity(intent)
        }
    }
}
