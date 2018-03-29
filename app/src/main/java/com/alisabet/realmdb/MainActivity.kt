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

        initViews()

        save.setOnClickListener(View.OnClickListener {
            val model = Model(id_et.text.toString().trim(), name_et.text.toString().trim())
            writeToDB(model)
        })

        read.setOnClickListener(View.OnClickListener {
            data.text = readAllFromDB()
        })

    }
    private fun initViews(){
        id_et = findViewById<EditText>(R.id.id_et)
        name_et = findViewById<EditText>(R.id.name_et)
        data = findViewById<TextView>(R.id.data)
        save = findViewById<Button>(R.id.save)
        read = findViewById<Button>(R.id.read)
        realm = Realm.getDefaultInstance()
    }

    private fun writeToDB(model: Model){
        realm.executeTransactionAsync(Realm.Transaction {
            realm -> realm.createObject(Model::class.java, model.id).name = model.name
        })
    }

    private fun readAllFromDB() : String{
        val models = realm.where(Model::class.java).findAll()
        var string : String = ""
        for (model in models) {
            string+= model.id + " " + model.name
        }
        return string
    }

}
