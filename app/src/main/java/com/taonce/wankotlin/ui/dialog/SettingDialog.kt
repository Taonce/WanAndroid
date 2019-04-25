package com.taonce.wankotlin.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.taonce.wankotlin.R
import kotlinx.android.synthetic.main.common_dialog.*
import kotlinx.coroutines.channels.ticker


class SettingDialog(context: Context, private val title: String) : Dialog(context) {

    private var enterListener: (() -> Unit)? = null
    private var cancelListener: (() -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.common_dialog)
        tv_dialog_title.text = title
        onClick()
        setCanceledOnTouchOutside(false)
    }

    fun setEnterListener(block: () -> Unit) {
        this.enterListener = block
    }

    fun setCancelListener(block: () -> Unit) {
        this.cancelListener = block
    }

    private fun onClick() {
        tv_dialog_cancel.setOnClickListener {
            dismiss()
            cancelListener?.invoke()
        }
        tv_dialog_enter.setOnClickListener {
            dismiss()
            enterListener?.invoke()
        }
    }
}

