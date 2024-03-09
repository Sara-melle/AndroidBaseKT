package com.ajwlhzh.core.mvvm

import androidx.databinding.ViewDataBinding

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/08 16:19:44
 */
interface BaseBinding<VB:ViewDataBinding> {
    fun VB.initBindingView()
    fun VB.initBindingData()
}