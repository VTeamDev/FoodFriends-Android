package com.vteam.foodfriends.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.vteam.foodfriends.R
import com.vteam.foodfriends.data.model.Message
import com.vteam.foodfriends.data.remote.FirebaseUserService
import com.vteam.foodfriends.ui.base.BaseAdapter
import com.vteam.foodfriends.ui.base.BaseViewHolder
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by H2PhySicS on 12/14/2017.
 */
class ChatAdapter(context: Context) : BaseAdapter<Message>(context) {
    val mUserService = FirebaseUserService(context)
    val SELF = 1
    val FRIEND = 2

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<Message> {
        if (viewType == FRIEND){
            contentView = R.layout.item_chat_left
        } else {
            contentView = R.layout.item_chat_right
        }
        val view : View = LayoutInflater.from(context).inflate(contentView!!, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Message>?, position: Int) {
        val mess = mList!![position]
        (holder as ChatViewHolder).bind(mess)
    }

    override fun getItemViewType(position: Int): Int {
        val mess = mList!![position]
        val firebaseUser = mUserService.getCurrentFirebaseUser()
        return if (mess.uid == firebaseUser!!.uid) SELF
        else FRIEND
    }

    inner class ChatViewHolder(itemView : View) : BaseViewHolder<Message>(itemView) {
        val avatar = itemView.findViewById<CircleImageView>(R.id.avatar)
        val message = itemView.findViewById<TextView>(R.id.message)
        val time = itemView.findViewById<TextView>(R.id.time)

        override fun bind(t: Message) {
            message.text = t.content
        }

    }

}