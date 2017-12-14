package com.vteam.foodfriends.ui.detail

import com.vteam.foodfriends.data.model.Comment
import com.vteam.foodfriends.data.model.Restaurant
import com.vteam.foodfriends.ui.base.BasePresenter
import com.vteam.foodfriends.ui.base.BaseView

/**
 * Created by H2PhySicS on 12/13/2017.
 */
interface DetailContract{
    interface Presenter : BasePresenter {
        fun getRestaurant(id : String)
    }

    interface View : BaseView<Presenter> {
        fun showRestDetail(restaurant: Restaurant)
        fun showComments(comment : ArrayList<Comment>)
        fun showLoadingIndicator(message : String?)
        fun hideLoadingIndicator()
        fun openWriteComment(id : String)
        fun openWaitingRoom(id : String)
    }
}