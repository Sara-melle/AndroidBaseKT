package com.ajwlhzh.feature.main

import android.annotation.SuppressLint
import android.content.Context
import com.ajwlhzh.feature.main.provider.MainProvider

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/04 16:23:20
 */
@SuppressLint("StaticFieldLeak")
object MainApp {

    fun init(context: Context){
        MainProvider().init()
    }
}