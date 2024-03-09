package com.ajwlhzh.common.base

import com.ajwlhzh.network.BaseResponse

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/06 17:45:55
 */
data class AJBaseResponse<T>(
    val code:Int,
    val msg:String?,
    val data:T?,
):BaseResponse