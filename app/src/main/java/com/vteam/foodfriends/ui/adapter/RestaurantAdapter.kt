package com.vteam.foodfriends.ui.adapter

import android.content.Context
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.vteam.foodfriends.R
import com.vteam.foodfriends.data.model.Restaurant
import com.vteam.foodfriends.ui.base.BaseAdapter
import com.vteam.foodfriends.ui.base.BaseViewHolder

/**
 * Created by H2PhySicS on 12/11/2017.
 */
class RestaurantAdapter(context: Context) : BaseAdapter<Restaurant>(context) {
    private var listener : OnItemClickListener? = null
    init {
        contentView = R.layout.item_restaurant
    }

    override fun onBindViewHolder(holder: BaseViewHolder<Restaurant>?, position: Int) {
        val rest : Restaurant = mList!![position]
        (holder as RestaurantViewHolder).bind(rest)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder<Restaurant> {
        val view : View = LayoutInflater.from(context).inflate(contentView!!, parent, false)
        return RestaurantViewHolder(view)
    }

    inner class RestaurantViewHolder(view : View) : BaseViewHolder<Restaurant>(view), View.OnClickListener {
        var restPhoto : ImageView? = null
        var discount : TextView? = null
        var restName : TextView? = null
        var restAddress : TextView? = null
        var availableTime : TextView? = null
        var rating : TextView? = null
        var follow : TextView? = null
        var direct : TextView? = null
        var root : CardView? = null

        override fun onClick(v: View?) {
            listener!!.onItemClick(v, adapterPosition)
        }

        init {
            restPhoto = view.findViewById<ImageView>(R.id.restPhoto)
            discount = view.findViewById<TextView>(R.id.discount)
            restName = view.findViewById<TextView>(R.id.restName)
            restAddress = view.findViewById<TextView>(R.id.restAddress)
            availableTime = view.findViewById<TextView>(R.id.availableTime)
            rating = view.findViewById<TextView>(R.id.rating)
            follow = view.findViewById<TextView>(R.id.follow)
            direct = view.findViewById<TextView>(R.id.direct)
            root = view.findViewById<CardView>(R.id.cardView)
            root?.setOnClickListener(this)
            follow?.setOnClickListener(this)
            direct?.setOnClickListener(this)
        }

        override fun bind(t: Restaurant) {
            Glide.with(context)
                    .load(t.photoUrl)
                    .asBitmap()
                    .into(restPhoto)
            discount?.text = t.discount!![1].toString() + "%"
            restName?.text = t.name
            restAddress?.text = t.address
            rating?.text = t.rating.toString()

        }
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    interface OnItemClickListener{
        fun onItemClick(view : View?, position: Int)
    }

}