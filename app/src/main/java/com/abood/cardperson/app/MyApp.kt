package com.abood.cardperson.app

import android.app.Application
import com.abood.cardperson.domain.Util

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Util.buildNotesDatabase(this)
    }
}