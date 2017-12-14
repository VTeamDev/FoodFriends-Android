package com.vteam.foodfriends.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.vteam.foodfriends.R
import com.vteam.foodfriends.data.model.Pending
import com.vteam.foodfriends.data.remote.FirebaseUserService
import com.vteam.foodfriends.ui.base.BaseAdapter
import com.vteam.foodfriends.ui.base.BaseViewHolder
import com.vteam.foodfriends.utils.AppUtils
import com.vteam.foodfriends.utils.Constant
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by H2PhySicS on 12/13/2017.
 */
class WaitingGroupAdapter(context: Context) : BaseAdapter<Pending>(context) {
    val mUserService = FirebaseUserService(context)
    val utils = AppUtils(context)
    lateinit var listener : WaitingSingleAdapter.OnItemClickListener
    init {
        contentView = R.layout.item_waiting_group
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Pending>?, position: Int) {
        val pending = mList!![position]
        (holder as WaitingGroupViewHolder).bind(pending)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<Pending> {
        val view : View = LayoutInflater.from(context).inflate(contentView!!, parent, false)
        return WaitingGroupViewHolder(view)
    }

    inner class WaitingGroupViewHolder(itemView : View) : BaseViewHolder<Pending>(itemView), View.OnClickListener {
        val avatar = itemView.findViewById<CircleImageView>(R.id.avatar)
        val groupName = itemView.findViewById<TextView>(R.id.groupName)
        val time = itemView.findViewById<TextView>(R.id.time)
        val members = itemView.findViewById<TextView>(R.id.members)

        override fun bind(t: Pending) {
            mUserService.getUser(t.uid)
                    .addSnapshotListener{documentSnapshot, firebaseFirestoreException ->

                        val avatarStr : String = documentSnapshot.getString(Constant.FIREBASE_USER_AVATAR)
                        val dob : String = documentSnapshot.getString(Constant.FIREBASE_USER_DOB)
                        val genderValue : Boolean = documentSnapshot.getBoolean(Constant.FIREBASE_USER_GENDER)
                        val phone : String = documentSnapshot.getString(Constant.FIREBASE_USER_PHONE)
                        val name : String = documentSnapshot.getString(Constant.FIREBASE_USER_USERNAME)

                        if (avatarStr != ""){
                            Glide.with(context).load(avatarStr).asBitmap().into(avatar)
                        }
                        groupName.text = name


                    }
            members.text = t.members.size.toString()
            time.text = t.time
        }

        override fun onClick(v: View?) {
            listener.onClick(v, adapterPosition)
        }

    }

}