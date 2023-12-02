package com.gl4.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val students = arrayListOf<Student>(
        Student("Ons", "Ouahchi", 'F', false),
        Student("arwa", "Oueriemmi", 'F', true),
        Student("Ghada", "Adel", 'F', false),
        Student("kais", "Ouahchi", 'H', true),
        Student("Abdessalem", "Oueriemmi", 'H', false),
        Student("ahmed", "Adel", 'H', true),
    )
    private val recyclerView: RecyclerView by lazy { findViewById(R.id.recycler)
    }

    private val presence = listOf("", "present", "absent")
    private val spinner: Spinner by lazy { findViewById(R.id.spinner) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val studentAdapter = StudentAdapter(this, students)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = studentAdapter
        }

        val adapterSpinner = ArrayAdapter(this, android.R.layout.simple_spinner_item, presence)
        adapterSpinner .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapterSpinner


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var selectedItem= presence[position]
                studentAdapter.filter.filter(selectedItem)

               studentAdapter.notifyDataSetChanged()
            }

            override fun onNothingSelected(adapterView: AdapterView<*>?) {
                // Handle case when nothing is selected
            }
        }




    }
}
