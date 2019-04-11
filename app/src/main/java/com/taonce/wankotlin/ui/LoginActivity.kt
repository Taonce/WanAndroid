package com.taonce.wankotlin.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.text.TextUtils
import android.view.View
import androidx.core.view.marginStart
import com.taonce.utilmodule.getDeviceWidth
import com.taonce.utilmodule.showInfo
import com.taonce.utilmodule.toast
import com.taonce.wankotlin.R
import com.taonce.wankotlin.base.BaseBean
import com.taonce.wankotlin.base.BaseMVPActivity
import com.taonce.wankotlin.bean.LoginBean
import com.taonce.wankotlin.contract.ILoginView
import com.taonce.wankotlin.presenter.LoginPresenter
import com.taonce.wankotlin.util.loadImage
import kotlinx.android.synthetic.main.activity_login.*


/**
 * Author: taoyongxiang
 * Date: 2019/4/8
 * Project: WanKotlin
 * Desc: 登录注册界面
 */

class LoginActivity : BaseMVPActivity<ILoginView, LoginPresenter>(), ILoginView {

    private val loginPresenter: LoginPresenter by lazy { getPresenter() }
    private lateinit var userName: String
    private lateinit var password: String
    private lateinit var rePassword: String
    private var btTranslator: ObjectAnimator? = null
    private var btAlpha: ObjectAnimator? = null
    private var etTranslator: ObjectAnimator? = null
    private var etAlpha: ObjectAnimator? = null
    // 登录按钮位移距离屏幕左侧的长度
    private var marginX: Float = 0.0f

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initData() {
        marginX = layout_name.marginStart.toFloat()
        showInfo(msg = "initData margin x is $marginX")
    }

    override fun initView() {
        loadImage(R.drawable.icon_large, iv_login)
    }

    override fun initEvent() {
        bt_login.setOnClickListener {
            userName = et_name.text.toString().trim()
            password = et_password.text.toString().trim()
            loginPresenter.login(this@LoginActivity, userName, password)
        }
        bt_register.setOnClickListener {
            when (bt_login.visibility) {
                View.VISIBLE -> translate()
                View.GONE -> register()
                else -> register()
            }
        }
    }

    override fun getPresenter(): LoginPresenter = LoginPresenter(this)

    override fun showLoginResult(bean: LoginBean) {
        toast("login success! ")
    }

    private fun translate() {
        showInfo(msg = "margin x is: $marginX")
        // bt_login missing
        btTranslator = ObjectAnimator.ofFloat(bt_login, "x", marginX, -bt_login.width.toFloat())
        btAlpha = ObjectAnimator.ofFloat(bt_login, "alpha", 1f, 0f)
        // et_repassword enter
        layout_repassword.visibility = View.VISIBLE
        etTranslator = ObjectAnimator.ofFloat(layout_repassword, "x", getDeviceWidth().toFloat(), marginX)
        etAlpha = ObjectAnimator.ofFloat(layout_repassword, "alpha", 0.1f, 1.0f)
        startAnimator(bt_login)
    }

    private fun register() {
        userName = et_name.text.toString().trim()
        password = et_password.text.toString().trim()
        rePassword = et_repassword.text.toString().trim()
        loginPresenter.register(this@LoginActivity, userName, password, rePassword)
    }

    override fun showRegisterResult(bean: LoginBean) {
        // bt_login enter
        bt_login.visibility = View.VISIBLE
        btTranslator = ObjectAnimator.ofFloat(bt_login, "x", getDeviceWidth().toFloat(), marginX)
        btAlpha = ObjectAnimator.ofFloat(bt_login, "alpha", 0.1f, 1f)
        // et_repassword missing
        etTranslator = ObjectAnimator.ofFloat(layout_repassword, "x", marginX, -(et_repassword.width).toFloat())
        etAlpha = ObjectAnimator.ofFloat(layout_repassword, "alpha", 1f, 0f)
        startAnimator(layout_password)
    }

    private fun startAnimator(view: View) {
        val animatorSet = AnimatorSet()
        animatorSet.apply {
            playTogether(btTranslator, btAlpha, etAlpha, etTranslator)
            duration = 1000L
            start()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    view.visibility = View.GONE
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        btTranslator?.cancel()
        btAlpha?.cancel()
        etTranslator?.cancel()
        etAlpha?.cancel()
    }
}
