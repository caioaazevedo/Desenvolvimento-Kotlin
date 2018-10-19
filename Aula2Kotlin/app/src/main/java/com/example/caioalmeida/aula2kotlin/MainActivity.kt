package com.example.caioalmeida.aula2kotlin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreference = getSharedPreferences("PREFERENCE_NAME", Context.MODE_PRIVATE)

        btnGetValue.setOnClickListener {
            var nome = sharedPreference.getString("nome", "defaultName")
            textResult.setText(nome)
        }

        btnSetValue.setOnClickListener {
            var editor = sharedPreference.edit()
            editor.putString("nome", "Caio Azevedo")
            editor.commit()
        }

        btnClearValue.setOnClickListener {
            sharedPreference.edit().remove("nome").commit()
            //sharedPreference.edit().clear().commit()
        }

    }
}
