package com.taonce.wankotlin.contract

import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.base.IBaseView


interface ICollectionView : IBaseView {

    fun unCollection(baseBean: BaseBean)

    fun collection(baseBean: BaseBean)

    fun collect2DB()
}

interface ICollectionModel {

    fun collection(id: Int, listener: OnCollectionListener)

    fun unCollection(id: Int, listener: OnUnCollectionListener)

    fun collect2DB(
        id: Int,
        url: String,
        collectTime: String,
        publishTime: String,
        author: String,
        title: String,
        listener: OnCollect2DBListener
    )

    interface OnCollectionListener {
        fun onCollectionListener(baseBean: BaseBean?)
    }

    interface OnUnCollectionListener {
        fun onUnCollectionListener(baseBean: BaseBean?)
    }

    interface OnCollect2DBListener {
        fun onCollect2DBListener()
        fun onCollect2DBFailedListener()
    }
}