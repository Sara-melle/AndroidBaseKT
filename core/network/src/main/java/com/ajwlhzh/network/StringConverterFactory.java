package com.ajwlhzh.network;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * @Description
 * @Author melle
 * @CreateTime 2024/03/06 17:28:15
 */
public class StringConverterFactory extends Converter.Factory{
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(@NonNull Type type, @NonNull Annotation[] annotations, @NonNull Retrofit retrofit) {
        return (Converter<ResponseBody, Object>) ResponseBody::string;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(@NonNull Type type, @NonNull Annotation[] parameterAnnotations, @NonNull Annotation[] methodAnnotations, @NonNull Retrofit retrofit) {
        Gson gson = new Gson();
        return new StringConvert<>(gson,gson.getAdapter(TypeToken.get(type)));
    }
}
