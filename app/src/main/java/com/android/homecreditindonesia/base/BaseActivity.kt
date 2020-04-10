package com.android.homecreditindonesia.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.homecreditindonesia.helper.NetworkChecker
import org.koin.android.ext.android.inject

abstract class BaseActivity : AppCompatActivity(), BaseView {

    protected val networkChecker by inject<NetworkChecker>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView(savedInstanceState)
        initEvent()
        loadingData()
        observeData()
    }

    override fun initView(savedInstanceState: Bundle?) {}

    override fun initEvent() {}

    override fun loadingData(isFromSwipe: Boolean) {}

    override fun observeData() {}

    override fun startLoading() {}

    override fun stopLoading() {}

    override fun onInternetError() {}

    override fun onError(throwable: Throwable?) {}
}