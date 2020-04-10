package com.android.homecreditindonesia.ui

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.Observer
import com.android.homecreditindonesia.R
import com.android.homecreditindonesia.base.BaseActivity
import com.android.homecreditindonesia.base.BaseRecyclerView
import com.android.homecreditindonesia.entity.*
import com.android.homecreditindonesia.helper.getBitmapFromVectorDrawable
import com.android.homecreditindonesia.ui.adapter.ArticleViewHolder
import com.android.homecreditindonesia.ui.adapter.MainAdapter
import com.android.homecreditindonesia.ui.adapter.ProductViewHolder
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.sectionEmptyState
import kotlinx.android.synthetic.main.layout_connection_lost.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(), ProductViewHolder.SetOnClickProduct,
    ArticleViewHolder.SetOnClickArticle {

    private val viewModel by viewModel<MainViewModel>()

    private val resultList = mutableListOf<ContentData>()
    private val adapterMain by lazy {
        MainAdapter(this, this, resultList)
    }

    override fun getLayoutResId(): Int = R.layout.activity_main

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        setupLogo()
        setupRecyclerView()
    }

    override fun initEvent() {
        super.initEvent()
        onClickEmptyState()
    }

    private fun setupLogo() {
        ivLogo.bringToFront()
    }

    private fun setupProgressView(visible: Boolean) {
        when (visible) {
            true -> {
                progressView.visibility = View.GONE
                rvContent.visibility = View.VISIBLE
            }
            else -> {
                rvContent.visibility = View.GONE
                progressView.visibility = View.VISIBLE
            }
        }
    }

    private fun setupCustomTab(url: String) {
        val backButton = this.getBitmapFromVectorDrawable(R.drawable.ic_arrow_back_ios_white_24dp)
        val builder = backButton?.let {
            CustomTabsIntent.Builder()
                .setToolbarColor(Color.RED)
                .setShowTitle(false)
                .setCloseButtonIcon(it)
        }
        val customTab = builder?.build()
        customTab?.launchUrl(this, Uri.parse(url))
    }

    private fun setupRecyclerView() {
        with(rvContent) {
            initRecyclerView(
                adapter = adapterMain,
                layout = BaseRecyclerView.LayoutManager.VERTICAL,
                reverseLayout = true
            )
        }
    }

    private fun addData(data: List<ContentData>) {
        resultList.clear()
        resultList.addAll(data)
        adapterMain.notifyDataSetChanged()
    }

    override fun onClickProduct(items: ContentItemProduct) {
        setupCustomTab(items.link)
    }

    override fun onClickArticle(items: ContentItemArticle) {
        setupCustomTab(items.link)
    }

    private fun onClickEmptyState() {
        btnReload.setOnClickListener {
            sectionEmptyState.visibility = View.GONE
            loadingData()
        }
    }

    override fun loadingData(isFromSwipe: Boolean) {
        super.loadingData(isFromSwipe)
        viewModel.getContent()
    }

    override fun observeData() {
        super.observeData()
        viewModel.contentData.observe(this, Observer {
            parseObserveData(it,
                resultSuccess = { result, _ ->
                    addData(result.data)
                })
        })
    }

    override fun startLoading() {
        setupProgressView(false)
    }

    override fun stopLoading() {
        setupProgressView(true)
    }

    override fun onInternetError() {
        sectionEmptyState.visibility = View.VISIBLE
    }
}
