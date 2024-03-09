package com.ajwlhzh.common.base.ext

import com.ajwlhzh.common.base.AJBaseResponse
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/07 17:05:06
 */

inline fun <reified T> getAJBaseResponseParameterizedType(): Type? = object : TypeToken<AJBaseResponse<T>?>() {}.type