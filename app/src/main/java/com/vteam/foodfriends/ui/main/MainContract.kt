package com.vteam.foodfriends.ui.main

import com.vteam.foodfriends.ui.base.BasePresenter
import com.vteam.foodfriends.ui.base.BaseView

/**
 * Created by H2PhySicS on 12/11/2017.
 */
interface MainContract {
    interface Presenter : BasePresenter

    interface View : BaseView<Presenter>{
        fun openMessenger()
    }
}