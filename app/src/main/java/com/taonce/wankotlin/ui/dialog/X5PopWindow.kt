package com.taonce.wankotlin.ui.dialog

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.TextView
import com.taonce.utilmodule.showDebug
import com.taonce.wankotlin.R


class X5PopWindow(context: Context, private val isCollected: Boolean) : PopupWindow(context), View.OnClickListener {

    private lateinit var cancelTv: TextView
    private lateinit var collectUserTv: TextView
    private lateinit var collectOfflineTv: TextView
    private lateinit var unCollect: TextView
    private lateinit var divideView: View

    init {
        showDebug(msg = "pop init")
        val layoutInflater: LayoutInflater = LayoutInflater.from(context)
        val mRootView: View = layoutInflater.inflate(R.layout.item_x5_pop, null)
        contentView = mRootView
        width = WindowManager.LayoutParams.MATCH_PARENT
        height = WindowManager.LayoutParams.WRAP_CONTENT
        isOutsideTouchable = true
        isFocusable = true
        animationStyle = R.style.PopAnimator
        setBackgroundDrawable(BitmapDrawable(context.resources))
        showAtLocation(mRootView, Gravity.BOTTOM, 0, 0)
        initView(mRootView)
        onClick()
    }

    private fun initView(view: View) {
        view.run {
            collectOfflineTv = findViewById(R.id.tv_item_pop_collect)
            collectUserTv = findViewById(R.id.tv_item_pop_collect_offline)
            cancelTv = findViewById(R.id.tv_item_pop_cancel)
            unCollect = findViewById(R.id.tv_item_pop_uncollect)
            divideView = findViewById(R.id.view_item_pop)
            setVisible()
        }
    }

    private fun setVisible() {
        if (isCollected) {
            collectOfflineTv.visibility = View.GONE
            collectUserTv.visibility = View.GONE
            divideView.visibility = View.GONE
            unCollect.visibility = View.VISIBLE
        } else {
            collectOfflineTv.visibility = View.VISIBLE
            collectUserTv.visibility = View.VISIBLE
            divideView.visibility = View.VISIBLE
            unCollect.visibility = View.GONE
        }
    }

    private var collectUserListener: (() -> Unit)? = null
    private var collectOfflineListener: (() -> Unit)? = null
    private var unCollectListener: (() -> Unit)? = null
    private fun onClick() {
        cancelTv.setOnClickListener(this)
        collectUserTv.setOnClickListener(this)
        collectOfflineTv.setOnClickListener(this)
        unCollect.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tv_item_pop_collect -> {
                dismiss()
                collectUserListener?.invoke()
            }
            R.id.tv_item_pop_collect_offline -> {
                dismiss()
                collectOfflineListener?.invoke()
            }
            R.id.tv_item_pop_uncollect -> {
                dismiss()
                unCollectListener?.invoke()
            }
            R.id.tv_item_pop_cancel -> dismiss()
        }
    }

    fun setCollectUserListener(block: () -> Unit) {
        this.collectUserListener = block
    }

    fun setCollectOfflineListener(block: () -> Unit) {
        this.collectOfflineListener = block
    }

    fun setUnCollectListener(block: () -> Unit) {
        this.unCollectListener = block
    }
}
