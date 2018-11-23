package com.example.caioalmeida.appemprestimos.Activity

import android.graphics.Color
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.caioalmeida.appemprestimos.Model.Item
import com.example.caioalmeida.appemprestimos.R
import kotlinx.android.synthetic.main.activity_item_info.*

class ItemInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_info)

        var item = intent.extras.getSerializable("item") as? Item

        if (item != null) {
            imageMostrar.setImageURI(Uri.parse(item.imagem))
            textMostraNomeItem.text = item.nome
            if(item.situacao == 1){
                textSituacao.text = "Disponível"
                textSituacao.setTextColor(Color.parseColor("#167916"))
            } else {
                textSituacao.text = "Emprestado"
                textSituacao.setTextColor(Color.parseColor("#b51d17"))
            }
        }
    }
}
