package com.ajwlhzh.network

import androidx.annotation.Keep

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/05 15:04:34
 */
@Keep
sealed class RequestResult<out T> {
    @Keep
    class Loading(val loading: Boolean) : RequestResult<Nothing>()

    @Keep
    class Success<out T>(val value: T) : RequestResult<T>()

    @Keep
    class Failure(val pair: Pair<Int, String>) : RequestResult<Nothing>()

    @Keep
    object Completion : RequestResult<Nothing>()

    @Keep
    class Cancel(val msg:String) : RequestResult<Nothing>()
}

inline fun <reified T> RequestResult<T>.onLoading(block: (Boolean) -> Unit) {
    if (this is RequestResult.Loading){
        block(loading)
    }
}

inline fun <reified T> RequestResult<T>.onSuccess(block: (T) -> Unit) {
    if (this is RequestResult.Success) {
        block(value)
    }
}

inline fun <reified T> RequestResult<T>.onFailure(block:(Pair<Int,String>)->Unit) = if (this is RequestResult.Failure) block(pair) else Unit

inline fun <reified T> RequestResult<T>.onCompletion(block:()->Unit) = if (this is RequestResult.Completion) block() else Unit

inline fun <reified T> RequestResult<T>.onCancel(block:(String)->Unit) = if (this is RequestResult.Cancel) block(msg) else Unit


