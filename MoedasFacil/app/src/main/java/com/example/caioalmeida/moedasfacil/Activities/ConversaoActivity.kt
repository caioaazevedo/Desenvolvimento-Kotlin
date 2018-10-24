package com.example.caioalmeida.moedasfacil.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.caioalmeida.moedasfacil.ConversaoMoedasActivity
import com.example.caioalmeida.moedasfacil.ConversaoRealActivity
import com.example.caioalmeida.moedasfacil.Model.Requisicao
import com.example.caioalmeida.moedasfacil.R
import kotlinx.android.synthetic.main.activity_conversao.*

class ConversaoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversao)

        buttonMenuConversaoReal.setOnClickListener {
            var intent = Intent(this, ConversaoRealActivity::class.java)
            startActivity(intent)
        }

        buttonMenuConversaoOutra.setOnClickListener {
            var intent = Intent(this, ConversaoMoedasActivity::class.java)
            startActivity(intent)
        }
    }
}
