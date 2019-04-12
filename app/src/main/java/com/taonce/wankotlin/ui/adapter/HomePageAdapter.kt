package com.taonce.wankotlin.ui.adapter

import android.content.Context
import android.service.quicksettings.Tile
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.recycler.BaseAdapter
import com.taonce.wankotlin.base.recycler.BaseHolder
import com.taonce.wankotlin.bean.HomePageBean
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.item_home_article.view.*


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
        val itemData: HomePageBean.Data.DatasItem = mData[position]
        val title: TextView = holder.getView(R.id.tv_item_home_title)
        val time: TextView = holder.getView(R.id.tv_item_home_time)
        val tag: TextView = holder.getView(R.id.tv_item_home_tag)
        val ivNew: ImageView = holder.getView(R.id.iv_item_home_new)
        title.text = itemData.title
        // over one day, image gone
        ivNew.visibility = if (System.currentTimeMillis() - itemData.publishTime <= 24 * 60 * 60 * 100000) {
            View.VISIBLE
        } else {
            View.GONE
        }
        time.text = itemData.niceDate
        tag.text = itemData.author
    }
}