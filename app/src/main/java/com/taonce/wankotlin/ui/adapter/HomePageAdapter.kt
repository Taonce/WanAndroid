package com.taonce.wankotlin.ui.adapter

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.taonce.utilmodule.formatDate2Day
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.recycler.BaseAdapter
import com.taonce.wankotlin.base.recycler.BaseHolder
import com.taonce.wankotlin.bean.HomePageBean


/**
 * Author: taoyongxiang
 * Date: 2019/4/10
 * Project: WanKotlin
 * Desc:
 */

class HomePageAdapter(
    private val mContext: Context,
    private val resId: Int,
    private val mData: MutableList<HomePageBean.Data.DatasItem>,
    private val mHeadView: View
) : BaseAdapter<HomePageBean.Data.DatasItem>(mContext, resId, mData, mHeadView) {
    override fun convert(holder: BaseHolder, position: Int) {
        // headView exist, position should -1
        val itemData: HomePageBean.Data.DatasItem = mData[position - 1]
        val title: TextView = holder.getView(R.id.tv_item_home_title)
        val time: TextView = holder.getView(R.id.tv_item_home_time)
        val tag: TextView = holder.getView(R.id.tv_item_home_tag)
        val ivNew: ImageView = holder.getView(R.id.iv_item_home_new)
        title.text = itemData.title
        title.setTextColor(mContext.resources.getColor(R.color.black))
        // over one day, image gone
        ivNew.visibility = if (System.currentTimeMillis().formatDate2Day() == (itemData.publishTime).formatDate2Day()) {
            View.VISIBLE
        } else {
            View.GONE
        }
        time.text = itemData.niceDate
        tag.text = itemData.author
    }
}