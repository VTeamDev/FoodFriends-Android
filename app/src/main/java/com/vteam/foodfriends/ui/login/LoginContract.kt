package com.vteam.foodfriends.ui.login

import com.vteam.foodfriends.ui.base.BasePresenter
import com.vteam.foodfriends.ui.base.BaseView

/**
 * Created by H2PhySicS on 12/11/2017.
 */
interface LoginContract{
    interface Presenter : BasePresenter {
        fun login(email: String, password: String)
        fun validateInput(email : String, password : String) : Boolean
    }

    interface View : BaseView<Presenter> {
        fun showLoadingIndicator(message : String?)
        fun hideLoadingIndicator()
        fun showAlertError(message: String?, title : String?)
        fun openMain()
        fun openRegister()
        fun openForgotPassword()
    }
}