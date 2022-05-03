package com.fernandoaoborges.ageapp


import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        DatePickerDialog(this, DatePickerDialog.OnDateSetListener {
                view, year, month, dayOfMonth ->
            val tvSelectedDate = findViewById<TextView>(R.id.tvSelectedDate)
            val tvSelectedDateInMinute = findViewById<TextView>(R.id.tvSelectedDateInMinute)
            val dateSelected = "$dayOfMonth/$month/$year"
            tvSelectedDate.setText("$dayOfMonth/$month/$year")

            val formatDate = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
            val date = formatDate.parse(dateSelected)
            val selectDateInMinutes  = date!!.time / 60000
            val currentDate = formatDate.parse(formatDate.format(System.currentTimeMillis()))
            val currentDateInMinutes  = currentDate!!.time / 60000
            val differenceInMinutes  = currentDateInMinutes - selectDateInMinutes

            tvSelectedDateInMinute.setText(differenceInMinutes.toString())

//            Toast.makeText(this, "$dayOfMonth/$month/$year", Toast.LENGTH_LONG).show()

        }, year, month, day).show()

    }
}