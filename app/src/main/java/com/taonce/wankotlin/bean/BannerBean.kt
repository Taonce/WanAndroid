package com.taonce.wankotlin.bean

import com.taonce.wankotlin.base.BaseBean

data class BannerBean(
    val `data`: List<Data>
) : BaseBean() {
    data class Data(
        val desc: String,
        val id: Int,
        val imagePath: String,
        val isVisible: Int,
        val order: Int,
        val title: String,
        val type: Int,
        val url: String
    )
}

