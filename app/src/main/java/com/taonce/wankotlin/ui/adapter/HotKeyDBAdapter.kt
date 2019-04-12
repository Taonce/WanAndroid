package com.taonce.wankotlin.ui.adapter

import android.content.Context
import android.widget.TextView
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.recycler.BaseAdapter
import com.taonce.wankotlin.base.recycler.BaseHolder
import com.taonce.wankotlin.bean.HotKeyDB


/**
 * Author: taoyongxiang
 * Date: 2019/4/12
 * Project: WanKotlin
 * Desc:
 */

class HotKeyDBAdapter(
    context: Context,
    resId: Int,
    private val mHotKeys: MutableList<HotKeyDB>
) : BaseAdapter<HotKeyDB>(context, resId, mHotKeys) {

    override fun convert(holder: BaseHolder, position: Int) {
        holder.getView<TextView>(R.id.tv_item_hot_key_db).apply {
            text = mHotKeys[position].key
            textSize = 14f
        }
        holder.getView<TextView>(R.id.tv_item_hot_key_db_times).apply {
            textSize = 10f
            text = mHotKeys[position].times.toString()
        }
    }
}

