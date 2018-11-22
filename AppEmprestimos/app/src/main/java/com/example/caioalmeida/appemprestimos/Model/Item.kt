package com.example.caioalmeida.appemprestimos.Model

import android.graphics.Bitmap
import java.io.Serializable
import java.util.*

class Item(
    val nome: String,
    val imagem: Int,
    val dataEmprestimo: String,
    val dataDevolucao: String,
    val situacao: Boolean
) : Serializable