package com.ajwlhzh.network

import com.ajwlhzh.core.tool.ext.debug
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/04 18:13:02
 */
object RetrofitClient {
    private const val BASE_URL = "https://systemapi.bailidaming.com/"

    private fun provideOkHttpClient():OkHttpClient {
        val builder = OkHttpClient
            .Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
        debug {
            builder.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        }
        return builder.build()
    }

    fun providerRetrofit(): Retrofit = Retrofit
        .Builder()
        .client(provideOkHttpClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(StringConverterFactory())
//        .addConverterFactory(GsonConverterFactory.create())
        .build()

}