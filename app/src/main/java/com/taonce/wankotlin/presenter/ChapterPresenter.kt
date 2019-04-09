package com.taonce.wankotlin.presenter

import com.taonce.wankotlin.base.BasePresenter
import com.taonce.wankotlin.bean.ChapterBean
import com.taonce.wankotlin.contract.IChapterModel
import com.taonce.wankotlin.contract.IChapterView
import com.taonce.wankotlin.contract.IMainView
import com.taonce.wankotlin.model.ChapterModel


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

class ChapterPresenter(
    private val mView: IChapterView
) : BasePresenter<IChapterView>(), IChapterModel.OnGetChapterListener {

    private val model: IChapterModel = ChapterModel()

    fun getChapter(): Unit = model.getWxChapter(this)

    override fun onListener(chapterBean: ChapterBean?) {
        chapterBean?.let { mView.showWxChapter(chapterBean) }
    }
}

