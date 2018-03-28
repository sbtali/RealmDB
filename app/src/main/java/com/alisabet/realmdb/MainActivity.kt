package com.alisabet.realmdb

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import io.realm.Realm
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val realm = Realm.getDefaultInstance()

        realm.executeTransactionAsync(Realm.Transaction {
            realm -> realm.createObject(Task::class.java, UUID.randomUUID().toString()).name = "sabet"
        })

        button.setOnClickListener(View.OnClickListener {
            val userSchedules = realm.where(Task::class.java).findAll()
            for (userSchedule in userSchedules) {
                Toast.makeText(this@MainActivity, userSchedule.name, Toast.LENGTH_SHORT).show()
            }
        })

    }

}
