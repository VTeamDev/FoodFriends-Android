package com.vteam.foodfriends.ui.register

import com.vteam.foodfriends.ui.base.BasePresenter
import com.vteam.foodfriends.ui.base.BaseView

/**
 * Created by H2PhySicS on 12/11/2017.
 */
interface RegisterContract {
    interface Presenter : BasePresenter {
        fun register(firstname: String?, lastName: String?, email: String?, password: String?)
        fun validateInput(firstname: String?, lastName: String?, email: String?, password: String?, passwordConfirm: String?): Boolean
    }

    interface View : BaseView<Presenter> {
        fun showLoadingIndicator(message: String?)
        fun hideLoadingIndicator()
        fun showAlertError(message: String?, title: String?)
        fun openMain()
    }
}