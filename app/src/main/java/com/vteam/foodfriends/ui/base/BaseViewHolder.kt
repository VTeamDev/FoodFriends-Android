package com.vteam.foodfriends.ui.base

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by H2PhySicS on 12/11/2017.
 */
abstract public class BaseViewHolder<T>(val itemView : View) : RecyclerView.ViewHolder(itemView) {
    abstract public fun bind(t : T)
}