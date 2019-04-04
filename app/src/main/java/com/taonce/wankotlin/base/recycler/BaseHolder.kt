package com.taonce.wankotlin.base.recycler

import android.view.View
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView


/**
 * Author: taoyongxiang
 * Date: 2019/4/4
 * Project: WanKotlin
 * Desc: [RecyclerView.ViewHolder] 基类
 */

class BaseHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val mViews = SparseArrayCompat<View>()

    /**
     * 通过resId获取view，通过as转换符将`V`转换成具体的`View`
     * 具体请看`MainAdapter`的实现
     */
    fun <V : View> getView(id: Int): V {
        var view = mViews[id]
        if (view == null) {
            view = itemView.findViewById(id)
            mViews.put(id, view)
        }
        return view as V
    }

}