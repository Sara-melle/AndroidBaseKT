package com.ajwlhzh.service.main

import android.content.Context
import com.ajwlhzh.core.router.Router

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/04 15:15:59
 */
object MainProxy {
    private val provider:MainService by lazy {
        Router.getProvider(MainServicePath.MAIN_SERVICE_PATH_CODE) as MainService
    }

    fun launchLoginActivity(context: Context){
        provider.launchLoginActivity(context)
    }

    fun isLogin() = provider.isLogin()
}