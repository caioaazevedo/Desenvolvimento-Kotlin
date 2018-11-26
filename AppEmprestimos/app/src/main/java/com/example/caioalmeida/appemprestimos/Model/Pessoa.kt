package com.example.caioalmeida.appemprestimos.Model

import java.io.Serializable

class Pessoa : Serializable{
    var id: Int = 0
    var nome: String = ""
    var cep: Int = 0
    var telefone: Long = 0
    var endereco: String = ""

    constructor()

    constructor(nome: String, cep: Int, telefone: Long, endereco:String){
        this.nome = nome
        this.cep = cep
        this.telefone = telefone
        this.endereco = endereco
    }
}