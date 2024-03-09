package com.ajwlhzh.core.mvvm.ext

import android.view.LayoutInflater
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/08 15:58:32
 */
fun <VB : ViewBinding> Any.getViewBinding(inflater: LayoutInflater): VB? = try {
    (javaClass.genericSuperclass as ParameterizedType)
        .actualTypeArguments.filterIsInstance<Class<*>>()[0]
        .getDeclaredMethod("inflate", LayoutInflater::class.java)
        .invoke(null, inflater) as? VB
} catch (e: Exception) {
    e.printStackTrace()
    null
}