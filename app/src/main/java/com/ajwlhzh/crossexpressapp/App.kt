package com.ajwlhzh.crossexpressapp

import android.app.Application
import com.ajwlhzh.feature.main.MainApp
import com.ajwlhzh.network.NetworkApp

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/04 16:38:05
 */
class App:Application() {
    override fun onCreate() {
        super.onCreate()
        MainApp.init(this)
        NetworkApp.init(this)
    }
}