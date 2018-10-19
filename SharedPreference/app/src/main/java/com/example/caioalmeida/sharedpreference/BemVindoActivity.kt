package com.example.caioalmeida.sharedpreference

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_bem_vindo.*

class BemVindoActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bem_vindo)


        btnBemVindo.setOnClickListener {
            var intent = Intent(this, MenuPrincipalActivity::class.java)
            startActivity(intent)
        }
    }
}
