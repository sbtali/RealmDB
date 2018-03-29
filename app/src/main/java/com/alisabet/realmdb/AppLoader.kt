package com.alisabet.realmdb

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class AppLoader : Application() {
    override fun onCreate() {
        super.onCreate()
        initRealm()
    }
    private fun initRealm(){
        Realm.init(this)
        val realmConfig = RealmConfiguration.Builder()
                .name("db.realm")
                .schemaVersion(0)
                .build()
        Realm.setDefaultConfiguration(realmConfig)
    }
}