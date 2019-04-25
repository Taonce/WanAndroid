package com.taonce.wankotlin.ui.adapter

import android.content.Context
import android.os.Build
import android.os.MessageQueue
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.taonce.utilmodule.formatDate2Day
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.recycler.BaseAdapter
import com.taonce.wankotlin.base.recycler.BaseHolder
import com.taonce.wankotlin.bean.HomePageBean
import com.taonce.wankotlin.bean.QueryBean


/**
 * Author: taoyongxiang
 * Date: 2019/4/12
 * Project: WanKotlin
 * Desc:
 */

class SearchResultAdapter(
    context: Context,
    resId: Int,
    private val mQueryBean: MutableList<QueryBean.Data.DatasItem>
) : BaseAdapter<QueryBean.Data.DatasItem>(context, resId, mQueryBean) {

    override fun convert(holder: BaseHolder, position: Int) {
        val itemData: QueryBean.Data.DatasItem = mQueryBean[position]
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.getView<TextView>(R.id.tv_item_home_title).text =
                Html.fromHtml(itemData.title, Html.FROM_HTML_MODE_LEGACY)
        } else {
            holder.getView<TextView>(R.id.tv_item_home_title).text =
                Html.fromHtml(itemData.title)
        }
        holder.getView<TextView>(R.id.tv_item_home_time).text = itemData.niceDate
        val tag: String = itemData.superChapterName + " / " + itemData.chapterName
        holder.getView<TextView>(R.id.tv_item_home_tag).text = tag
        val ivNew: ImageView = holder.getView(R.id.iv_item_home_new)
        ivNew.visibility = if (System.currentTimeMillis().formatDate2Day() == (itemData.publishTime).formatDate2Day()) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }
}