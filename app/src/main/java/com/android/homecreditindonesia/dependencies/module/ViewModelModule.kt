package com.android.homecreditindonesia.dependencies.module

import com.android.homecreditindonesia.ui.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {

    single { MainViewModel(get()) }
}