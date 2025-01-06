package com.example.spinner

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private val roleList = mutableListOf(
        "Фрезеровщик",
        "Слесарь",
        "Токарь",
        "Доводчик деталей",
        "Программист",
        "Директор",
        "Машинист крана",
        "Тракторист"
    )
    private var role: String? = null
    private var people: MutableList<Person> = mutableListOf()
    private var person: Person? = null
    private var listAdapter: ListAdapter<Person>? = null

    private lateinit var toolbarTB: Toolbar
    private lateinit var nameET: EditText
    private lateinit var surnameET: EditText
    private lateinit var ageET: EditText
    private lateinit var spinnerRole: Spinner
    private lateinit var saveButtonBTN: Button
    private lateinit var listViewLV: ListView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        spinner()

        saveButtonBTN.setOnClickListener {
            person = Person(
                nameET.text.toString(),
                surnameET.text.toString(),
                ageET.text.toString(),
                role.toString()
            )
            people.add(person!!)
            listAdapter = ListAdapter(this, people)
            listViewLV.adapter = listAdapter
            listAdapter?.notifyDataSetChanged()
            clear()
        }
        remove()
    }

    private fun remove() {
        listViewLV.onItemClickListener =
        AdapterView.OnItemClickListener { _, _, position, _ ->
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Внимание!")
                .setMessage("Удалить сотрудника?")
                .setCancelable(true)
                .setNegativeButton("Да") { dialog, witch ->
                    val person = listAdapter?.getItem(position)
                    listAdapter?.remove(person)
                }.setPositiveButton("Нет") { dialog, witch ->
                    dialog.cancel()
                }
                .create()
            builder.show()
        }
    }

    private fun clear() {
        nameET.text.clear()
        surnameET.text.clear()
        ageET.text.clear()
    }

    private fun spinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, roleList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerRole.adapter = adapter
        val itemSelectedListener: AdapterView.OnItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    role = p0?.getItemAtPosition(p2) as String
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        spinnerRole.onItemSelectedListener = itemSelectedListener
    }

    private fun init() {
        toolbarTB = findViewById(R.id.toolbarTB)
        setSupportActionBar(toolbarTB)
        nameET = findViewById(R.id.nameET)
        surnameET = findViewById(R.id.surnameET)
        ageET = findViewById(R.id.ageET)
        spinnerRole = findViewById(R.id.spinnerRole)
        saveButtonBTN = findViewById(R.id.saveButtonBTN)
        listViewLV = findViewById(R.id.listViewLV)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.exitItem -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
