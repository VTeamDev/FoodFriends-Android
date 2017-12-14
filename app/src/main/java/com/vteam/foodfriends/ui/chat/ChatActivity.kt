package com.vteam.foodfriends.ui.chat

import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.EditText
import com.vteam.foodfriends.R
import com.vteam.foodfriends.data.model.Message
import com.vteam.foodfriends.ui.adapter.ChatAdapter
import com.vteam.foodfriends.ui.base.BaseActivity
import com.vteam.foodfriends.utils.Constant
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : BaseActivity(), ChatContract.View, View.OnClickListener {
    private val LOG_TAG = ChatActivity::class.java.simpleName
    override lateinit var presenter: ChatContract.Presenter
    lateinit var mAdapter : ChatAdapter
    lateinit var boxId : String

    init {
        contentView = R.layout.activity_chat
    }

    override fun initView() {

    }

    override fun initData() {
        boxId = intent.getStringExtra(Constant.EXTRA_ROOM_ID)
        presenter = ChatPresenter(this, this)
        mAdapter = ChatAdapter(this)

        presenter.getChatList(boxId)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.stackFromEnd = true

        chatList.layoutManager = layoutManager
        chatList.itemAnimator = DefaultItemAnimator()
        chatList.setHasFixedSize(true)

        send.setOnClickListener(this)
        back.setOnClickListener(this)
        add.setOnClickListener(this)
    }

    override fun showChatList(messages: ArrayList<Message>) {
        if (!messages.isEmpty()){
            mAdapter.clear()
            mAdapter.addAll(messages)
            chatList.adapter = mAdapter
        } else {
            Log.e(LOG_TAG, "Chat is empty")
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.send -> {
                val mess : String = messageInput.text.toString()
                presenter.sendMessage(boxId, mess)
                messageInput.setText("")
                if (mAdapter.itemCount > 1){
                    chatList.smoothScrollToPosition(mAdapter.itemCount - 1)
                }
            }
            R.id.back -> {
                onBackPressed()
                finish()
            }
            R.id.add -> {

            }
        }
    }


}
