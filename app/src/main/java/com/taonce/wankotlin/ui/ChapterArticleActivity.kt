package com.taonce.wankotlin.ui

import android.content.Intent
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseActivity
import com.taonce.wankotlin.base.BaseMVPActivity
import com.taonce.wankotlin.base.Constant
import com.taonce.wankotlin.bean.WxHistoryBean
import com.taonce.wankotlin.contract.IChapterArticleView
import com.taonce.wankotlin.presenter.ChapterArticlePresenter
import com.taonce.wankotlin.ui.adapter.ChapterArticleAdapter
import kotlinx.android.synthetic.main.activity_chapter_article.*


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

    override fun getLayoutId(): Int = R.layout.activity_chapter_article

    override fun initData() {
        val intent: Intent = intent
        val chapterId: Int = intent.getIntExtra(Constant.CHAPTER_ID, -1)
        mPresenter.getWxHistory(chapterId.toString(), 0)
    }

    override fun initView() {
    }

    override fun initEvent() {
    }

    override fun getPresenter(): ChapterArticlePresenter = ChapterArticlePresenter(this)

    override fun showWxHistory(wxHistoryBean: WxHistoryBean) {
        wxHistoryBean.data.datas?.forEach { data ->
            mHistoryData.add(data)
        }
        if (mHistoryData.isNotEmpty()) {
            mAdapter = ChapterArticleAdapter(this@ChapterArticleActivity, R.layout.item_chapter_article, mHistoryData)
            rv_chapter_article.adapter = mAdapter
            mAdapter?.notifyDataSetChanged()
        }
    }
}