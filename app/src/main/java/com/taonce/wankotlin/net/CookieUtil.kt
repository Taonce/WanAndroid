package com.taonce.wankotlin.net

import android.content.SharedPreferences
import com.taonce.utilmodule.putSP
import com.taonce.wankotlin.App
import com.taonce.wankotlin.base.Constant

object CookieUtil {

    /**
     * combination cookie list
     */
    fun encodeCookie(cookies: MutableList<String>): String {
        val sb = StringBuilder()
        val set = HashSet<String>()
        cookies.map { cookie ->
            cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        }.forEach {
            it.filterNot { item ->
                set.contains(item)
            }.forEach { item ->
                set.add(item)
            }
        }
        val iterator: MutableIterator<String> = set.iterator()
        while (iterator.hasNext()) {
            val cookie: String = iterator.next()
            sb.append(cookie).append(";")
        }
        val last: Int = sb.lastIndexOf(";")
        if (sb.length - 1 == last) {
            sb.deleteCharAt(last)
        }
        return sb.toString()
    }

    /**
     * add cookie in [SharedPreferences]
     */
    fun saveCookie(cookies: String) {
        App.mInstance.run {
            putSP(Constant.SP_COOKIE, cookies)
        }
    }
}

