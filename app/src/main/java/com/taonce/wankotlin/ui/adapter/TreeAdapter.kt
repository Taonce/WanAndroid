package com.taonce.wankotlin.ui.adapter

import android.content.Context
import android.widget.TextView
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.recycler.BaseAdapter
import com.taonce.wankotlin.base.recycler.BaseHolder
import com.taonce.wankotlin.bean.TreeBean
import kotlinx.android.synthetic.main.item_tree.view.*
import java.time.chrono.MinguoDate


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

class TreeAdapter(
    private val ctx: Context,
    private val resId: Int,
    private val mData: MutableList<TreeBean.DataItem>
) : BaseAdapter<TreeBean.DataItem>(ctx, resId, mData) {

    override fun convert(
        holder: BaseHolder,
        position: Int
    ) {
        val textView: TextView = holder.getView(R.id.tv_item_tree)
        textView.text = mData[position].name
    }
}

