package com.vteam.foodfriends.ui.waiting_single

import com.vteam.foodfriends.data.model.Pending
import com.vteam.foodfriends.ui.base.BasePresenter
import com.vteam.foodfriends.ui.base.BaseView

/**
 * Created by H2PhySicS on 12/13/2017.
 */
interface WaitingSingleContract{
    interface Presenter : BasePresenter{
        fun getPairs(resId : String)

    }

    interface View : BaseView<Presenter>{
        fun showPairs(pairs : ArrayList<Pending>)
        fun showLoadingIndicator(message : String?)
        fun hideLoadingIndicator()
    }
}