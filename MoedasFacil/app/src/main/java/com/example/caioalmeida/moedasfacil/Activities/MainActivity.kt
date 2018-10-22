package com.example.caioalmeida.moedasfacil.Activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.caioalmeida.moedasfacil.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCotacao.setOnClickListener {
            var intent = Intent(this@MainActivity, CotacaoActivity::class.java)
            startActivity(intent)
        }

        buttonConversao.setOnClickListener {
            var intent = Intent(this@MainActivity, ConversaoActivity::class.java)
            startActivity(intent)
        }

        buttonMoedas.setOnClickListener {
            var intent = Intent(this@MainActivity, MoedasActivity::class.java)
            startActivity(intent)
        }
    }
}
