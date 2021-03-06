package com.alisabet.realmdb

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required

open class Model() : RealmObject() {
    @Required
    @PrimaryKey
    var id: String? = null
    @Required
    var name: String? = null
    constructor(id: String, name: String) : this(){
        this.id = id
        this.name = name
    }
}