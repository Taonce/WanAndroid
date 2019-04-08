package com.taonce.wankotlin.util

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide


/**
 * Author: taoyongxiang
 * Date: 2019/4/8
 * Project: WanKotlin
 * Desc:
 */

fun Context.loadImage(resId: Int, target: ImageView) {
    Glide.with(this)
        .load(resId)
        .into(target)
}

