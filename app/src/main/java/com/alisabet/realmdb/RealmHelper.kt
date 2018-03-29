package com.alisabet.realmdb

import android.util.Log
import io.realm.Realm

class RealmHelper {

    companion object {
        private var realm = Realm.getDefaultInstance()
        fun writeToDB(model: Model){
            realm.executeTransactionAsync({
                realm -> realm.createObject(Model::class.java, model.id).name = model.name
            }, {
                Log.i("db", "saved")
            }, {
                Log.i("db", "not saved because of : " + it.message)
            })
        }
        fun findAllFromDB() : String{
            val models = realm.where(Model::class.java).findAll()
            var string : String = ""
            for (model in models) {
                string+= model.id + " " + model.name + "\n"
            }
            return string
        }
        fun findByIdFromDB(id: String) : String? {
            val model = realm.where(Model::class.java).equalTo("id", id).findFirst()
            return model!!.name
        }
    }
}