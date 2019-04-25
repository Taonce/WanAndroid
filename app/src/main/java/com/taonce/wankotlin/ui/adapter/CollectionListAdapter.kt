package com.taonce.wankotlin.ui.adapter

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import com.taonce.utilmodule.formatDate2Day
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.recycler.BaseAdapter
import com.taonce.wankotlin.base.recycler.BaseHolder
import com.taonce.wankotlin.bean.CollectionListBean
import com.taonce.wankotlin.bean.db.CollectionDB
import kotlinx.android.synthetic.main.item_collection_list.view.*

class CollectionListAdapter(
    private val context: Context,
    resId: Int,
    private val mCollectionData: MutableList<CollectionDB>
) : BaseAdapter<CollectionDB>(context, resId, mCollectionData) {

    override fun convert(holder: BaseHolder, position: Int) {
        holder.getView<TextView>(R.id.tv_item_collection_title).text = mCollectionData[position].title
        holder.getView<TextView>(R.id.tv_item_collection_publish_time).text =
            String.format(
                context.resources.getString(R.string.published_time),
                mCollectionData[position].publishTime
            )
        holder.getView<TextView>(R.id.tv_item_collection_collected_time).text =
            String.format(context.resources.getString(R.string.collected_time), mCollectionData[position].collectTime)
        holder.getView<TextView>(R.id.tv_item_collection_author).text =
            String.format(context.resources.getString(R.string.author), mCollectionData[position].author)
        holder.getView<ImageView>(R.id.iv_item_collection).setOnClickListener {
            listener?.invoke(position)
        }
    }

    private var listener: ((position: Int) -> Unit)? = null
    fun setCancelListener(listener: ((position: Int) -> Unit)?) {
        this.listener = listener
    }
}
