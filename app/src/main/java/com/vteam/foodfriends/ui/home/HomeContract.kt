package com.vteam.foodfriends.ui.home

import com.vteam.foodfriends.data.model.Restaurant
import com.vteam.foodfriends.ui.base.BasePresenter
import com.vteam.foodfriends.ui.base.BaseView

/**
 * Created by H2PhySicS on 12/11/2017.
 */
interface HomeContract{
    interface Presenter : BasePresenter {
        fun loadRestaurants()
        fun follow()
        fun direct()
    }

    interface View : BaseView<Presenter> {
        fun showRestaurants(rests : ArrayList<Restaurant>)
        fun openDetail(id : String)
        fun showLoadingIndicator(message : String?)
        fun hideLoadingIndicator()
    }
}