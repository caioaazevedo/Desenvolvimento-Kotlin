package com.example.caioalmeida.conversormoedas

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.caioalmeida.conversormoedas.R.id.buttonDolarComercial
import com.example.caioalmeida.conversormoedas.R.id.textConversao
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : Activity() {

    var url = "https://economia.awesomeapi.com.br/all"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonDolarComercial.setOnClickListener {
            val que = Volley.newRequestQueue(this@MainActivity)
            val req = JsonObjectRequest(Request.Method.GET, url, null,
                Response.Listener {response ->

                    val dolar: JSONObject = response.getJSONObject("USD")
                    var valor = dolar.getDouble("bid")
                    var texto: String = valor.toString()
                    textConversao.setText(texto)

                }, Response.ErrorListener {
                    textConversao.setText("Erro de Requisição!")
                })
            que.add(req)
        }


    }

}
