package com.example.caioalmeida.primeiraaulakotlin

import android.app.Activity
import android.app.VoiceInteractor
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    val url = "https://olinda.bcb.gov.br/olinda/servico/PTAX/versao/v1/odata/Moedas?format=json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvNome.setText("Nome")
        btnOK.setOnClickListener{
//            var nome =  edtNome.text
//            tvNome.setText(nome)

            val que = Volley.newRequestQueue(this@MainActivity)
            val req = StringRequest(Request.Method.GET, url, Response.Listener { response ->
                tvNome.text = response.toString()
            }, Response.ErrorListener {
                Toast.makeText(this, "Erro de conexão!", Toast.LENGTH_LONG).show()
            })
            que.add(req)
        }
    }
}
