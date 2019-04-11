package com.taonce.wankotlin.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import androidx.core.content.ContextCompat.startActivity
import com.taonce.utilmodule.showDebug
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseActivity
import com.taonce.wankotlin.base.Constant
import com.tencent.smtt.sdk.WebChromeClient
import com.tencent.smtt.sdk.WebView
import com.tencent.smtt.sdk.WebViewClient
import kotlinx.android.synthetic.main.activity_x5.*
import kotlinx.android.synthetic.main.item_common_head.*


/**
 * Author: taoyongxiang
 * Date: 2019/4/11
 * Project: WanKotlin
 * Desc:
 */

class CommonX5Activity : BaseActivity() {
    override fun getLayoutId(): Int = R.layout.activity_x5

    override fun initData() {
    }

    override fun initView() {
        val intent: Intent = intent
        val url: String = intent.getStringExtra(Constant.X5_URL)
        x5_common.loadUrl(url)
        x5_common.webViewClient = object : WebViewClient() {
            override fun onPageStarted(p0: WebView?, p1: String?, p2: Bitmap?) {
                pb_x5.visibility = View.VISIBLE
                super.onPageStarted(p0, p1, p2)
            }

            override fun onPageFinished(p0: WebView?, p1: String?) {
                pb_x5.visibility = View.GONE
                super.onPageFinished(p0, p1)
            }
        }
        x5_common.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(p0: WebView?, p1: String?) {
                tv_head_title.text = p1
                super.onReceivedTitle(p0, p1)
            }

            override fun onProgressChanged(p0: WebView?, p1: Int) {
                pb_x5.progress = p1
                super.onProgressChanged(p0, p1)
            }
        }
    }

    override fun initEvent() {
        iv_head_back.setOnClickListener { finish() }
        iv_head_more.setOnClickListener { showDebug(msg = "more") }
    }

    /**
     * intercept system back key
     */
    override fun onBackPressed() {
        if (x5_common.canGoBack()) {
            x5_common.goBack()
        } else {
            super.onBackPressed()
        }
    }

    /**
     * prevent memory leak
     */
    override fun onDestroy() {
        super.onDestroy()
        x5_common.destroy()
    }
}

/**
 * jump to this
 * [Context] 上下文
 * [url] web link
 */
fun toCommonX5Activity(context: Context, url: String) {
    val intent: Intent = Intent(context, CommonX5Activity::class.java)
    intent.putExtra(Constant.X5_URL, url)
    context.startActivity(intent)
}
