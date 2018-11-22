package com.example.caioalmeida.appemprestimos.Activity

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import com.example.caioalmeida.appemprestimos.R
import kotlinx.android.synthetic.main.activity_add_item.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AddItemActivity : AppCompatActivity() {

    private val GALLERY = 1

    var nome : String = ""
    var imagemItem : ByteArray? = null
    var dataInicio : Date? = null
    var dataTermino : Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        btnImagem.setOnClickListener {
            choosePhotoFromGallary()
        }

        obtemDatas()

        buttonAddItem.setOnClickListener {
            nome = editNome.text.toString()


        }

    }

    fun obtemDatas(){
        val dataAtual = Calendar.getInstance()

        var dia = dataAtual.get(Calendar.DAY_OF_MONTH)
        var mes = dataAtual.get(Calendar.MONTH)
        var ano = dataAtual.get(Calendar.YEAR)

        textInicioEmprestimo.setText(dia.toString() + "/" + mes.toString() + "/" + ano.toString())

        dia += 1

        textTerminoEmprestimo.setText(dia.toString() + "/" + mes.toString() + "/" + ano.toString())

        textInicioEmprestimo.setOnClickListener {
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                    view, year, monthOfYear, dayOfMonth ->

                val data = dayOfMonth.toString() + "/" + monthOfYear.toString() + "/" + year.toString()
                textInicioEmprestimo.text = data
                val formatoData = "dd/MM/yyyy"
                val formato = SimpleDateFormat(formatoData)
                dataInicio = formato.parse(data)
            }, ano, mes, dia)
            val calendar = Calendar.getInstance()
            datePicker.datePicker.minDate = calendar.timeInMillis
            datePicker.show()

        }

        textTerminoEmprestimo.setOnClickListener {
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                    view, year, monthOfYear, dayOfMonth ->

                val data = dayOfMonth.toString() + "/" + monthOfYear.toString() + "/" + year.toString()
                textTerminoEmprestimo.text = data
                val formatoData = "dd/MM/yyyy"
                val formato = SimpleDateFormat(formatoData)
                dataInicio = formato.parse(data)
            }, ano, mes, dia)
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH,1)
            datePicker.datePicker.minDate = calendar.timeInMillis
            datePicker.show()
        }
    }

    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }

    public override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY && data != null) {
            val contentURI = data!!.data
            try {
                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                imageView!!.setImageBitmap(bitmap)
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                imagemItem = baos.toByteArray()

            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this, "Erro!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
