package com.taonce.wankotlin.bean

import com.taonce.wankotlin.base.BaseBean

data class TreeBean(val data: MutableList<DataItem>?) : BaseBean() {
    data class DataItem(
        val visible: Int = 0,
        val children: MutableList<ChildrenItem>?,
        val name: String = "",
        val userControlSetTop: Boolean = false,
        val id: Int = 0,
        val courseId: Int = 0,
        val parentChapterId: Int = 0,
        val order: Int = 0
    ) {
        data class ChildrenItem(
            val visible: Int = 0,
            val name: String = "",
            val userControlSetTop: Boolean = false,
            val id: Int = 0,
            val courseId: Int = 0,
            val parentChapterId: Int = 0,
            val order: Int = 0
        )

    }
}