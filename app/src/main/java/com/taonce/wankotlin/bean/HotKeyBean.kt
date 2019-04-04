package com.taonce.wankotlin.bean

import com.taonce.wankotlin.base.BaseBean

data class HotKeyBean(
    val data: List<DataItem>?
) : BaseBean() {
    data class DataItem(
        val visible: Int = 0,
        val link: String = "",
        val name: String = "",
        val id: Int = 0,
        val order: Int = 0
    )
}