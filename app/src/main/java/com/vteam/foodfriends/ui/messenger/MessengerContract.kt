package com.vteam.foodfriends.ui.messenger

import com.vteam.foodfriends.data.model.ChatRoom
import com.vteam.foodfriends.ui.base.BasePresenter
import com.vteam.foodfriends.ui.base.BaseView

/**
 * Created by H2PhySicS on 12/14/2017.
 */
interface MessengerContract{
    interface Presenter : BasePresenter{
        fun getChatRooms()
    }

    interface View : BaseView<Presenter>{
        fun showChatRooms(rooms : ArrayList<ChatRoom>)
        fun openChat(boxId : String)
    }
}