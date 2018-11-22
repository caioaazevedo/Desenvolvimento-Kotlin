package com.example.caioalmeida.appemprestimos.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.caioalmeida.appemprestimos.DataBaseHandler
import com.example.caioalmeida.appemprestimos.Model.Pessoa
import com.example.caioalmeida.appemprestimos.R
import kotlinx.android.synthetic.main.activity_edit_pessoa.*
import org.json.JSONObject

class EditPessoaActivity : AppCompatActivity() {

    var nome = ""
    var telefone :Long = 0
    var endereco = ""
    var cep = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_pessoa)
        buttonCepEdit.setOnClickListener {
            var cepEdt = editCampoCepEdit.text.toString()
            obtemEndereco(cepEdt)
            cep = cepEdt.toInt()
        }

        buttonEditPessoa.setOnClickListener {
            if((editCampoNomeEdit.text.toString() == "") || (editCampoTelefoneEdit.text.toString() == "")
                || (editCampoCepEdit.text.toString() == "") || (editCampoRuaEdit.text.toString() == "")){
                Toast.makeText(this, "Os campos marcados com * são obrigatórios!"
                    , Toast.LENGTH_LONG).show()
            } else {
                telefone = (editCampoTelefoneEdit.text.toString()).toLong()
                nome = editCampoNomeEdit.text.toString()
                endereco = editCampoRuaEdit.text.toString() + ", " +
                        editCampoComplementoEdit.text.toString() + ", " +
                        editCampoBairroEdit.text.toString() + ", " +
                        editCampoLocalidadeEdit.text.toString() + ", " +
                        editCampoUfEdit.text.toString()

                var pessoa = Pessoa(
                    telefone = telefone,
                    nome = nome,
                    cep = cep,
                    endereco = endereco)

                var db = DataBaseHandler(this)
                db.updatePessoa(pessoa)

//                var intent = Intent(this, PessoaInfoActivity::class.java)
//                startActivity(intent)
            }

        }
    }

    fun obtemEndereco(cep: String) {
        var url = "https://viacep.com.br/ws/" + cep + "/json/"

        val que = Volley.newRequestQueue(this)
        val req = object : StringRequest(
            Request.Method.GET, url,
            Response.Listener { response ->
                val json = response.toString()
                val rua = JSONObject(json).getString("logradouro").toString()
                val complemento = JSONObject(json).getString("complemento").toString()
                val bairro = JSONObject(json).getString("bairro").toString()
                val localidade = JSONObject(json).getString("localidade").toString()
                val uf = JSONObject(json).getString("uf").toString()

                editCampoRuaEdit.setText(rua, TextView.BufferType.EDITABLE)
                editCampoComplementoEdit.setText(complemento, TextView.BufferType.EDITABLE)
                editCampoBairroEdit.setText(bairro, TextView.BufferType.EDITABLE)
                editCampoLocalidadeEdit.setText(localidade, TextView.BufferType.EDITABLE)
                editCampoUfEdit.setText(uf, TextView.BufferType.EDITABLE)

            }, Response.ErrorListener {
                Toast.makeText(this, "Erro ao obter o CPF", Toast.LENGTH_LONG).show()
            }){}
        que.add(req)
    }
}