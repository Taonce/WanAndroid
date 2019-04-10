package com.taonce.wankotlin.ui.fragment

import androidx.fragment.app.Fragment
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseFragment
import com.taonce.wankotlin.bean.ChapterBean
import com.taonce.wankotlin.contract.IChapterView
import com.taonce.wankotlin.presenter.ChapterPresenter


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

class WxChapterFragment : BaseFragment(), IChapterView {

    private lateinit var mPresenter: ChapterPresenter
    private val mData: MutableList<ChapterBean.DataItem> = mutableListOf()

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
        }
    }
}
