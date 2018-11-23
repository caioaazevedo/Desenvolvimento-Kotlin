package com.example.caioalmeida.appemprestimos.Activity

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AlertDialog
import android.widget.Toast
import com.example.caioalmeida.appemprestimos.DataBaseHandler
import com.example.caioalmeida.appemprestimos.Model.Item
import com.example.caioalmeida.appemprestimos.R
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.activity_add_item.*
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class AddItemActivity : AppCompatActivity() {

    private val GALLERY = 1
    val REQUEST_IMAGE_CAPTURE = 1
    val TAKE_PHOTO_REQUEST: Int = 2
    val PICK_PHOTO_REQUEST: Int = 1
    var fileUri: Uri? = null

    var nome : String = ""
    var imagemItem : String? = null
    val SITUACAO = 1
//    var dataInicio : Date? = null
//    var dataTermino : Date? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)

        imageView.setOnClickListener {
//            choosePhotoFromGallary()
            askCameraPermission()
        }

//        obtemDatas()

        buttonAddItem.setOnClickListener {
            nome = editNome.text.toString()

            var item = Item(nome = nome, imagem = imagemItem, situacao = SITUACAO)

            var db = DataBaseHandler(this)
            db.insertItem(item)
        }

    }

//    fun obtemDatas(){
//        val dataAtual = Calendar.getInstance()
//
//        var dia = dataAtual.get(Calendar.DAY_OF_MONTH)
//        var mes = dataAtual.get(Calendar.MONTH)
//        var ano = dataAtual.get(Calendar.YEAR)
//
//        textInicioEmprestimo.setText(dia.toString() + "/" + mes.toString() + "/" + ano.toString())
//
//        dia += 1
//
//        textTerminoEmprestimo.setText(dia.toString() + "/" + mes.toString() + "/" + ano.toString())
//
//        textInicioEmprestimo.setOnClickListener {
//            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
//                    view, year, monthOfYear, dayOfMonth ->
//
//                val data = dayOfMonth.toString() + "/" + monthOfYear.toString() + "/" + year.toString()
//                textInicioEmprestimo.text = data
//                val formatoData = "dd/MM/yyyy"
//                val formato = SimpleDateFormat(formatoData)
//                dataInicio = formato.parse(data)
//            }, ano, mes, dia)
//            val calendar = Calendar.getInstance()
//            datePicker.datePicker.minDate = calendar.timeInMillis
//            datePicker.show()
//
//        }
//
//        textTerminoEmprestimo.setOnClickListener {
//            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
//                    view, year, monthOfYear, dayOfMonth ->
//
//                val data = dayOfMonth.toString() + "/" + monthOfYear.toString() + "/" + year.toString()
//                textTerminoEmprestimo.text = data
//                val formatoData = "dd/MM/yyyy"
//                val formato = SimpleDateFormat(formatoData)
//                dataInicio = formato.parse(data)
//            }, ano, mes, dia)
//            val calendar = Calendar.getInstance()
//            calendar.add(Calendar.DAY_OF_MONTH,1)
//            datePicker.datePicker.minDate = calendar.timeInMillis
//            datePicker.show()
//        }
//    }



//    //pick a photo from gallery
//    private fun pickPhotoFromGallery() {
//        val pickImageIntent = Intent(Intent.ACTION_PICK,
//            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//
//        startActivityForResult(pickImageIntent, AppConstants.PICK_PHOTO_REQUEST)
//    }

    //launch the camera to take photo via intent
    private fun launchCamera() {
        val values = ContentValues(1)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        fileUri = contentResolver
            .insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values)
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(packageManager) != null) {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                    or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
            startActivityForResult(intent, TAKE_PHOTO_REQUEST)
        }
    }

    //ask for permission to take photo
    fun askCameraPermission(){
        Dexter.withActivity(this)
            .withPermissions(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(report: MultiplePermissionsReport) {/* ... */
                    if(report.areAllPermissionsGranted()){
                        //once permissions are granted, launch the camera
                        launchCamera()
                    }else{
                        Toast.makeText(this@AddItemActivity, "All permissions need to be granted to take photo", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onPermissionRationaleShouldBeShown(permissions: List<PermissionRequest>, token: PermissionToken) {/* ... */
                    //show alert dialog with permission options
                    AlertDialog.Builder(this@AddItemActivity)
                        .setTitle(
                            "Permissions Error!")
                        .setMessage(
                            "Please allow permissions to take photo with camera")
                        .setNegativeButton(
                            android.R.string.cancel,
                            { dialog, _ ->
                                dialog.dismiss()
                                token?.cancelPermissionRequest()
                            })
                        .setPositiveButton(android.R.string.ok,
                            { dialog, _ ->
                                dialog.dismiss()
                                token?.continuePermissionRequest()
                            })
                        .setOnDismissListener({
                            token?.cancelPermissionRequest() })
                        .show()
                }

            }).check()

    }

    //override function that is called once the photo has been taken
    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        if (resultCode == Activity.RESULT_OK
            && requestCode == TAKE_PHOTO_REQUEST
        ) {
            //photo from camera
            //display the photo on the imageview
            imagemItem = fileUri.toString()
            imageView.setImageURI(fileUri)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

//    fun choosePhotoFromGallary() {
//        val galleryIntent = Intent(Intent.ACTION_PICK,
//            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
//        startActivityForResult(galleryIntent, GALLERY)
//    }
//
//    public override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {
//
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == GALLERY && data != null) {
//            val contentURI = data!!.data
//            try {
//                val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
//                imageView!!.setImageBitmap(bitmap)
//                val baos = ByteArrayOutputStream()
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//                imagemItem = baos.toByteArray()
//
//            } catch (e: IOException) {
//                e.printStackTrace()
//                Toast.makeText(this, "Erro!", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
}
