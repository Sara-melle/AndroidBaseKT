package com.ajwlhzh.network.ext

import com.ajwlhzh.core.tool.ext.toObject
import com.ajwlhzh.network.BaseRequest
import com.ajwlhzh.network.BaseResponse
import com.ajwlhzh.network.Repo
import com.ajwlhzh.network.RequestResult
import com.ajwlhzh.network.StringBaseResponse
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.reflect.Type

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/05 10:50:35
 */

inline fun repo(init: BaseRequest.() -> Unit) = Repo().apply {
    req = BaseRequest().also { it.init() }
}

inline fun <reified T : BaseResponse> Repo.toFlow(typeOfT: Type? = null) = flow {
    val jsonResult = execute()
    if (T::class.java == StringBaseResponse::class.java) {
        emit(StringBaseResponse(jsonResult) as T)
    } else {
        emit(
            jsonResult.toObject<T>(typeOfT) ?: throw JsonSyntaxException(
                """
            json解析异常，请求结果无法转化为数据类！!
            json：-->$jsonResult
            数据类：->${T::class.simpleName}
        """.trimIndent()
            )
        )
    }
}.flowOn(Dispatchers.IO)

/**
 * 指定协程中请求
 */
inline fun <reified T : BaseResponse> Repo.request(
    coroutineScope: CoroutineScope,
    crossinline callback: (RequestResult<T>) -> Unit,
    typeOfT: Type? = null,
) {
    coroutineScope.request(this, callback,typeOfT)
}

/**
 * 直接请求
 */
suspend inline fun <reified T : BaseResponse> Repo.request(typeOfT: Type? = null): T {
    return execute().let {
        if (T::class.java == StringBaseResponse::class.java) {
            StringBaseResponse(it) as T
        } else {
            it.toObject<T>(typeOfT) ?: throw JsonSyntaxException(
                """
            json解析异常，请求结果无法转化为数据类！!
            json：-->$it
            数据类：->${T::class.simpleName}
        """.trimIndent()
            )
        }
    }
}

