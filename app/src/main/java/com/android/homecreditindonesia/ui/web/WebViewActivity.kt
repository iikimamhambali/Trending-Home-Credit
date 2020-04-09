package com.android.homecreditindonesia.ui.web

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.View
import android.webkit.*
import com.android.homecreditindonesia.R
import com.android.homecreditindonesia.base.BaseActivity
import kotlinx.android.synthetic.main.activity_webview.*
import kotlinx.android.synthetic.main.layout_toolbar_default.*

class WebViewActivity : BaseActivity() {

    private var urlAddress = ""
    private var productTitle = ""

    companion object {
        const val URL_ADDRESS = "URL_ADDRESS"
        const val PRODUCT_TITLE = "PRODUCT_TITLE"
        private const val PROGRESS_DONE = 100
    }

    override fun getLayoutResId(): Int = R.layout.activity_webview

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        getIntentData()
        setupToolbar()
        setupWebViewSetting()
    }

    override fun initEvent() {
        super.initEvent()
        setOnClickToolbar()
    }

    private fun getIntentData() {
        intent?.extras?.let {
            urlAddress = it.getString(URL_ADDRESS, "")
            productTitle = it.getString(PRODUCT_TITLE, "")
        }
    }

    private fun setupToolbar() {
        tvToolbarInfoTitle.text = productTitle
        ivToolbarBack.visibility = View.VISIBLE
    }

    private fun setOnClickToolbar() {
        ivToolbarBack.setOnClickListener { finish() }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebViewSetting() {
        webViewProduct.settings.apply {
            javaScriptEnabled = true
            setSupportZoom(true)
            builtInZoomControls = true
            displayZoomControls = true
            loadWithOverviewMode = true
            useWideViewPort = true
            cacheMode = WebSettings.LOAD_NO_CACHE
            domStorageEnabled = true
            defaultTextEncodingName = "UTF-8"
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        }
    }

    private fun openUrl(url: String) {
        webViewProduct.webViewClient = ProductWebViewClient { code, desc ->
            //            eventBus.post(ErrorEvent(clazz<ProductActivity>().simpleName,
//                desc, code))
//            setProgressVisibility(false)
        }
        webViewProduct.webChromeClient = ProductChromeClient(
            {
                //                setProgressVisibility(true)
//                pbTnCHorizontal.progress = it
            },
            {
                //                setProgressVisibility(false)
            }
        )
        webViewProduct.loadUrl(url)
    }

    override fun loadingData(isFromSwipe: Boolean) {
        super.loadingData(isFromSwipe)
        openUrl(urlAddress)
    }

    class ProductWebViewClient(private val funcOnError: (String, String) -> Unit) :
        WebViewClient() {

        @Suppress("OverridingDeprecatedMember")
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            view?.loadUrl(url)
            return true
        }

        @Suppress("OverridingDeprecatedMember", "DEPRECATION")
        override fun onReceivedError(
            view: WebView?,
            errorCode: Int,
            description: String?,
            failingUrl: String?
        ) {
            super.onReceivedError(view, errorCode, description, failingUrl)
            funcOnError("$errorCode", description ?: "NA")
        }

        @Suppress("DEPRECATION")
        @TargetApi(Build.VERSION_CODES.M)
        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            this.onReceivedError(
                view, error?.errorCode ?: 0, error?.description.toString(),
                request?.url.toString()
            )
            funcOnError("${error?.errorCode}", error?.description.toString())
        }
    }

    class ProductChromeClient(
        private val funcOnLoad: (progress: Int) -> Unit,
        private val funcOnFinished: () -> Unit
    ) : WebChromeClient() {

        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            when (newProgress) {
                PROGRESS_DONE -> funcOnFinished()
                else -> funcOnLoad(newProgress)
            }
        }
    }
}
