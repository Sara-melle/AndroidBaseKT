package com.ajwlhzh.network.ext

import com.ajwlhzh.core.tool.ext.toObject
import com.ajwlhzh.network.ErrorResponse
import com.ajwlhzh.network.NetworkExceptionConstantCode
import com.ajwlhzh.network.ResponseException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/05 15:47:05
 */
suspend fun Throwable.toPair(): Pair<Int, String> {
    return when (val e = this) {
        is HttpException -> {
            kotlin.runCatching {
                val errorJson = withContext(Dispatchers.IO) {
                    e.response()?.errorBody()?.string()
                }
                val errorRsp: ErrorResponse? = withContext(Dispatchers.Default) {
                    errorJson.toObject()
                }
                Pair(
                    errorRsp?.code ?: NetworkExceptionConstantCode.UN_KNOWN,
                    errorRsp?.mes ?: "HttpException:no mes"
                )
            }.getOrElse {
                Pair(NetworkExceptionConstantCode.UN_KNOWN, "HttpException : ${this.message}")
            }
        }

        is CancellationException -> {
            Pair(NetworkExceptionConstantCode.CANCEL, "CancellationException : ${this.message}")
        }

        else -> {
            Pair(NetworkExceptionConstantCode.UN_KNOWN, "other error : ${this.message}")
        }
    }
}
