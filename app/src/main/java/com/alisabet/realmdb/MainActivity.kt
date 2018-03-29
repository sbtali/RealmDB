package com.alisabet.realmdb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var id_et: EditText
    private lateinit var name_et: EditText
    private lateinit var data: TextView
    private lateinit var save: Button
    private lateinit var read: Button
    private lateinit var readById: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        save.setOnClickListener({
            val model = Model(id_et.text.toString().trim(), name_et.text.toString().trim())
            RealmHelper.writeToDB(model)
        })
        read.setOnClickListener({
            data.text = RealmHelper.findAllFromDB()
        })
        readById.setOnClickListener({
            Toast.makeText(this, RealmHelper.findByIdFromDB("1001"), Toast.LENGTH_SHORT).show()
        })
    }

    private fun initViews(){
        id_et = findViewById<EditText>(R.id.id_et)
        name_et = findViewById<EditText>(R.id.name_et)
        data = findViewById<TextView>(R.id.data)
        save = findViewById<Button>(R.id.save)
        read = findViewById<Button>(R.id.read)
        readById = findViewById<Button>(R.id.readById)
    }
}
