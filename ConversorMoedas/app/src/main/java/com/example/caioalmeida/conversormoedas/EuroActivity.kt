package com.example.caioalmeida.conversormoedas

import android.app.Activity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_euro.*
import org.json.JSONObject

class EuroActivity : Activity() {

    var url = "https://economia.awesomeapi.com.br/all"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_euro)

        btnEuro.setOnClickListener {
            textTituloEuro.setText("Cotação do Euro:")

            val que = Volley.newRequestQueue(this@EuroActivity)
            val req = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener { response ->

                    val dolarTurismo: JSONObject = response.getJSONObject("EUR")
                    var compra = dolarTurismo.getDouble("bid")
                    var venda = dolarTurismo.getDouble("ask")
                    var variacao = dolarTurismo.getDouble("varBid")
                    var porcentVariacao = dolarTurismo.getDouble("pctChange")
                    var dataHora = dolarTurismo.getString("create_date")

                    var texto: String = "Compra: R$ " + compra.toString() + "\n" +
                            "Venda: R$ " + venda.toString() + "\n" +
                            "Variação: R$ " + variacao.toString() + "\n" +
                            "Variação (Porcentagem): " + porcentVariacao.toString() + "%\n" +
                            "Data/Hora: " + dataHora

                    textRespostaEuro.setText(texto)

                }, Response.ErrorListener {
                    textRespostaEuro.setText("Erro de Requisição!")
                })
            que.add(req)
        }
    }
}
