package com.android.homecreditindonesia.dependencies.module

import com.android.homecreditindonesia.app.AppExecutors
import org.koin.dsl.module

val appModule = module {

    single { AppExecutors() }

}