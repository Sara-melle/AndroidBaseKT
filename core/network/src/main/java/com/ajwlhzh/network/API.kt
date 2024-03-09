package com.ajwlhzh.network

import okhttp3.MultipartBody
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.QueryMap
import retrofit2.http.Url

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/05 10:19:53
 */
interface API {
    @GET
    suspend fun get(
        @Url url:String,
        @QueryMap body:MutableMap<String,Any?>,
    ):String

    @POST
    suspend fun post(
        @Url url:String,
        @Body body:MutableMap<String,Any?>,
    ):String

    @Multipart
    @POST
    suspend fun uploadFile(
        @Url url:String,
        @Part file:MultipartBody.Part,
    ):String

    @PUT
    suspend fun put(
        @Url url:String,
        @Body body:MutableMap<String,Any?>,
    ):String

    @DELETE
    suspend fun delete(
        @Url url:String,
        @QueryMap body:MutableMap<String,Any?>,
    ):String

    @HTTP(method = "DELETE", hasBody = true)
    suspend fun deleteBody(
        @Url url:String,
        @Body body:MutableMap<String,Any?>,
    ):String
}