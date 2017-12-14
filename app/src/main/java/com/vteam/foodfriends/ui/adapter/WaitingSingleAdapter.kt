package com.vteam.foodfriends.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.vteam.foodfriends.R
import com.vteam.foodfriends.data.model.Pending
import com.vteam.foodfriends.data.model.User
import com.vteam.foodfriends.data.remote.FirebaseUserService
import com.vteam.foodfriends.ui.base.BaseAdapter
import com.vteam.foodfriends.ui.base.BaseViewHolder
import com.vteam.foodfriends.utils.AppUtils
import com.vteam.foodfriends.utils.Constant
import de.hdodenhof.circleimageview.CircleImageView

/**
 * Created by H2PhySicS on 12/13/2017.
 */
class WaitingSingleAdapter(context : Context) : BaseAdapter<Pending>(context) {
    val mUserService = FirebaseUserService(context)
    val utils = AppUtils(context)
    lateinit var listener : OnItemClickListener
    init {
        contentView = R.layout.item_waiting_single
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<Pending> {
        val view : View = LayoutInflater.from(context).inflate(contentView!!, parent, false)
        return WaitingSingleViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Pending>?, position: Int) {
        val pending = mList!![position]

        (holder as WaitingSingleViewHolder).bind(pending)
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    inner class WaitingSingleViewHolder(itemView : View) : BaseViewHolder<Pending>(itemView), View.OnClickListener {

        val avatar = itemView.findViewById<CircleImageView>(R.id.avatar)
        val username = itemView.findViewById<TextView>(R.id.username)
        val gender = itemView.findViewById<ImageView>(R.id.gender)
        val age = itemView.findViewById<TextView>(R.id.age)
        val time = itemView.findViewById<TextView>(R.id.time)
        val distance = itemView.findViewById<TextView>(R.id.distance)
        val messenger = itemView.findViewById<ImageView>(R.id.messenger)

        override fun bind(t: Pending) {
            time.text = t.time
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
                        username.text = name
                        if (genderValue){
                            gender.setImageDrawable(context.getDrawable(R.drawable.male_orange))
                        } else {
                            gender.setImageDrawable(context.getDrawable(R.drawable.female_orange))
                        }
                        age.text = utils.getAge(dob).toString()

                    }
            messenger.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onClick(v, adapterPosition)
        }
    }

    interface OnItemClickListener{
        fun onClick(view : View?, position: Int)
    }

}