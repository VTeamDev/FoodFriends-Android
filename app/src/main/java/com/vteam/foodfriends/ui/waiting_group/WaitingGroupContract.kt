package com.vteam.foodfriends.ui.waiting_group

import com.vteam.foodfriends.data.model.Pending
import com.vteam.foodfriends.ui.base.BasePresenter
import com.vteam.foodfriends.ui.base.BaseView

/**
 * Created by H2PhySicS on 12/13/2017.
 */
interface WaitingGroupContract{
    interface Presenter : BasePresenter{
        fun getGroups(resId : String)
    }

    interface View : BaseView<Presenter>{
        fun showGroups(groups : ArrayList<Pending>)
        fun showLoadingIndicator(message : String?)
        fun hideLoadingIndicator()
    }
}