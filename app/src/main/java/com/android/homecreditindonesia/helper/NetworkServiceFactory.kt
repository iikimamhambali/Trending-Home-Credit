package com.android.homecreditindonesia.helper

import android.content.Context
import com.android.homecreditindonesia.BuildConfig
import com.android.homecreditindonesia.entity.ContentData
import com.android.homecreditindonesia.entity.ContentDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object NetworkServiceFactory {

    fun makeClientService(loggingInterceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .addInterceptor { chain ->
                val ongoing = chain.request().newBuilder()
                ongoing.addHeader("Content-Type", "application/json")
                chain.proceed(ongoing.build())
            }
            .addInterceptor(loggingInterceptor)
            .build()
    }

    fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
            .setLevel(
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            )
    }

    fun makeGson(): Gson {
        return GsonBuilder().registerTypeAdapter(ContentData::class.java, ContentDeserializer())
            .create()
    }

    fun makeCache(context: Context): Cache {
        val cacheSize = 10 * 1024 * 1024 // 10 MB
        return Cache(context.cacheDir, cacheSize.toLong())
    }

    fun makeNetworkChecker(
        context: Context
    ): NetworkChecker {
        return NetworkChecker(context)
    }
}