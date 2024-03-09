package com.ajwlhzh.common.base.ext

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.ajwlhzh.common.base.AJBaseResponse
import com.ajwlhzh.common.base.ResponseCode
import com.ajwlhzh.network.BaseRequest
import com.ajwlhzh.network.Repo
import com.ajwlhzh.network.RequestResult
import com.ajwlhzh.network.ext.repo
import com.ajwlhzh.network.ext.request
import kotlinx.coroutines.CoroutineScope


/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/06 18:09:16
 */

/**
 * 请求接口，以AJBaseResponse数据类型返回
 * @param handleOtherCode 是否自动处理其他Code值，例如401重新登录
 */
inline fun <reified T> CoroutineScope.requestData(
    repo: Repo,
    crossinline callback: (RequestResult<AJBaseResponse<T>>) -> Unit,
) {
    request(repo, callback, getAJBaseResponseParameterizedType<T>())
}

/**
 * 请求接口，以AJBaseResponse数据类型返回
 */
inline fun <reified T1, reified T2> CoroutineScope.requestData(
    repo1: Repo,
    repo2: Repo,
    crossinline callback: (RequestResult<Pair<AJBaseResponse<T1>, AJBaseResponse<T2>>>) -> Unit
) {
    request(
        repo1,
        repo2,
        callback,
        getAJBaseResponseParameterizedType<T1>(),
        getAJBaseResponseParameterizedType<T2>()
    )
}

/**
 * 请求接口，以AJBaseResponse数据类型返回
 */
inline fun <reified T1, reified T2, reified T3> CoroutineScope.requestData(
    repo1: Repo,
    repo2: Repo,
    repo3: Repo,
    crossinline callback: (RequestResult<Triple<AJBaseResponse<T1>, AJBaseResponse<T2>, AJBaseResponse<T3>>>) -> Unit
) {
    request(
        repo1,
        repo2,
        repo3,
        callback,
        getAJBaseResponseParameterizedType<T1>(),
        getAJBaseResponseParameterizedType<T2>(),
        getAJBaseResponseParameterizedType<T3>(),
    )
}

/**
 * 请求接口，以AJBaseResponse数据类型返回
 */
inline fun <reified T> Repo.requestData(
    coroutineScope: CoroutineScope,
    crossinline callback: (RequestResult<AJBaseResponse<T>>) -> Unit,
) {
    request(coroutineScope, callback, getAJBaseResponseParameterizedType<T>())
}

/**
 * 请求成功时的处理，code=200时请求成功。
 */
inline fun <reified V, reified T : AJBaseResponse<V>> RequestResult<T>.onSuccessCode(block: (T) -> Unit) {
    if (this is RequestResult.Success && ResponseCode.SUCCESS.code == value.code) {
        block(value)
    }
}

/**
 * 其他code的处理，code!=200时调用。
 */
inline fun <reified V, reified T : AJBaseResponse<V>> RequestResult<T>.onOtherCode(block: (T) -> Unit) {
    if (this is RequestResult.Success && ResponseCode.SUCCESS.code != value.code) {
        block(value)
    }
}

inline fun <reified T> AppCompatActivity.request(init: BaseRequest.()->Unit, crossinline callback: (RequestResult<AJBaseResponse<T>>) -> Unit){
    repo(init).requestData(lifecycleScope){
        it.onOtherCode (handleOtherCode)
        callback(it)
    }
}

inline fun <reified T> Fragment.request(init: BaseRequest.()->Unit, crossinline callback: (RequestResult<AJBaseResponse<T>>) -> Unit){
    repo(init).requestData(lifecycleScope){
        it.onOtherCode (handleOtherCode)
        callback(it)
    }
}

val handleOtherCode :(AJBaseResponse<*>) -> Unit = {
    when (it.code) {
        ResponseCode.IDENTIFY_INVALID.code ->{
            //todo 重新登录
            
        }

    }
}