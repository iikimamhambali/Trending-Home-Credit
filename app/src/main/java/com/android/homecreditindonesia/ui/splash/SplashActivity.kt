package com.android.homecreditindonesia.ui.splash

import android.os.Bundle
import android.os.Handler
import com.android.homecreditindonesia.R
import com.android.homecreditindonesia.base.BaseActivity
import com.android.homecreditindonesia.ui.MainActivity
import org.jetbrains.anko.startActivity

class SplashActivity : BaseActivity() {

    companion object {
        private const val DELAY_SPLASH_SCREEN: Long = 2000
    }

    override fun getLayoutResId(): Int = R.layout.activity_splash

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)

        Handler().postDelayed({
            startActivity<MainActivity>()
            this.finish()
        }, DELAY_SPLASH_SCREEN)
    }
}
