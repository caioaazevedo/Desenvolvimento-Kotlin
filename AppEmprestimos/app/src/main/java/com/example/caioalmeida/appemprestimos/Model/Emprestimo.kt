package com.example.caioalmeida.appemprestimos.Model

import java.io.Serializable
import java.util.*

class Emprestimo : Serializable{

    var id: Int = 0
    var idPessoa: Int = 0
    var idItem: Int = 0
    var dataEmprestimo: String = ""
    var dataDevolucao: String = ""
    var situacao: Int = 0

    constructor()

    constructor(idPessoa: Int, idItem: Int, dataEmprestimo: String, dataDevolucao: String, situacao: Int){
        this.idPessoa = idPessoa
        this.idItem = idItem
        this.dataEmprestimo = dataEmprestimo
        this.dataDevolucao = dataDevolucao
        this.situacao = situacao
    }
}
