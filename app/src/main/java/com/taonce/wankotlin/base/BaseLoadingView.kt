package com.taonce.wankotlin.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.taonce.wankotlin.R
import kotlinx.android.synthetic.main.base_loading_view.*


/**
 * Author: taoyongxiang
 * Date: 2019/4/4
 * Project: WanKotlin
 * Desc:
 */

class BaseLoadingView : AlertDialog {

    constructor(context: Context, themeResId: Int) : super(context, themeResId)

    constructor(context: Context) : super(context)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.base_loading_view)
        loadingView.smoothToShow()

        // 点击屏幕，dialog不消失
        setCanceledOnTouchOutside(false)
    }

}