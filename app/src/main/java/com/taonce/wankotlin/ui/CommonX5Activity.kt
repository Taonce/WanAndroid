package com.taonce.wankotlin.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.view.View
import com.taonce.utilmodule.formatDate2Day
import com.taonce.utilmodule.showDebug
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.base.BaseMVPActivity
import com.taonce.wankotlin.base.Constant
import com.taonce.wankotlin.contract.ICollectionView
import com.taonce.wankotlin.presenter.CollectionPresenter
import com.taonce.wankotlin.ui.dialog.X5PopWindow
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

class CommonX5Activity : BaseMVPActivity<ICollectionView, CollectionPresenter>(), ICollectionView {

    private val mPresenter: CollectionPresenter by lazy { getPresenter() }
    private var url: String = ""
    private var isCollected: Boolean = false
    private var articleId: Int = -1
    private var isShow: Boolean = true
    private var title: String = ""
    private var collectTime: String = ""
    private var publishTime: String = ""
    private var author = ""

    override fun getLayoutId(): Int = R.layout.activity_x5

    override fun initData() {
    }

    override fun initView() {
        val intent: Intent = intent
        url = intent.getStringExtra(Constant.X5_URL)
        isCollected = intent.getBooleanExtra(Constant.X5_IS_COLLECTED, false)
        articleId = intent.getIntExtra(Constant.X5_ARTICLE_ID, -1)
        isShow = intent.getBooleanExtra(Constant.X5_IS_SHOW, true)
        collectTime = intent.getStringExtra(Constant.X5_COLLECTED_TIME)
        publishTime = intent.getStringExtra(Constant.X5_PUBLISH_TIME)
        author = intent.getStringExtra(Constant.X5_AUTHOR)
        title = intent.getStringExtra(Constant.X5_ARTICLE_TITLE)
        iv_head_more.visibility = if (isShow) View.VISIBLE else View.INVISIBLE
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
        iv_head_more.setOnClickListener {
            showDebug(msg = "more")
            val pop = X5PopWindow(this, isCollected)
            pop.run {
                setCollectUserListener { mPresenter.collectionArticle(articleId) }
                setCollectOfflineListener {
                    mPresenter.collect2DB(
                        articleId,
                        url,
                        System.currentTimeMillis().formatDate2Day(),
                        publishTime,
                        author,
                        title
                    )
                }
                setUnCollectListener { mPresenter.unCollectionArticle(articleId) }
            }
        }
    }

    override fun getPresenter(): CollectionPresenter = CollectionPresenter(this)

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

    override fun collection(baseBean: BaseBean) {
        isCollected = !isCollected
    }

    override fun unCollection(baseBean: BaseBean) {
        isCollected = !isCollected
    }

    override fun collect2DB() {
        isCollected = !isCollected
    }
}

/**
 * jump to this
 * [Context] 上下文
 * [url] web link
 */
fun toCommonX5Activity(
    context: Context,
    url: String,
    isCollected: Boolean = false,
    articleId: Int = -1,
    isShow: Boolean = true,
    collectTime: String = "",
    publishTime: String = "",
    author: String = "",
    title: String = ""
) {
    val intent = Intent(context, CommonX5Activity::class.java)
    intent.putExtra(Constant.X5_URL, url)
    if (isShow) {
        intent.putExtra(Constant.X5_IS_COLLECTED, isCollected)
        intent.putExtra(Constant.X5_ARTICLE_ID, articleId)
        intent.putExtra(Constant.X5_COLLECTED_TIME, collectTime)
        intent.putExtra(Constant.X5_PUBLISH_TIME, publishTime)
        intent.putExtra(Constant.X5_AUTHOR, author)
        intent.putExtra(Constant.X5_ARTICLE_TITLE, title)
    }
    intent.putExtra(Constant.X5_IS_SHOW, isShow)
    context.startActivity(intent)
}
