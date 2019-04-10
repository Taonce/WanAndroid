package com.taonce.wankotlin.base.recycler

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
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
        if (getItemViewType(position) == type_header || getItemViewType(position) == type_footer) return
        convert(holder, position)
        holder.itemView.setOnClickListener {
            // 这里一定要实现闭包的invoke()方法
            mItemClick?.invoke(position)
        }
        holder.itemView.setOnLongClickListener {
            if (mItemLongClick != null) {
                mItemLongClick!!.invoke(position)
            } else return@setOnLongClickListener false
        }
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
        return when {
            mHeadView == null && mFootView == null -> type_normal
            mHeadView != null && position == 0 -> type_header
            mFootView != null && position == itemCount - 1 -> type_footer
            else -> type_normal
        }
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
     * 添加数据
     * [listData]：添加的数据
     * [isDelete]：是否删除原来的数据
     */
    fun addListData(listData: MutableList<T>, isDelete: Boolean) {
        if (isDelete) {
            mData?.clear()
        }
        mData?.addAll(listData)
        notifyDataSetChanged()
    }

    /**
     * 删除指定项数据
     * [position]:从0开始
     */
    fun deletePositionData(position: Int) {
        // 防止position越界
        if (position in 1..(itemCount - 1)) {
            mData?.remove(mData[position])
            notifyDataSetChanged()
        } else {
            showError(msg = "delete item failed, position error!")
        }
    }
}

