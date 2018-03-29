package com.alisabet.realmdb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import io.realm.Realm

class MainActivity : AppCompatActivity() {

    private lateinit var id_et: EditText
    private lateinit var name_et: EditText
    private lateinit var data: TextView
    private lateinit var save: Button
    private lateinit var read: Button
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id_et = findViewById<EditText>(R.id.id_et)
        name_et = findViewById<EditText>(R.id.name_et)
        data = findViewById<TextView>(R.id.data)
        save = findViewById<Button>(R.id.save)
        read = findViewById<Button>(R.id.read)
        realm = Realm.getDefaultInstance()

        save.setOnClickListener(View.OnClickListener {
            val model = Model()
            model.id = id_et.text.toString().trim()
            model.name = name_et.text.toString().trim()
            writeToDB(model)
        })

        read.setOnClickListener(View.OnClickListener {
            val tasks = realm.where(Model::class.java).findAll()
            var string : String = ""
            for (task in tasks) {
                string = string + task.id + " " + task.name + "\t"
            }
            data.text = string
        })

    }

    fun writeToDB(model: Model){
        realm.executeTransactionAsync(Realm.Transaction {
            realm -> realm.createObject(Model::class.java, model.id).name = model.name
        })
    }

}
