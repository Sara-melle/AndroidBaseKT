package com.ajwlhzh.feature.main.login

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.ajwlhzh.common.base.AJBaseActivity
import com.ajwlhzh.common.base.AJBaseResponse
import com.ajwlhzh.common.base.ResponseCode
import com.ajwlhzh.common.base.ext.onOtherCode
import com.ajwlhzh.common.base.ext.onSuccessCode
import com.ajwlhzh.common.base.ext.request
import com.ajwlhzh.common.base.ext.requestData
import com.ajwlhzh.common.base.utils.RSAUtils
import com.ajwlhzh.data.main.models.UserModel
import com.ajwlhzh.feature.main.R
import com.ajwlhzh.feature.main.databinding.ActivityLoginBinding
import com.ajwlhzh.network.BaseRequest
import com.ajwlhzh.network.RequestMode
import com.ajwlhzh.network.RequestResult
import com.ajwlhzh.network.ext.repo
import com.ajwlhzh.network.onCancel
import com.ajwlhzh.network.onCompletion
import com.ajwlhzh.network.onFailure
import com.ajwlhzh.network.onLoading
import com.ajwlhzh.network.onSuccess
import com.ajwlhzh.service.main.MainProxy

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/04 16:05:10
 */
class LoginActivity: AJBaseActivity<ActivityLoginBinding>() {

    val vm:ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        findViewById<View>(R.id.btn_login).setOnClickListener {
            val pass = findViewById<EditText>(R.id.et_pass).text.toString()


            request<UserModel>(
                {
                    api { "https://systemapi.bailidaming.com/api/user/user/login" }
                    requestMode { RequestMode.POST }
                    params { "username" to findViewById<EditText>(R.id.et_user).text.toString() }
                    params { "password" to RSAUtils.encode(pass) }
                    params { "source" to "I-1" }
                }
            ){
                it.onSuccess {
                    Log.e("melle", "onSuccess:$it")
                }
                it.onSuccessCode {
                    Toast.makeText(this,it.data?.token, Toast.LENGTH_SHORT).show()
                }
                it.onOtherCode {
                    Toast.makeText(this,"${it.code}:${it.msg}", Toast.LENGTH_SHORT).show()
                }
                it.onFailure {
                    Log.e("melle", "onFailure:$it")
                }
                it.onCancel {
                    Log.e("melle","onCancel:$it")
                }
                it.onLoading{
                    Log.e("melle-onLoading","onLoading:$it")
                }
                it.onCompletion {
                    Log.e("melle","onCompletion")
                }
            }
        }
    }

    override fun ActivityLoginBinding.initBindingData() {
    }

    override fun ActivityLoginBinding.initBindingView() {
    }

}

