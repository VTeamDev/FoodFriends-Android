package com.vteam.foodfriends.ui.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.View
import com.vteam.foodfriends.R
import com.vteam.foodfriends.ui.adapter.TextWatcherAdapter
import com.vteam.foodfriends.ui.base.BaseActivity
import com.vteam.foodfriends.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : BaseActivity(), RegisterContract.View, View.OnClickListener {

    override lateinit var presenter: RegisterContract.Presenter

    init {
        contentView = R.layout.activity_register
    }

    override fun initView() {
        password.addTextChangedListener(object : TextWatcherAdapter() {
            override fun afterTextChanged(s: Editable?) {
                passwordLayout.isPasswordVisibilityToggleEnabled = s!!.isNotEmpty()
            }
        })
        passwordComfirm.addTextChangedListener(object : TextWatcherAdapter(){
            override fun afterTextChanged(s: Editable?) {
                passwordConfirmLayout.isPasswordVisibilityToggleEnabled = s!!.isNotEmpty()
            }
        })
    }

    override fun initData() {
        presenter = RegisterPresenter(this, this)
        register.setOnClickListener(this)
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

    override fun showAlertError(message: String?, title: String?) {

    }

    override fun openMain() {
        startActivityWithAnimation(Intent(this@RegisterActivity, MainActivity::class.java))
        finish()
    }

    override fun onClick(v: View?) {
        val email : String? = email.text.toString()
        val username : String? = username.text.toString()
        val password : String? = password.text.toString()
        val passwordConfirm : String? = passwordComfirm.text.toString()
        val phone : String? = phone.text.toString()
        val birthday : String? = birthday.text.toString()
        val gender : Boolean = true
        when(v?.id){
            R.id.register -> {
//                presenter.validateInput(email, username, password, passwordConfirm, phone, birthday)
                presenter.register(email!!, password!!, username!!, phone!!, birthday!!, gender)
            }
        }
    }


}
