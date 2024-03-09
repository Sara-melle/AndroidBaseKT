package com.ajwlhzh.feature.main.provider

import android.content.Context
import android.content.Intent
import com.ajwlhzh.core.router.Router
import com.ajwlhzh.feature.main.login.LoginActivity
import com.ajwlhzh.service.main.MainService
import com.ajwlhzh.service.main.MainServicePath


/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/04 15:24:34
 */
class MainProvider:MainService{

    override fun launchLoginActivity(context: Context) {
        context.startActivity(Intent(context,LoginActivity::class.java))
    }

    override fun isLogin(): Boolean {
        return false
    }

    override fun init() {
        Router.register(MainServicePath.MAIN_SERVICE_PATH_CODE,this)
    }

}