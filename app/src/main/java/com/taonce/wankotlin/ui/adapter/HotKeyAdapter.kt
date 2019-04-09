package com.taonce.wankotlin.ui.adapter

import android.content.Context
import android.widget.TextView
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.recycler.BaseAdapter
import com.taonce.wankotlin.base.recycler.BaseHolder
import com.taonce.wankotlin.bean.HotKeyBean
import com.taonce.wankotlin.bean.TreeBean


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

class HotKeyAdapter(
    private val ctx: Context,
    private val resId: Int,
    private val mData: MutableList<HotKeyBean.DataItem>
) : BaseAdapter<HotKeyBean.DataItem>(ctx, resId, mData) {

    override fun convert(
        holder: BaseHolder,
        position: Int
    ) {
        val textView: TextView = holder.getView(R.id.tv_item_hot_key)
        textView.text = mData[position].name
    }
}