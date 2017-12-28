package com.vteam.foodfriends.ui.register

import android.app.Activity
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import com.vteam.foodfriends.R
import com.vteam.foodfriends.ui.adapter.TextWatcherAdapter
import com.vteam.foodfriends.ui.base.BaseActivity
import com.vteam.foodfriends.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), RegisterContract.View, View.OnClickListener {

    override lateinit var presenter: RegisterContract.Presenter

    companion object {
        private val TAG = RegisterActivity::class.java.simpleName
    }

    init {
        contentView = R.layout.activity_register
    }

    override fun initView() {
        password.addTextChangedListener(object : TextWatcherAdapter() {
            override fun afterTextChanged(s: Editable?) {
                passwordLayout.isPasswordVisibilityToggleEnabled = s!!.isNotEmpty()
            }
        })
        passwordComfirm.addTextChangedListener(object : TextWatcherAdapter() {
            override fun afterTextChanged(s: Editable?) {
                passwordConfirmLayout.isPasswordVisibilityToggleEnabled = s!!.isNotEmpty()
            }
        })
    }

    override fun initData() {
        presenter = RegisterPresenter(this, this)
        bt_register.setOnClickListener(this)
        tv_login.setOnClickListener(this)
    }

    override fun showLoadingIndicator(message: String?) {
        if (message == null) {
            showLoading()
        } else {
            showLoading(message)
        }
        enableTouchable(false)
    }

    override fun hideLoadingIndicator() {
        hideLoading()
        enableTouchable(true)
    }

    override fun showAlertError(message: String?, title: String?) {
        showAlert(title!!, message!!)
    }

    override fun openMain() {
        startActivityWithAnimation(Intent(this@RegisterActivity, MainActivity::class.java))
        finish()
    }

    override fun onClick(v: View?) {
        val email: String? = email.text.toString()
        val firstname: String? = firstname.text.toString()
        val lastname: String? = lastname.text.toString()
        val passwordConfirm: String? = passwordComfirm.text.toString()
        val password: String? = password.text.toString()

        when (v?.id) {
            R.id.bt_register -> {
                presenter.validateInput(firstname, lastname, email, password, passwordConfirm)
                presenter.register(firstname, lastname, email, password)
                openMain()
            }
            R.id.tv_login -> {
                Log.d(TAG,"hello")
                finishActivityWithAnimation()
            }
        }
    }


}
