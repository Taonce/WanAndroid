package com.taonce.wankotlin.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.text.TextUtils
import android.view.View
import com.taonce.utilmodule.getDeviceWidth
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
 * Desc:
 */

class LoginActivity : BaseMVPActivity<ILoginView, LoginPresenter>(), ILoginView {


    private val loginPresenter: LoginPresenter by lazy { getPresenter() }
    private lateinit var userName: String
    private lateinit var password: String
    private lateinit var rePassword: String

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initData() {
    }

    override fun initView() {
        loadImage(R.drawable.icon_large, iv_login)
    }

    override fun initEvent() {
        bt_login.setOnClickListener {
            userName = et_name.text.toString().trim()
            password = et_password.text.toString().trim()
            when {
                TextUtils.isEmpty(userName) -> {
                    toast("userName can not be empty! ")
                    return@setOnClickListener
                }
                TextUtils.isEmpty(password) -> {
                    toast("password can not be empty! ")
                    return@setOnClickListener
                }
                else -> loginPresenter.login(userName, password)
            }
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
        // bt_login missing
        val x: Float = bt_login.x
        val btTranslator: ObjectAnimator = ObjectAnimator.ofFloat(bt_login, "x", x, -bt_login.width.toFloat())
        val btAlpha: ObjectAnimator = ObjectAnimator.ofFloat(bt_login, "alpha", 1f, 0f)
        // et_repassword enter
        layout_repassword.visibility = View.VISIBLE
        val etTranslator: ObjectAnimator = ObjectAnimator.ofFloat(layout_repassword, "x", getDeviceWidth().toFloat(), x)
        val etAlpha: ObjectAnimator = ObjectAnimator.ofFloat(layout_repassword, "alpha", 0.1f, 1.0f)
        val animatorSet: AnimatorSet = AnimatorSet()
        animatorSet.apply {
            playTogether(btTranslator, btAlpha, etAlpha, etTranslator)
            duration = 1000L
            start()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    super.onAnimationEnd(animation)
                    bt_login.visibility = View.GONE
                }
            })
        }
    }

    private fun register() {
        userName = et_name.text.toString().trim()
        password = et_password.text.toString().trim()
        rePassword = et_repassword.text.toString().trim()
        when {
            TextUtils.isEmpty(userName) -> {
                toast("userName can not be empty! ")
                return
            }
            TextUtils.isEmpty(password) -> {
                toast("password can not be empty! ")
                return
            }
            TextUtils.isEmpty(rePassword) -> {
                toast("rePassword can not be empty! ")
                return
            }
            else -> loginPresenter.register(userName, password, rePassword)
        }
    }

    override fun showRegisterResult(bean: BaseBean) {
        loginPresenter.login(userName, password)
    }
}
