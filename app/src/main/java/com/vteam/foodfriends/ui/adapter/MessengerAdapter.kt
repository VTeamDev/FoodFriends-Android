package com.vteam.foodfriends.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.vteam.foodfriends.R
import com.vteam.foodfriends.data.model.ChatRoom
import com.vteam.foodfriends.data.remote.FirebaseUserService
import com.vteam.foodfriends.ui.base.BaseAdapter
import com.vteam.foodfriends.ui.base.BaseViewHolder
import com.vteam.foodfriends.utils.Constant
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by H2PhySicS on 12/14/2017.
 */
class MessengerAdapter(context: Context) : BaseAdapter<ChatRoom>(context) {
    private val mUserService = FirebaseUserService(context)
    private lateinit var listener : WaitingSingleAdapter.OnItemClickListener

    init {
        contentView = R.layout.item_messenger
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ChatRoom>?, position: Int) {
        val room = mList!![position]
        (holder as MessengerViewHolder).bind(room)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<ChatRoom> {
        val view : View = LayoutInflater.from(context).inflate(contentView!!, parent, false)
        return MessengerViewHolder(view)
    }

    fun setOnItemClickListener(listener: WaitingSingleAdapter.OnItemClickListener){
        this.listener = listener
    }

    inner class MessengerViewHolder(itemView : View) : BaseViewHolder<ChatRoom>(itemView), View.OnClickListener{

        val avatar = itemView.findViewById<CircleImageView>(R.id.avatar)
        val title = itemView.findViewById<TextView>(R.id.title)
        val lastMessage = itemView.findViewById<TextView>(R.id.lastMessage)
        val time = itemView.findViewById<TextView>(R.id.time)

        init {
            itemView.rootView.setOnClickListener(this)
        }

        override fun bind(t: ChatRoom) {
            mUserService.getUser(t.title)
                    .addSnapshotListener { documentSnapshot, firebaseFirestoreException ->
                        if (!documentSnapshot.exists()) return@addSnapshotListener

                        val avatarStr = documentSnapshot.getString(Constant.FIREBASE_USER_AVATAR)
                        val name = documentSnapshot.getString(Constant.FIREBASE_USER_USERNAME)
                        if (avatarStr != ""){
                            Glide.with(context).load(avatarStr)
                                    .asBitmap()
                                    .into(avatar)
                        }
                        title.text = name

                    }
            lastMessage.text = t.lastMessage
        }

        override fun onClick(v: View?) {
            listener.onClick(v, adapterPosition)
        }

    }



}