package com.taonce.wankotlin.ui

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.scwang.smartrefresh.layout.footer.ClassicsFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
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

    private var chapterId: Int = 0
    private val mPresenter: ChapterArticlePresenter by lazy { getPresenter() }
    private var mAdapter: ChapterArticleAdapter? = null
    private var mHistoryData: MutableList<WxHistoryBean.Data.DatasItem> = mutableListOf()
    private lateinit var chapterName: String
    private var index = 0
    private var updateIndex: Int = 0

    override fun getLayoutId(): Int = R.layout.activity_chapter_article

    override fun initData() {
        chapterId = intent.getIntExtra(Constant.CHAPTER_ID, -1)
        mPresenter.getWxHistory(chapterId.toString(), index)
    }

    override fun initView() {
        chapterName = intent.getStringExtra(Constant.CHAPTER_NAME)
        tv_head_title.text = chapterName
        iv_head_more.visibility = View.INVISIBLE
        mAdapter = ChapterArticleAdapter(this@ChapterArticleActivity, R.layout.item_chapter_article, mHistoryData)
        rv_chapter_article.adapter = mAdapter
        rv_chapter_article.layoutManager = LinearLayoutManager(this@ChapterArticleActivity)
        srl_chapter_article.setRefreshHeader(ClassicsHeader(this@ChapterArticleActivity))
        srl_chapter_article.setRefreshFooter(ClassicsFooter(this@ChapterArticleActivity))
    }

    override fun initEvent() {
        iv_head_back.setOnClickListener { finish() }
        mAdapter?.setOnItemClickListener { position ->
            toCommonX5Activity(this@ChapterArticleActivity, mHistoryData[position].link)
        }
        srl_chapter_article.setOnRefreshListener { mPresenter.getWxHistory(chapterId.toString(), index) }
        srl_chapter_article.setOnLoadMoreListener { mPresenter.getWxHistory(chapterId.toString(), index) }
    }

    override fun getPresenter(): ChapterArticlePresenter = ChapterArticlePresenter(this)

    override fun showWxHistory(wxHistoryBean: MutableList<WxHistoryBean.Data.DatasItem>) {
        index++
        updateIndex = if (mHistoryData.isEmpty()) 0 else mHistoryData.size
        mHistoryData.addAll(wxHistoryBean)
        mAdapter?.addListData(wxHistoryBean, refreshIndex = updateIndex)
    }

    override fun refreshFinished() {
        srl_chapter_article.finishRefresh()
        srl_chapter_article.finishLoadMore()
    }
}