package com.ajwlhzh.network

import okhttp3.MediaType
import java.io.File

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/04 17:52:26
 */
class BaseRequest {
    var api: String? = null
    var requestMode = RequestMode.GET
    var file: File? = null
    private var params = mutableMapOf<String, Any?>()
    var contentType: MediaType? = null

    fun api(init: () -> String) {
        api = init()
    }

    fun requestMode(init: () -> RequestMode) {
        requestMode = init()
    }

    fun file(init: () -> File) {
        file = init()
    }

    fun params(init: () -> Pair<String,Any?>) {
        val p = init()
        params[p.first] = p.second
    }

    fun contentType(init: () -> MediaType) {
        contentType = init()
    }

    fun reflectParameters() = params

    override fun toString(): String {
        return "api:$api \n params :$params"
    }
}