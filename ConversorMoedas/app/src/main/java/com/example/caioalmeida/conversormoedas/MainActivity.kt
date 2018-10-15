package com.example.caioalmeida.conversormoedas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonDolarComercial.setOnClickListener {
            val intent = Intent(this, DolarActivity::class.java)
            startActivity(intent)
        }

        buttonDolarTurismo.setOnClickListener {
            val intent = Intent(this, DolarTurismoActivity::class.java)
            startActivity(intent)

        }

        buttonEuro.setOnClickListener{
            val intent = Intent(this, EuroActivity::class.java)
            startActivity(intent)
        }

        buttonLibra.setOnClickListener {
            val intent = Intent(this, LibraActivity::class.java)
            startActivity(intent)
        }

        buttonBitcoin.setOnClickListener {
            val intent = Intent(this, BitcoinActivity::class.java)
            startActivity(intent)
        }
    }

}
