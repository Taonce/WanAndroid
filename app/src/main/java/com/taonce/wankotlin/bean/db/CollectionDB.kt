package com.taonce.wankotlin.bean.db

import org.litepal.crud.LitePalSupport


class CollectionDB : LitePalSupport() {
    var id: Long = 0
    var articleId: Int = -1
    var url: String = ""
    var tag: Int = 1
    var collectTime: String = ""
    var publishTime: String = ""
    var author: String = ""
    var title: String = ""
    var originId: Int = -1
    override fun toString(): String {
        return "CollectionDB(id=$id, articleId=$articleId, url='$url', tag=$tag, collectTime='$collectTime', publishTime='$publishTime', author='$author', title='$title', originId=$originId)"
    }
}

