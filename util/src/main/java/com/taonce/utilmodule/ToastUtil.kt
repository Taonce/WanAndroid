package com.taonce.utilmodule


import android.content.Context
import android.widget.Toast
import androidx.fragment.app.Fragment


/**
 * Author: taoyongxiang
 * Date: 2019/3/28
 * Desc: 吐司工具
 */

fun Context.toast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun Context.toast(resId: Int) {
    Toast.makeText(this, this.resources.getString(resId), Toast.LENGTH_SHORT).show()
}

fun Context.toastLong(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}

fun Context.toastLong(resId: Int) {
    Toast.makeText(this, this.resources.getString(resId), Toast.LENGTH_LONG).show()
}

fun Fragment.toast(text: String) {
    Toast.makeText(this.context, text, Toast.LENGTH_SHORT).show()
}

fun Fragment.toast(resId: Int) {
    Toast.makeText(this.context, this.resources.getString(resId), Toast.LENGTH_SHORT).show()
}

fun Fragment.toastLong(text: String) {
    Toast.makeText(this.context, text, Toast.LENGTH_LONG).show()
}

fun Fragment.toastLong(resId: Int) {
    Toast.makeText(this.context, this.resources.getString(resId), Toast.LENGTH_LONG).show()
}

