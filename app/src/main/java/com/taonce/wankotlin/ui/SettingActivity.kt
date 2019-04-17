package com.taonce.wankotlin.ui

import com.taonce.utilmodule.getSP
import com.taonce.utilmodule.getVersionName
import com.taonce.utilmodule.start
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.base.BaseMVPActivity
import com.taonce.wankotlin.bean.LoginBean
import com.taonce.wankotlin.contract.ILogoutView
import com.taonce.wankotlin.net.BaseObserver
import com.taonce.wankotlin.net.RetrofitUtil
import com.taonce.wankotlin.net.RxSchedulers
import com.taonce.wankotlin.presenter.LogoutPresenter
import kotlinx.android.synthetic.main.activity_setting.*


class SettingActivity : BaseMVPActivity<ILogoutView, LogoutPresenter>(), ILogoutView {

    private val mPresenter: LogoutPresenter by lazy { getPresenter() }
    private lateinit var userName: String

    override fun getLayoutId(): Int = R.layout.activity_setting

    override fun initView() {
        userName = getSP("userName", "") as String
        tv_setting_name.text = if (userName.isEmpty()) {
            this.getText(R.string.clickLogin)
        } else {
            userName
        }
        tv_setting_version_number.text = getVersionName()
    }

    override fun initData() {
    }

    override fun initEvent() {
        tv_setting_quit.setOnClickListener { mPresenter.logout() }
        tv_setting_name.setOnClickListener {
            if (userName.isEmpty())
                start(LoginActivity::class.java)
        }
        tv_setting_collection.setOnClickListener {
            RetrofitUtil.mInstance.getService()
                .getCollectionList()
                .compose(RxSchedulers.observableTransformer())
                .subscribe(object : BaseObserver<BaseBean> {
                    override fun onSuccess(value: BaseBean) {
                    }

                    override fun onFailed() {
                    }
                })
        }
        tv_setting_about.setOnClickListener { }
    }

    override fun getPresenter(): LogoutPresenter = LogoutPresenter(this)

    override fun showLogout(bean: LoginBean) {
        userName = getSP("userName", "") as String
        tv_setting_name.text = this.getText(R.string.clickLogin)
    }
}


