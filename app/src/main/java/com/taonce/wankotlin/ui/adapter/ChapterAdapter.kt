package com.taonce.wankotlin.ui.adapter

import android.content.Context
import android.widget.TextView
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.recycler.BaseAdapter
import com.taonce.wankotlin.base.recycler.BaseHolder
import com.taonce.wankotlin.bean.ChapterBean

class ChapterAdapter(
    private val mContext: Context,
    private val layoutId: Int,
    private val mData: MutableList<ChapterBean.DataItem>
) : BaseAdapter<ChapterBean.DataItem>(mContext, layoutId, mData) {

    override fun convert(holder: BaseHolder, position: Int) {
        holder.getView<TextView>(R.id.tv_item_chapter)
            .text = mData[position].name
    }
}