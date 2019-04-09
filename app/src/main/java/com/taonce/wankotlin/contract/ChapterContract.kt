package com.taonce.wankotlin.contract

import com.taonce.wankotlin.bean.ChapterBean


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

interface IChapterView {
    fun showWxChapter(chapterBean: ChapterBean)
}

interface IChapterModel {
    fun getWxChapter(listener: OnGetChapterListener)
    interface OnGetChapterListener {
        fun onListener(chapterBean: ChapterBean?)
    }
}

