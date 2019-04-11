package com.taonce.wankotlin.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.widget.TextView
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.recycler.BaseAdapter
import com.taonce.wankotlin.base.recycler.BaseHolder
import com.taonce.wankotlin.bean.WxHistoryBean
import kotlinx.android.synthetic.main.item_chapter_article.view.*


/**
 * Author: taoyongxiang
 * Date: 2019/4/11
 * Project: WanKotlin
 * Desc:
 */

class ChapterArticleAdapter(
    private val context: Context,
    private val resId: Int,
    private val mData: MutableList<WxHistoryBean.Data.DatasItem>
) : BaseAdapter<WxHistoryBean.Data.DatasItem>(context, resId, mData) {

    @SuppressLint("SetTextI18n")
    override fun convert(holder: BaseHolder, position: Int) {
        holder.getView<TextView>(R.id.tv_item_chapter_article_title).text = mData[position].title
        holder.getView<TextView>(R.id.tv_item_chapter_article_time).text = mData[position].niceDate
        holder.getView<TextView>(R.id.tv_item_chapter_article_author).text =
            "${mData[position].superChapterName}: ${mData[position].chapterName}"
            }
    }