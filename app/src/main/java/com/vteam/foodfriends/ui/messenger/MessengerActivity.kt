package com.vteam.foodfriends.ui.messenger

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.vteam.foodfriends.R
import com.vteam.foodfriends.data.model.ChatRoom
import com.vteam.foodfriends.ui.adapter.MessengerAdapter
import com.vteam.foodfriends.ui.adapter.WaitingSingleAdapter
import com.vteam.foodfriends.ui.base.BaseActivity
import com.vteam.foodfriends.ui.chat.ChatActivity
import com.vteam.foodfriends.utils.Constant
import kotlinx.android.synthetic.main.activity_messenger.*

class MessengerActivity : BaseActivity(), MessengerContract.View, WaitingSingleAdapter.OnItemClickListener {

    override lateinit var presenter: MessengerContract.Presenter

    private lateinit var mAdapter : MessengerAdapter
    init {
        contentView = R.layout.activity_messenger
    }

    override fun initView() {

    }

    override fun initData() {


        presenter = MessengerPresenter(this, this)
        mAdapter = MessengerAdapter(this)
        mAdapter.setOnItemClickListener(this)

        presenter.getChatRooms()

    }

    override fun showChatRooms(rooms: ArrayList<ChatRoom>) {
        if (!rooms.isEmpty()){
            mAdapter.clear()
            mAdapter.addAll(rooms)
            messengerList.adapter = mAdapter
        }
    }

    override fun openChat(boxId: String) {
        val intent = Intent(this@MessengerActivity, ChatActivity::class.java)
        intent.putExtra(Constant.EXTRA_ROOM_ID, boxId)
        startActivityWithAnimation(intent)
    }

    override fun onClick(view: View?, position: Int) {
        val room = mAdapter.getData()[position]

        openChat(room.boxId)
    }

}
