package com.android.homecreditindonesia.dependencies.module

import com.android.homecreditindonesia.repository.ContentRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { ContentRepository(get(), get()) }
}