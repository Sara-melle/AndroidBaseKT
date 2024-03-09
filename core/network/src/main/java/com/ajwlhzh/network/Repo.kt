package com.ajwlhzh.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.lang.RuntimeException
import java.lang.UnsupportedOperationException

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/05 10:27:08
 */
class Repo {
    var req = BaseRequest()
    private val apiService by lazy {
        RetrofitClient.providerRetrofit().create(API::class.java)
    }

    suspend fun execute() = withContext(Dispatchers.IO) {
        val request = req
        val params = request.reflectParameters()
        val api = request.api ?: throw RuntimeException("repo 没有传入请求地址！")
        when (request.requestMode) {
            RequestMode.GET -> {
                apiService.get(
                    api,
                    params
                )
            }

            RequestMode.MULTIPART -> {
                val file =
                    request.file ?: throw RuntimeException("repo 请求multipart时，文件不能为空！")
                val requestBody = file.asRequestBody(request.contentType)
                apiService.uploadFile(
                    api,
                    MultipartBody.Part.createFormData(
                        "uploadFile",
                        file.name,
                        requestBody
                    )
                )
            }

            RequestMode.POST -> {
                apiService.post(
                    api,
                    params
                )
            }

            RequestMode.DELETE -> {
                apiService.delete(
                    api,
                    params
                )
            }

            RequestMode.PUT -> {
                apiService.put(
                    api,
                    params
                )
            }

            RequestMode.DELETE_BODY -> {
                apiService.deleteBody(
                    api,
                    params
                )
            }
        }
    }

}