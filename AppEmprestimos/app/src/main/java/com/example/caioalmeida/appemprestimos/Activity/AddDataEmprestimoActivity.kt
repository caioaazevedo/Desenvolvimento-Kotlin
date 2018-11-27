package com.example.caioalmeida.appemprestimos.Activity

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.caioalmeida.appemprestimos.DataBaseHandler
import com.example.caioalmeida.appemprestimos.Model.Emprestimo
import com.example.caioalmeida.appemprestimos.Model.Item
import com.example.caioalmeida.appemprestimos.Model.Pessoa
import com.example.caioalmeida.appemprestimos.R
import kotlinx.android.synthetic.main.activity_add_data_emprestimo.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

class AddDataEmprestimoActivity : AppCompatActivity() {

    var dataEmprestimo: String = ""
    var dataDevolucao: String = ""

    var emprestimoDate : Date = Calendar.getInstance().time
    var devolucaoDate : Date = Calendar.getInstance().time

    val dataAtual = Calendar.getInstance()

    var dia = dataAtual.get(Calendar.DAY_OF_MONTH)
    var mes = dataAtual.get(Calendar.MONTH)
    var ano = dataAtual.get(Calendar.YEAR)

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder
    private val chanelId = "com.example.caioalmeida.appemprestimos.Activity"
    private val description = "Empréstimo Realizado"
    private var id = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data_emprestimo)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        var pessoa = intent.extras.getSerializable("pessoa") as Pessoa
        var item = intent.extras.getSerializable("item") as Item

        dataEmprestimo = dia.toString() + "/" + mes.toString() + "/" + ano.toString()

        editDataEmpEmp.setText(dataEmprestimo)

        dia += 1

        dataDevolucao = dia.toString() + "/" + mes.toString() + "/" + ano.toString()

        editDataDevEmp.setText(dataDevolucao)

        obtemDatas()

        buttonCriarEmprestimo.setOnClickListener {
            var emprestimo = Emprestimo(
                idPessoa = pessoa.id,
                idItem = item.id,
                dataEmprestimo = dataEmprestimo,
                dataDevolucao = dataDevolucao,
                situacao = 1)

            var db = DataBaseHandler(this)
            db.insertEmprestimo(emprestimo)
            db.updateSituacaoItem(emprestimo.idItem)

            val intent = Intent(this, AddDataEmprestimoActivity::class.java)
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(chanelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.GREEN
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this,  chanelId)
                    .setContentTitle("Emprestimo")
                    .setContentText("Emprestimo Realizado!")
                    .setSmallIcon(R.drawable.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher))
                    .setContentIntent(pendingIntent)
            } else {
                builder = Notification.Builder(this)
                    .setContentTitle("Emprestimo")
                    .setContentText("Emprestimo Realizado!")
                    .setSmallIcon(R.drawable.ic_launcher_round)
                    .setLargeIcon(BitmapFactory.decodeResource(this.resources, R.drawable.ic_launcher))
                    .setContentIntent(pendingIntent)
            }

            notificationManager.notify(id, builder.build())

            id++
        }
    }

    fun obtemDatas() {
        obtemDataEmprestimo()
        obtemDataDevolucao()
    }

    fun obtemDataEmprestimo(){
        editDataEmpEmp.setOnClickListener {
            val datePicker =
                DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    dataEmprestimo = dayOfMonth.toString() + "/" + monthOfYear.toString() + "/" + year.toString()
                    editDataEmpEmp.setText(dataEmprestimo, TextView.BufferType.EDITABLE)
                    val formatoData = "dd/MM/yyyy"
                    val formato = SimpleDateFormat(formatoData)
                    emprestimoDate = formato.parse(dataEmprestimo)
                }, ano, mes, dia)
            val calendar = Calendar.getInstance()
            datePicker.datePicker.minDate = calendar.timeInMillis
            datePicker.show()

        }
    }

    fun obtemDataDevolucao(){
        editDataDevEmp.setOnClickListener {
            val datePicker =
                DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->

                    dataDevolucao = dayOfMonth.toString() + "/" + monthOfYear.toString() + "/" + year.toString()
                    editDataDevEmp.setText(dataDevolucao, TextView.BufferType.EDITABLE)
                    val formatoData = "dd/MM/yyyy"
                    val formato = SimpleDateFormat(formatoData)
                    devolucaoDate = formato.parse(dataDevolucao)
                }, ano, mes, dia)
            val calendar = Calendar.getInstance()
            calendar.add(calcularDiferenca(), 1)
            datePicker.datePicker.minDate = calendar.timeInMillis
            datePicker.show()
        }
    }

    fun calcularDiferenca() : Int{

        var hoje = Calendar.getInstance().time
        var data = emprestimoDate

        var dif = data!!.day - hoje.day

        return dif
    }
}
