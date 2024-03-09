package com.ajwlhzh.core.mvvm.ext

import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.reflect.ParameterizedType

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/08 16:26:13
 */
fun <VM:ViewModel> ComponentActivity.createViewModel(position:Int) :VM? = try {
    (javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments
        .filterIsInstance<Class<*>>()[position]
        .let {
            it as? Class<VM>
        }?.let {
            ViewModelProvider(this)[it]
        }
}catch (e:Exception){
    e.printStackTrace()
    null
}