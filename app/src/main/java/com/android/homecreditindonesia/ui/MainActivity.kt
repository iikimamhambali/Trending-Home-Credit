package com.android.homecreditindonesia.ui

import androidx.lifecycle.Observer
import com.android.homecreditindonesia.R
import com.android.homecreditindonesia.base.BaseActivity
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel by viewModel<MainViewModel>()

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun loadingData(isFromSwipe: Boolean) {
        super.loadingData(isFromSwipe)
        viewModel.getContent()
    }

    override fun observeData() {
        super.observeData()
        viewModel.contentData.observe(this, Observer {
            parseObserveData(it, resultLoading = {},
                resultSuccess = { result, _ ->
                    toast("Success")
                })
        })
    }

}
