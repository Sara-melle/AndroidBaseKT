package com.ajwlhzh.network

import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/06 14:59:42
 */
object NetworkApp {

    fun init(context: Context) {
        Logger.addLogAdapter(object :AndroidLogAdapter(){
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }
}