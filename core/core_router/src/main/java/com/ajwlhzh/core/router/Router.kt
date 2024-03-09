package com.ajwlhzh.core.router

import java.util.DuplicateFormatFlagsException

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/01 17:57:45
 */
object Router {
    private val routerServices = mutableMapOf<String,RouterService>()

    fun register(path:String,provider:RouterService){
        if (routerServices.contains(path)){
            throw DuplicatePathException("provider注册失败，path($path)重复注册！")
        }
        routerServices[path]=provider
    }

    fun getProvider(path:String):RouterService{
        return routerServices[path] ?: throw NullPointerException("未找到path($path)对应的provider！")
    }
}