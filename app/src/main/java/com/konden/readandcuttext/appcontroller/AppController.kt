package com.konden.readandcuttext.appcontroller

import android.app.Application
import com.yariksoffice.lingver.Lingver

class AppController : Application() {
    override fun onCreate() {
        super.onCreate()
        Lingver.init(this)
    }
}