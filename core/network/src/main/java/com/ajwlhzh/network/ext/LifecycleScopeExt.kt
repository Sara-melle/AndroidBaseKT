package com.ajwlhzh.network.ext

import com.ajwlhzh.core.tool.ext.toJson
import com.ajwlhzh.core.tool.ext.toObject
import com.ajwlhzh.network.BaseResponse
import com.ajwlhzh.network.Repo
import com.ajwlhzh.network.RequestResult
import com.ajwlhzh.network.StringBaseResponse
import com.ajwlhzh.network.onSuccess
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.combine
import java.lang.reflect.Type

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/05 18:06:15
 */

inline fun <reified T : BaseResponse> CoroutineScope.request(
    repo: Repo,
    crossinline callback: (RequestResult<T>) -> Unit,
    typeOfT: Type? = null,
) {
    repo.toFlow<T>(typeOfT).completedIn(this, callback)
}

inline fun <reified T1 : BaseResponse, reified T2 : BaseResponse> CoroutineScope.request(
    repo1: Repo,
    repo2: Repo,
    crossinline callback: (RequestResult<Pair<T1, T2>>) -> Unit,
    typeOfT1: Type? = null,
    typeOfT2: Type? = null
) {
    combine(repo1.toFlow<T1>(typeOfT1), repo2.toFlow<T2>(typeOfT2)) { r1, r2 ->
        Pair(r1, r2)
    }.completedIn(this, callback)
}

inline fun <reified T1 : BaseResponse, reified T2 : BaseResponse, reified T3 : BaseResponse> CoroutineScope.request(
    repo1: Repo,
    repo2: Repo,
    repo3: Repo,
    crossinline callback: (RequestResult<Triple<T1, T2,T3>>) -> Unit,
    typeOfT1: Type? = null,
    typeOfT2: Type? = null,
    typeOfT3: Type? = null
) {
    combine(repo1.toFlow<T1>(typeOfT1), repo2.toFlow<T2>(typeOfT2),repo3.toFlow<T3>(typeOfT3)) { r1, r2,r3 ->
        Triple(r1, r2,r3)
    }.completedIn(this, callback)

}

inline fun CoroutineScope.request(
    crossinline callback:(RequestResult<Array<BaseResponse>>)->Unit,
    vararg repos:Repo
){
    repos
        .map { it.toFlow<BaseResponse>() }
        .let {requests->
            combine(*requests.toTypedArray()){it}
        }.completedIn(this,callback)
}

