package com.ajwlhzh.network

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/04 17:41:22
 */
enum class RequestMode(val value:Int) {
    GET(1),
    MULTIPART(2),
    POST(3),
    DELETE(4),
    PUT(5),
    DELETE_BODY(6),
}