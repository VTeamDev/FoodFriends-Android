package com.vteam.foodfriends.ui.main

import android.content.Context

/**
 * Created by H2PhySicS on 12/11/2017.
 */
class MainPresenter(context: Context, view : MainContract.View) : MainContract.Presenter {

    init {
        view.presenter = this
    }

    override fun start() {

    }

    override fun destroy() {

    }

}