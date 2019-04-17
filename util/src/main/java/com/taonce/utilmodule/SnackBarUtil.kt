package com.taonce.utilmodule

import android.app.Activity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import java.time.Duration

/**
 * [Snackbar]工具类
 * [view]: attch view
 * [msg]: show message
 * [bgColor]: background color
 * [msgColor]: message text color
 * [actionColor]: action text color
 * [action]: action text
 * [isCenter]: if center in view
 * [duration]: show time(s)
 * [listener]: action listener
 */
fun showSnackBar(
    view: View,
    msg: String,
    bgColor: Int? = null,
    msgColor: Int? = null,
    actionColor: Int? = null,
    action: String? = null,
    isCenter: Boolean = false,
    duration: Int = 3000,
    listener: ((view: View) -> Unit)? = null
) {
    val snackBar: Snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_SHORT)
    action?.let { listener?.let { snackBar.setAction(action, listener) } }
    val rootView: View = snackBar.view
    bgColor?.let { rootView.setBackgroundColor(it) }
    msgColor?.let { rootView.findViewById<TextView>(R.id.snackbar_text).setTextColor(it) }
    actionColor?.let { snackBar.setActionTextColor(it) }
    if (isCenter) {
        val params: ViewGroup.LayoutParams = rootView.layoutParams
        val layoutParams: CoordinatorLayout.LayoutParams = CoordinatorLayout.LayoutParams(params.width, params.height)
        layoutParams.gravity = Gravity.CENTER
        rootView.layoutParams = layoutParams
    }
    snackBar.duration = duration
    snackBar.show()
}

