package com.vteam.foodfriends.ui.chat

import com.vteam.foodfriends.data.model.Message
import com.vteam.foodfriends.ui.base.BasePresenter
import com.vteam.foodfriends.ui.base.BaseView

/**
 * Created by H2PhySicS on 12/14/2017.
 */
interface ChatContract{
    interface Presenter : BasePresenter{
        fun getChatList(boxId : String)
        fun sendMessage(boxId: String, message : String)
    }

    interface View : BaseView<Presenter>{
        fun showChatList(messages : ArrayList<Message>)
    }
}