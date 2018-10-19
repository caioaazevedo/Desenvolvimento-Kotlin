package com.example.caioalmeida.sharedpreference

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu_principal.*

class MenuPrincipalActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_principal)

        val myPreference = MyPreference(this)

        btnSair.setOnClickListener {
            myPreference.setLogout()
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
