package com.taonce.wankotlin.ui

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.taonce.utilmodule.showDebug
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseMVPActivity
import com.taonce.wankotlin.base.Constant
import com.taonce.wankotlin.bean.WxHistoryBean
import com.taonce.wankotlin.contract.IChapterArticleView
import com.taonce.wankotlin.presenter.ChapterArticlePresenter
import com.taonce.wankotlin.ui.adapter.ChapterArticleAdapter
import kotlinx.android.synthetic.main.activity_chapter_article.*
import kotlinx.android.synthetic.main.item_common_head.*


/**
 * Author: taoyongxiang
 * Date: 2019/4/11
 * Project: WanKotlin
 * Desc:
 */
class ChapterArticleActivity
    : BaseMVPActivity<IChapterArticleView, ChapterArticlePresenter>(),
    IChapterArticleView {

    private val mPresenter: ChapterArticlePresenter by lazy { getPresenter() }
    private var mAdapter: ChapterArticleAdapter? = null
    private var mHistoryData: MutableList<WxHistoryBean.Data.DatasItem> = mutableListOf()
    private lateinit var chapterName: String

    override fun getLayoutId(): Int = R.layout.activity_chapter_article

    override fun initData() {
        val chapterId: Int = intent.getIntExtra(Constant.CHAPTER_ID, -1)
        mPresenter.getWxHistory(chapterId.toString(), 0)
    }

    override fun initView() {
        chapterName = intent.getStringExtra(Constant.CHAPTER_NAME)
        tv_head_title.text = chapterName
        iv_head_more.visibility = View.INVISIBLE
    }

    override fun initEvent() {
        iv_head_back.setOnClickListener { finish() }
    }

    override fun getPresenter(): ChapterArticlePresenter = ChapterArticlePresenter(this)

    override fun showWxHistory(wxHistoryBean: WxHistoryBean) {
        wxHistoryBean.data.datas?.forEach { data ->
            mHistoryData.add(data)
        }
        if (mHistoryData.isNotEmpty()) {
            showDebug(msg = "chapter article is not empty! ")
            mAdapter = ChapterArticleAdapter(this@ChapterArticleActivity, R.layout.item_chapter_article, mHistoryData)
            mAdapter?.setOnItemClickListener { position ->
                toCommonX5Activity(this@ChapterArticleActivity, mHistoryData[position].link)
            }
            rv_chapter_article.adapter = mAdapter
            rv_chapter_article.layoutManager = LinearLayoutManager(this@ChapterArticleActivity)
            mAdapter?.notifyDataSetChanged()
        }
    }
}