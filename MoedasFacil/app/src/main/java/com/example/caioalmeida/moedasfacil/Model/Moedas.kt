package com.example.caioalmeida.moedasfacil.Model

import java.io.Serializable

class Moedas : Serializable{

    var nome: String = ""
    var simbolo: String = ""
    var codigo: String = ""
    var descricao: String = ""
    var bandeira = 0

    constructor()

    constructor(nome: String, simbolo: String, codigo: String, descricao: String, bandeira: Int) {
        this.nome = nome
        this.simbolo = simbolo
        this.codigo = codigo
        this.descricao = descricao
        this.bandeira = bandeira
    }
}