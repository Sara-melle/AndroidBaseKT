package com.ajwlhzh.crossexpressapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import com.ajwlhzh.common.base.AJBaseResponse
import com.ajwlhzh.common.base.ext.onOtherCode
import com.ajwlhzh.common.base.ext.onSuccessCode
import com.ajwlhzh.network.RequestMode
import com.ajwlhzh.network.ext.repo
import com.ajwlhzh.network.ext.request
import com.ajwlhzh.network.onCancel
import com.ajwlhzh.network.onFailure
import com.ajwlhzh.network.onLoading
import com.ajwlhzh.network.onSuccess
import com.ajwlhzh.service.main.MainProxy

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        findViewById<View>(R.id.btn_login).setOnClickListener {
            MainProxy.launchLoginActivity(this)
        }



    }
}