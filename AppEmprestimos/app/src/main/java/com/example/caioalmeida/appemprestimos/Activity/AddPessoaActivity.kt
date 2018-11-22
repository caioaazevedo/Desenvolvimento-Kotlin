package com.example.caioalmeida.appemprestimos.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.caioalmeida.appemprestimos.DataBaseHandler
import com.example.caioalmeida.appemprestimos.Model.Pessoa
import com.example.caioalmeida.appemprestimos.R
import kotlinx.android.synthetic.main.activity_add_pessoa.*
import org.json.JSONObject

class AddPessoaActivity : AppCompatActivity() {

    var nome = ""
    var telefone :Long = 0
    var endereco = ""
    var cep = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_pessoa)

        buttonCep.setOnClickListener {
            var cepEdt = editCampoCep.text.toString()
            obtemEndereco(cepEdt)
            cep = cepEdt.toInt()
        }

        buttonAddPessoa.setOnClickListener {
            if((editCampoNome.text.toString() == "") || (editCampoTelefone.text.toString() == "")
                || (editCampoCep.text.toString() == "") || (editCampoRua.text.toString() == "")){
                Toast.makeText(this, "Os campos marcados com * são obrigatórios!"
                    , Toast.LENGTH_LONG).show()
            } else {
                telefone = (editCampoTelefone.text.toString()).toLong()
                nome = editCampoNome.text.toString()
                endereco = editCampoRua.text.toString() + ", " +
                        editCampoComplemento.text.toString() + ", " +
                        editCampoBairro.text.toString() + ", " +
                        editCampoLocalidade.text.toString() + ", " +
                        editCampoUf.text.toString()

                var pessoa = Pessoa(
                    telefone = telefone,
                    nome = nome,
                    cep = cep,
                    endereco = endereco)

                var db = DataBaseHandler(this)
                db.insertPessoa(pessoa)
            }

        }
    }

    fun obtemEndereco(cep: String) {
        var url = "https://viacep.com.br/ws/" + cep + "/json/"

        val que = Volley.newRequestQueue(this)
        val req = object : StringRequest(Request.Method.GET, url,
            Response.Listener { response ->
                val json = response.toString()
                val rua = JSONObject(json).getString("logradouro").toString()
                val complemento = JSONObject(json).getString("complemento").toString()
                val bairro = JSONObject(json).getString("bairro").toString()
                val localidade = JSONObject(json).getString("localidade").toString()
                val uf = JSONObject(json).getString("uf").toString()

                editCampoRua.setText(rua, TextView.BufferType.EDITABLE)
                editCampoComplemento.setText(complemento, TextView.BufferType.EDITABLE)
                editCampoBairro.setText(bairro, TextView.BufferType.EDITABLE)
                editCampoLocalidade.setText(localidade, TextView.BufferType.EDITABLE)
                editCampoUf.setText(uf, TextView.BufferType.EDITABLE)

            }, Response.ErrorListener {
                Toast.makeText(this, "Erro ao obter o CPF", Toast.LENGTH_LONG).show()
            }){}
        que.add(req)
    }
}

