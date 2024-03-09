package com.ajwlhzh.network

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/05 10:56:35
 */
interface BaseResponse

data class StringBaseResponse(val str:String):BaseResponse{
    override fun toString(): String {
        return str
    }
}

data class ErrorResponse(
    val code:Int,
    var mes:String
):BaseResponse