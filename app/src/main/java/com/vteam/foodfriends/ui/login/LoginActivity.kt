package com.vteam.foodfriends.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import com.vteam.foodfriends.R
import com.vteam.foodfriends.ui.adapter.TextWatcherAdapter
import com.vteam.foodfriends.ui.base.BaseActivity
import com.vteam.foodfriends.ui.main.MainActivity
import com.vteam.foodfriends.ui.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginContract.View, View.OnClickListener {

    override lateinit var presenter: LoginContract.Presenter

    init {
        contentView = R.layout.activity_login
    }

    override fun initView() {
        password.addTextChangedListener(object : TextWatcherAdapter() {
            override fun afterTextChanged(s: Editable?) {
                passwordLayout.isPasswordVisibilityToggleEnabled = s!!.isNotEmpty()
            }
        })
    }

    override fun initData() {
        presenter = LoginPresenter(this, this)
        login.setOnClickListener(this)
        register.setOnClickListener(this)

    }

    override fun onStart() {
        super.onStart()
    }

    override fun onResume() {
        super.onResume()
        presenter.start()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun showLoadingIndicator(message: String?) {
        if (message == null){
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

    override fun openMain() {
        startActivityWithAnimation(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    override fun openRegister() {
        startActivityWithAnimation(Intent(this@LoginActivity, RegisterActivity::class.java))
    }

    override fun openForgotPassword() {

    }

    override fun showAlertError(message: String?, title: String?) {
        showAlert(message!!, title!!)
    }

    override fun onClick(v: View?) {
        val email : String = email.text.toString()
        val password : String = password.text.toString()
        when(v?.id){
            R.id.login -> {
                val isValidate = presenter.validateInput(email, password)
                if (isValidate){
                    presenter.login(email, password)
                } else {
                    showAlertError(getString(R.string.error_login_invalidate_title), getString(R.string.error_login_invalidate_content))
                }
            }
            R.id.register -> openRegister()
            R.id.forgotPassword -> openForgotPassword()
        }
    }

}
