package com.ajwlhzh.network

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/05 15:45:57
 */
class ResponseException(val code:Int, override val message: String?):Exception()