package com.android.homecreditindonesia.ui

import android.os.Bundle
import android.util.Log.d
import android.view.View
import androidx.lifecycle.Observer
import com.android.homecreditindonesia.R
import com.android.homecreditindonesia.base.BaseActivity
import com.android.homecreditindonesia.base.BaseRecyclerView
import com.android.homecreditindonesia.entity.*
import com.android.homecreditindonesia.ui.adapter.ArticleViewHolder
import com.android.homecreditindonesia.ui.adapter.MainAdapter
import com.android.homecreditindonesia.ui.adapter.ProductViewHolder
import com.android.homecreditindonesia.ui.web.WebViewActivity
import com.android.homecreditindonesia.ui.web.WebViewActivity.Companion.PRODUCT_TITLE
import com.android.homecreditindonesia.ui.web.WebViewActivity.Companion.URL_ADDRESS
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_item_main.*
import org.jetbrains.anko.startActivity
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
        startActivity<WebViewActivity>(
            URL_ADDRESS to items.link,
            PRODUCT_TITLE to items.productName
        )
    }

    override fun onClickArticle(items: ContentItemArticle) {
        startActivity<WebViewActivity>(
            URL_ADDRESS to items.link,
            PRODUCT_TITLE to items.articleTitle
        )
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
                },
                resultError = {
                    d("LOG", "Error =" + it.toString())
                })
        })
    }

    override fun startLoading() {
        setupProgressView(false)
    }

    override fun stopLoading() {
        setupProgressView(true)
    }
}
