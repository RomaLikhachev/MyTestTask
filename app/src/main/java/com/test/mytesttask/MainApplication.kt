package com.test.mytesttask

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class MainApplication : Application() {

    override fun onCreate() {
        AndroidThreeTen.init(this)

        super.onCreate()
    }
}