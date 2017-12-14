package com.vteam.foodfriends.ui.waiting

import com.vteam.foodfriends.ui.base.BasePresenter
import com.vteam.foodfriends.ui.base.BaseView

/**
 * Created by H2PhySicS on 12/13/2017.
 */
interface WaitingContract {
    interface Presenter : BasePresenter{
        fun createPending(resId : String, expectedTime : String, isSingle : Boolean)
    }

    interface View : BaseView<Presenter>{
        fun showCreateDialog()
    }
}