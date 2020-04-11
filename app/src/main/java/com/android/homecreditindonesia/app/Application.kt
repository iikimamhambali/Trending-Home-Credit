package com.android.homecreditindonesia.app

import com.android.homecreditindonesia.base.BaseApplication
import com.android.homecreditindonesia.dependencies.libraries
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : BaseApplication() {

    override fun initApplication() {
        startKoin {
            modules(libraries)
            androidContext(this@Application)
        }
    }
}