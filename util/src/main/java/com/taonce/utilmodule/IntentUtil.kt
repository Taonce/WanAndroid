package com.taonce.utilmodule

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import java.io.Serializable


/**
 * Author: taoyongxiang
 * Date: 2019/4/12
 * Project: WanKotlin
 * Desc:
 */

/**
 * [clazz]: destination [Activity]
 * [map]: put data
 * [isFinished]: [Activity] is finish or is not finish
 */
fun <T : Activity> Activity.start(
    clazz: Class<T>,
    map: Map<String, Any>? = null,
    isFinished: Boolean = false
) {
    val intent = Intent(this, clazz)
    if (map != null) {
        if (map.isNotEmpty()) {
            map.forEach {
                val key: String = it.key
                val value: Any = it.value
                when (value) {
                    is String -> intent.putExtra(it.key, value)
                    is Int -> intent.putExtra(it.key, value)
                    is Float -> intent.putExtra(it.key, value)
                    is Double -> intent.putExtra(it.key, value)
                    is Boolean -> intent.putExtra(it.key, value)
                    is Short -> intent.putExtra(it.key, value)
                    is ShortArray -> intent.putExtra(it.key, value)
                    is Long -> intent.putExtra(it.key, value)
                    is LongArray -> intent.putExtra(it.key, value)
                    is IntArray -> intent.putExtra(it.key, value)
                    is FloatArray -> intent.putExtra(it.key, value)
                    is DoubleArray -> intent.putExtra(it.key, value)
                    is Char -> intent.putExtra(it.key, value)
                    is CharArray -> intent.putExtra(it.key, value)
                    is Byte -> intent.putExtra(it.key, value)
                    is ByteArray -> intent.putExtra(it.key, value)
                    is BooleanArray -> intent.putExtra(it.key, value)
                    is Bundle -> intent.putExtra(it.key, value)
                    is Parcelable -> intent.putExtra(it.key, value)
                    is Serializable -> intent.putExtra(it.key, value)
                }
            }
        }
    }
    this.startActivity(intent)
    if (isFinished) this.finish()
}

/**
 * [clazz]: destination [Activity]
 * [map]: put data
 * Don't need to include `isFinished` as it is an extension of [Fragment]
 */
fun <T : Activity> Fragment.start(
    clazz: Class<T>,
    map: Map<String, Any>? = null
) {
    val intent = Intent(this.context, clazz)
    if (map != null) {
        if (map.isNotEmpty()) {
            map.forEach {
                val key: String = it.key
                val value: Any = it.value
                when (value) {
                    is String -> intent.putExtra(it.key, value)
                    is Int -> intent.putExtra(it.key, value)
                    is Float -> intent.putExtra(it.key, value)
                    is Double -> intent.putExtra(it.key, value)
                    is Boolean -> intent.putExtra(it.key, value)
                    is Short -> intent.putExtra(it.key, value)
                    is ShortArray -> intent.putExtra(it.key, value)
                    is Long -> intent.putExtra(it.key, value)
                    is LongArray -> intent.putExtra(it.key, value)
                    is IntArray -> intent.putExtra(it.key, value)
                    is FloatArray -> intent.putExtra(it.key, value)
                    is DoubleArray -> intent.putExtra(it.key, value)
                    is Char -> intent.putExtra(it.key, value)
                    is CharArray -> intent.putExtra(it.key, value)
                    is Byte -> intent.putExtra(it.key, value)
                    is ByteArray -> intent.putExtra(it.key, value)
                    is BooleanArray -> intent.putExtra(it.key, value)
                    is Bundle -> intent.putExtra(it.key, value)
                    is Parcelable -> intent.putExtra(it.key, value)
                    is Serializable -> intent.putExtra(it.key, value)
                }
            }
        }
    }
    this.startActivity(intent)
}

