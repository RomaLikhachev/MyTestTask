package com.test.mytesttask

import com.jakewharton.threetenabp.AndroidThreeTen
import com.test.mytesttask.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class MainApplication : DaggerApplication() {

    override fun onCreate() {
        AndroidThreeTen.init(this)

        super.onCreate()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}