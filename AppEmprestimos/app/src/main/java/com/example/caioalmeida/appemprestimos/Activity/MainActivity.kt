package com.example.caioalmeida.appemprestimos.Activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.caioalmeida.appemprestimos.Fragment.HomeFragment
import com.example.caioalmeida.appemprestimos.Fragment.ItemFragment
import com.example.caioalmeida.appemprestimos.Fragment.PessoaFragment
import com.example.caioalmeida.appemprestimos.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = HomeFragment()
        mostrarFragment(fragment)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val fragment = HomeFragment()
                    mostrarFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_pessoa -> {
                    val fragment = PessoaFragment()
                    mostrarFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.nav_item -> {
                    val fragment = ItemFragment()
                    mostrarFragment(fragment)
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }
    }
    fun mostrarFragment(fragment : Fragment) {
        val manager = supportFragmentManager

        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
