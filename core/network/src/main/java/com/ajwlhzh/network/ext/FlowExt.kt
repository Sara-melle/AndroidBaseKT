package com.ajwlhzh.network.ext

import android.util.Log
import com.ajwlhzh.network.NetworkExceptionConstantCode
import com.ajwlhzh.network.RequestResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/05 15:38:21
 */

const val TAG = "Core-NetWork"

inline fun <reified T:Any> Flow<T>.completedIn(
    scope: CoroutineScope,
    crossinline callback:(RequestResult<T>)->Unit
){
    callback(RequestResult.Loading(true))
    catch {throwable->
        Log.e(TAG,throwable.stackTraceToString())
        throwable.toPair().let {
            if(it.first == NetworkExceptionConstantCode.CANCEL)
                callback(RequestResult.Cancel(it.second))
            else
                callback(RequestResult.Failure(it))
        }
    }.onEach {
        callback(RequestResult.Success(it))
    }.catch {throwable->
        Log.e(TAG,throwable.stackTraceToString())
        throwable.toPair().let {
            if(it.first == NetworkExceptionConstantCode.CANCEL)
                callback(RequestResult.Cancel(it.second))
            else
                callback(RequestResult.Failure(it))
        }
    }.onCompletion {
        callback(RequestResult.Completion)
        callback(RequestResult.Loading(false))
    }.launchIn(scope).start()
}

