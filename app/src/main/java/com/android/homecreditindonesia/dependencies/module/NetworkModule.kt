package com.android.homecreditindonesia.dependencies.module

import com.android.homecreditindonesia.R
import com.android.homecreditindonesia.helper.LiveDataCallAdapterFactory
import com.android.homecreditindonesia.helper.NetworkServiceFactory
import com.android.homecreditindonesia.network.ContentService
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { NetworkServiceFactory.makeClientService(get(), get()) }

    single { NetworkServiceFactory.makeLoggingInterceptor() }

    single { NetworkServiceFactory.makeCache(get()) }

    single { NetworkServiceFactory.makeGson() }

    single { NetworkServiceFactory.makeNetworkChecker(get()) }

    single(named("RetrofitMovie")) {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(get<OkHttpClient>(named("general_client")))
            .baseUrl(androidContext().getString(R.string.server_url) + "/")
            .build()
    }
    single { get<Retrofit>(named("RetrofitMovie")).create(ContentService::class.java) }
}