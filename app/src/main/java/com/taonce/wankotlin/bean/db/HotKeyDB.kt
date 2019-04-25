package com.taonce.wankotlin.bean.db

import org.litepal.annotation.Column
import org.litepal.crud.LitePalSupport

/**
 * Author: taoyongxiang
 * Date: 2019/4/12
 * Project: WanKotlin
 * Desc:
 */

class HotKeyDB() : LitePalSupport() {
    val id: Long = 0
    @Column(unique = true, defaultValue = "Android")
    var key: String = ""
    var times: Int = 0
}

