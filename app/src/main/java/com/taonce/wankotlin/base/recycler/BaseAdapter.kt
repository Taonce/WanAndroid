package com.taonce.wankotlin.base.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.taonce.utilmodule.showDebug
import com.taonce.utilmodule.showError


/**
 * Author: taoyongxiang
 * Date: 2019/4/4
 * Project: WanKotlin
 * Desc: [RecyclerView.Adapter]基类
 */

abstract class BaseAdapter<T>(
    private val ctx: Context,
    private val layoutRes: Int,
    private val mData: MutableList<T>?,
    private val mHeadView: View? = null,
    private val mFootView: View? = null
) : RecyclerView.Adapter<BaseHolder>() {

    // 利用闭包实现点击事件的lambda语法
    private var mItemClick: ((position: Int) -> Unit)? = null
    private var mItemLongClick: ((position: Int) -> Boolean)? = null

    companion object {
        const val type_header: Int = 1
        const val type_normal: Int = 0
        const val type_footer: Int = 2
    }

    fun setOnItemClickListener(itemClick: (position: Int) -> Unit) {
        this.mItemClick = itemClick
    }

    fun setOnItemLongClickListener(itemLongClick: (position: Int) -> Boolean) {
        this.mItemLongClick = itemLongClick
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        holder.itemView.setOnClickListener {
            // 这里一定要实现闭包的invoke()方法
            if (mHeadView == null) {
                mItemClick?.invoke(position)
            } else {
                mItemClick?.invoke(position - 1)
            }
        }
        holder.itemView.setOnLongClickListener {
            if (mItemLongClick != null) {
                if (mHeadView == null) mItemLongClick!!.invoke(position)
                else mItemLongClick!!.invoke(position - 1)
            } else return@setOnLongClickListener false
        }
        if (getItemViewType(position) == type_header || getItemViewType(position) == type_footer) return
        convert(holder, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return when {
            mHeadView != null && viewType == 1 -> BaseHolder(mHeadView)
            mFootView != null && viewType == 2 -> BaseHolder(mFootView)
            else -> BaseHolder(LayoutInflater.from(ctx).inflate(layoutRes, parent, false))
        }
    }

    override fun getItemCount(): Int {
        return when {
            (mHeadView != null && mFootView == null)
                    || (mFootView != null && mHeadView == null) -> (mData?.size ?: 0) + 1
            mHeadView != null && mFootView != null -> (mData?.size ?: 0) + 2
            mHeadView == null && mFootView == null -> mData?.size ?: 0
            else -> mData?.size ?: 0
        }
    }

    override fun getItemViewType(position: Int): Int {
        mHeadView ?: mFootView ?: return type_normal
        mHeadView?.run { if (position == 0) return type_header }
        mFootView?.run { if (position == itemCount - 1) return type_footer }
        return type_normal
    }

    /**
     * 用来给具体Adapter实现逻辑的抽象方法
     */
    abstract fun convert(holder: BaseHolder, position: Int)

    /**
     * 添加一项数据
     */
    fun addData(item: T) {
        mData?.add(item)
        notifyDataSetChanged()
    }

    /**
     * 添加数据，指定位置刷新
     * [listData]：添加的数据
     * [isDelete]：是否删除原来的数据
     */
    fun addListData(listData: MutableList<T>, isDelete: Boolean = false) {
        if (isDelete) {
            mData?.clear()
        }
        mData?.addAll(listData)
        notifyItemChanged(itemCount)
    }

    /**
     * 删除指定项数据
     * [position]:从0开始
     */
    fun deletePositionData(position: Int) {
        // 防止position越界
        if (position in 0 until itemCount) {
            mData?.removeAt(position)
            notifyItemRemoved(position)
            if (position != itemCount)
                notifyItemRangeChanged(position, itemCount - position)
        } else {
            showError(msg = "delete item failed, position error!")
        }
    }
}

