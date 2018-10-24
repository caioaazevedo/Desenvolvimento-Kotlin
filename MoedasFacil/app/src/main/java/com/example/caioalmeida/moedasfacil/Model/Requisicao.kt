package com.example.caioalmeida.moedasfacil.Model

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class Requisicao {
    var url = "https://economia.awesomeapi.com.br/all"
    var texto: String = " "

    fun requisicaoCotacao(siglaMoeda: String, context: Context) : String{

        val que = Volley.newRequestQueue(context)
        val req = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener {response ->

                    val moeda: JSONObject = response.getJSONObject(siglaMoeda)

                    var compra = moeda.getDouble("bid")
                    var venda = moeda.getDouble("ask")
                    var variacao = moeda.getDouble("varBid")
                    var porcentVariacao = moeda.getDouble("pctChange")
                    var dataHora = moeda.getString("create_date")

                    texto = "Compra: R$ " + compra.toString() + "\n" +
                            "Venda: R$ " + venda.toString() + "\n" +
                            "Variação: R$ " + variacao.toString() + "\n" +
                            "Variação (Porcentagem): " + porcentVariacao.toString() + "%\n" +
                            "Data/Hora: " + dataHora

            }, Response.ErrorListener {
                texto = "Erro de Requisição!"
            })
        que.add(req)

        return texto
    }

    fun requisicaoMoeda(siglaMoeda: String, context: Context): Double{
        var valorMoeda = 0.0
        val que = Volley.newRequestQueue(context)
        val req = JsonObjectRequest(Request.Method.GET, url, null,
            Response.Listener { response ->
                val moeda = response.getJSONObject(siglaMoeda)

                valorMoeda = moeda.getDouble("bid")

            }, Response.ErrorListener {
                valorMoeda = -1.0
            })
        que.add(req)

        return valorMoeda
    }
}