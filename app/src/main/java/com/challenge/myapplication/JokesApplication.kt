package com.challenge.myapplication

import android.app.Application
import com.challenge.myapplication.network.DefaultAppContainer
import com.challenge.myapplication.network.JokesAppContainer

class JokesApplication : Application() {
    lateinit var container: JokesAppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
