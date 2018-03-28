package com.alisabet.realmdb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val id_et = findViewById<EditText>(R.id.id_et)
        val name_et = findViewById<EditText>(R.id.name_et)
        val data = findViewById<TextView>(R.id.data)
        val save = findViewById<Button>(R.id.save)
        val read = findViewById<Button>(R.id.read)
        val realm = Realm.getDefaultInstance()

        save.setOnClickListener(View.OnClickListener {
            realm.executeTransactionAsync(Realm.Transaction {
                realm -> realm.createObject(Task::class.java, id_et.text.toString().trim()).name = name_et.text.toString().trim()
            })
        })

        read.setOnClickListener(View.OnClickListener {
            val tasks = realm.where(Task::class.java).findAll()
            var string : String = ""
            for (task in tasks) {
                string = string + task.id + " " + task.name + "\t"
            }
            data.text = string
        })

    }

}
