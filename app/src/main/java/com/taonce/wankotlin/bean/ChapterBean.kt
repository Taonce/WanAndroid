package com.taonce.wankotlin.bean

import com.taonce.wankotlin.base.BaseBean


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

data class ChapterBean(
    val data: List<DataItem>?
) : BaseBean() {
    data class DataItem(
        val visible: Int = 0,
        val name: String = "",
        val userControlSetTop: Boolean = false,
        val id: Int = 0,
        val courseId: Int = 0,
        val parentChapterId: Int = 0,
        val order: Int = 0
    )
}