package com.android.homecreditindonesia.dependencies.module

import com.android.homecreditindonesia.ui.home.ContentViewModel
import org.koin.dsl.module

val viewModelModule = module {

    single { ContentViewModel(get()) }
}