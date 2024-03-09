package com.ajwlhzh.service.main

import android.content.Context
import com.ajwlhzh.core.router.RouterService

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/04 14:38:20
 */
interface MainService:RouterService {

    fun launchLoginActivity(context: Context)

    fun isLogin():Boolean
}