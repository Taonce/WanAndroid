package com.taonce.wankotlin.model

import com.taonce.wankotlin.bean.HotKeyBean
import com.taonce.wankotlin.bean.HotKeyDB
import com.taonce.wankotlin.contract.IHotKeyModel
import com.taonce.wankotlin.net.BaseObserver
import com.taonce.wankotlin.net.RetrofitUtil
import com.taonce.wankotlin.net.RxSchedulers
import org.litepal.LitePal
import org.litepal.extension.find


/**
 * Author: taoyongxiang
 * Date: 2019/4/9
 * Project: WanKotlin
 * Desc:
 */

class HotKeyModel : IHotKeyModel {
    override fun getHotKey(listener: IHotKeyModel.OnGetHotKeyListener) {
        RetrofitUtil.mInstance
            .getService()
            .getHotKey()
            .compose(RxSchedulers.observableTransformer())
            .subscribe(object : BaseObserver<HotKeyBean> {
                override fun onSuccess(value: HotKeyBean) {
                    listener.onListener(value)
                }

                override fun onFailed() {
                    listener.onListener(null)
                }
            })
    }

    override fun saveKey2DB(key: String) {
        val item: List<HotKeyDB> = LitePal.where("key = ?", key).find()
        if (item.isEmpty()) {
            val hotKeyDB = HotKeyDB()
            hotKeyDB.key = key
            hotKeyDB.times = 1
            hotKeyDB.save()
        } else {
            val hotKeyDB = HotKeyDB()
            hotKeyDB.times = item[0].times + 1
            hotKeyDB.update(item[0].id)
        }
    }

    override fun getKeyAll(listener: IHotKeyModel.OnGetHotKeyListener) {
        LitePal.order("times desc").findAsync(HotKeyDB::class.java).listen {
            listener.onDBListener(it)
        }
    }
}