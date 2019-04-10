package com.taonce.wankotlin.ui.fragment

import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseFragment
import com.taonce.wankotlin.bean.ChapterBean
import com.taonce.wankotlin.contract.IChapterView
import com.taonce.wankotlin.presenter.ChapterPresenter
import com.taonce.wankotlin.ui.adapter.ChapterAdapter
import kotlinx.android.synthetic.main.fragment_chapter.*


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

class WxChapterFragment : BaseFragment(), IChapterView {

    private lateinit var mPresenter: ChapterPresenter
    private val mData: MutableList<ChapterBean.DataItem> = mutableListOf()
    private var mAdapter: ChapterAdapter? = null

    override fun getLayoutId(): Int = R.layout.fragment_chapter

    override fun initData() {
        mPresenter = ChapterPresenter(this)
        mPresenter.getChapter()
    }

    override fun initView() {

    }

    override fun initEvent() {

    }

    override fun showWxChapter(chapterBean: ChapterBean) {
        chapterBean.data?.let {
            mData.addAll(0, it)
            mAdapter = ChapterAdapter(this.context!!, R.layout.item_chapter, mData)
            rv_chapter.adapter = mAdapter
            rv_chapter.layoutManager = LinearLayoutManager(this.context!!)
            mAdapter?.notifyDataSetChanged()
        }
    }
}
