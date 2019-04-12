package com.taonce.wankotlin.bean

import com.taonce.wankotlin.base.BaseBean

data class  ArticleBean(
    val data: Data
):BaseBean() {
    data class Data(
        val over: Boolean = false,
        val pageCount: Int = 0,
        val total: Int = 0,
        val curPage: Int = 0,
        val offset: Int = 0,
        val size: Int = 0,
        val datas: List<DatasItem>?
    ) {
        data class DatasItem(
            val superChapterName: String = "",
            val publishTime: Long = 0,
            val visible: Int = 0,
            val niceDate: String = "",
            val projectLink: String = "",
            val author: String = "",
            val prefix: String = "",
            val zan: Int = 0,
            val origin: String = "",
            val chapterName: String = "",
            val link: String = "",
            val title: String = "",
            val type: Int = 0,
            val userId: Int = 0,
            val apkLink: String = "",
            val envelopePic: String = "",
            val chapterId: Int = 0,
            val superChapterId: Int = 0,
            val id: Int = 0,
            val fresh: Boolean = false,
            val collect: Boolean = false,
            val courseId: Int = 0,
            val desc: String = ""
        )
    }
}