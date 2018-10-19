package com.example.caioalmeida.sharedpreference

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myPreference = MyPreference(this)

        var isLoged = myPreference.getIfLoged()

        btnEntrar.setOnClickListener {

            var login = edtLogin.text.toString()
            var senha = edtSenha.text.toString()

            val usuario = HashMap<String, String>()
            usuario.put("login", "caio@gmail.com")
            usuario.put("senha", "123456")

            if((usuario.get("login").equals(login)) && (usuario.get("senha").equals(senha)) && (isLoged.equals(false))){
                myPreference.setLoged()
                var intent = Intent(this, BemVindoActivity::class.java)
                startActivity(intent)
            } else if ((usuario.get("login").equals(login)) && (usuario.get("senha").equals(senha)) && (isLoged.equals(true))){
                var intent = Intent(this, MenuPrincipalActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuário ou Senha incorretos!", Toast.LENGTH_LONG).show()
            }
        }
    }
}
