package com.example.caioalmeida.appemprestimos.Model

import android.net.Uri
import java.io.Serializable

class Item : Serializable{
    var id: Int = 0
    var nome: String = ""
    var imagem: String? = ""
    var situacao: Int = 0

    constructor()

    constructor(nome: String, imagem: String?, situacao: Int){
        this.nome = nome
        this.imagem = imagem
        this.situacao = situacao
    }
}