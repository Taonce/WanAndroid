package com.taonce.wankotlin.ui.custome

import android.content.Context
import android.util.AttributeSet
import com.tencent.smtt.sdk.WebSettings
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient


/**
 * Author: taoyongxiang
 * Date: 2019/4/11
 * Project: WanKotlin
 * Desc:
 */

class X5WebView : WebView {

    private val client: WebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(p0: WebView?, p1: String?): Boolean {
            p0?.loadUrl(p1)
            return true
        }
    }

    constructor(context: Context) : super(context) {
        setBackgroundColor(85621)
    }

    constructor(context: Context, arg1: AttributeSet) : super(context, arg1) {
        webViewClient = client
        view.isClickable = true
        initWebSettings()
    }

    private fun initWebSettings() {
        val webSetting: WebSettings = this.settings
        webSetting.apply {
            javaScriptEnabled = true
            javaScriptCanOpenWindowsAutomatically = true
            allowFileAccess = true
            layoutAlgorithm = WebSettings.LayoutAlgorithm.NARROW_COLUMNS
            setSupportZoom(true)
            builtInZoomControls = true
            setSupportMultipleWindows(true)
            useWideViewPort = true
            loadWithOverviewMode = true
            setAppCacheEnabled(true)
            domStorageEnabled = true
            setGeolocationEnabled(true)
            setAppCacheMaxSize(Long.MAX_VALUE)
            cacheMode = WebSettings.LOAD_NO_CACHE
            displayZoomControls = false
        }
    }
}

