package com.example.aviasales

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val spinner_from = findViewById<Spinner>(R.id.from)
        val spinner_to = findViewById<Spinner>(R.id.to)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.sities_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_from.adapter = adapter
        spinner_to.adapter = adapter

        val dateInputFrom = findViewById<EditText>(R.id.date_from)

        val dateInputTo = findViewById<EditText>(R.id.date_to)


        dateInputFrom.setOnClickListener {
            dateInputFrom.clearFocus()
            showDatePicker(dateInputFrom)
        }

        dateInputTo.setOnClickListener {
            dateInputTo.clearFocus()
            showDatePicker(dateInputTo)
        }
    }

    private fun showDatePicker(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                editText.setText(selectedDate)
            },
            year, month, day
        )

        datePickerDialog.show()
    }
}