package com.taonce.wankotlin.bean

import com.taonce.wankotlin.base.BaseBean

data class CollectionListBean(
    val data: Data
) : BaseBean() {
    data class Data(
        val over: Boolean = false,
        val pageCount: Int = 0,
        val total: Int = 0,
        val curPage: Int = 0,
        val offset: Int = 0,
        val size: Int = 0,
        val datas: MutableList<DatasItem>?
    ) {
        data class DatasItem(
            val publishTime: Long = 0,
            val visible: Int = 0,
            val niceDate: String = "",
            val author: String = "",
            val zan: Int = 0,
            val origin: String = "",
            val chapterName: String = "",
            val link: String = "",
            val title: String = "",
            val userId: Int = 0,
            val originId: Int = 0,
            val envelopePic: String = "",
            val chapterId: Int = 0,
            val id: Int = 0,
            val courseId: Int = 0,
            val desc: String = ""
        )
    }
}