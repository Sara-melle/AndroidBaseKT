package com.ajwlhzh.core.tool.ext

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.reflect.Type

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/05 11:11:06
 */

val gson: Gson = GsonBuilder().apply {
    setLenient()
//    setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
    serializeSpecialFloatingPointValues()
    disableHtmlEscaping()
}.create()

inline fun <reified T> String?.toObject(typeOfT: Type? = null): T? {
    this ?: return null
    return gson.transform {
        it.fromJson(this, typeOfT ?: T::class.java)
    }
}

fun Any.toJson(): String? = gson.toJson(this)

