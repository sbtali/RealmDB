package com.alisabet.realmdb

import io.realm.Realm

class RealmHelper {

    companion object {
        private var realm= Realm.getDefaultInstance()
        fun writeToDB(model: Model){
            realm.executeTransactionAsync(Realm.Transaction {
                realm -> realm.createObject(Model::class.java, model.id).name = model.name
            })
        }
        fun readAllFromDB() : String{
            val models = realm.where(Model::class.java).findAll()
            var string : String = ""
            for (model in models) {
                string+= model.id + " " + model.name + "\n"
            }
            return string
        }
    }
}