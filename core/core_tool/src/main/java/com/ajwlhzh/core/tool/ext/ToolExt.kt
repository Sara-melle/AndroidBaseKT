package com.ajwlhzh.core.tool.ext

import com.google.gson.reflect.TypeToken

/**
 *
 * @Description
 * @Author melle
 * @CreateTime 2024/03/05 11:21:57
 */

inline fun <T,R> T.transform(block:(T)->R):R?{
    return kotlin.runCatching {
        block(this)
    }.getOrNull()
}