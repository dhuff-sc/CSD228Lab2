package com.example.csd228lab2

import android.app.Application

class MyChatApp: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
//        container = AppContainerImp()
    }

}