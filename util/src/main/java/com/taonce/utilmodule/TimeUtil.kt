package com.taonce.utilmodule

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*


/**
 * Author: taoyongxiang
 * Date: 2019/4/15
 * Project: WanKotlin
 * Desc:
 */

@SuppressLint("SimpleDateFormat")
fun Long.formatDate2Day(): String {
    val format: SimpleDateFormat = SimpleDateFormat("yyyy.MM.dd")
    return format.format(Date(this))
}

@SuppressLint("SimpleDateFormat")
fun Long.formatDate2Minute(): String {
    val format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm")
    return format.format(Date(this))
}

@SuppressLint("SimpleDateFormat")
fun Long.formatDate2Second(): String {
    val format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    return format.format(Date(this))
}


