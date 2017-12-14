package com.vteam.foodfriends.ui.base

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView

/**
 * Created by H2PhySicS on 12/11/2017.
 */
abstract class BaseAdapter<T> constructor(val context : Context) : RecyclerView.Adapter<BaseViewHolder<T>>() {
    protected var mList : ArrayList<T>? = null

    @LayoutRes
    var contentView : Int? = null

    init {
        mList = ArrayList()
    }

    override fun getItemCount(): Int {return mList!!.size}

    fun add(t : T){
        mList?.add(t)
        notifyDataSetChanged()
    }

    fun addAll(list : ArrayList<T>?) {
        mList?.addAll(list!!)
        notifyDataSetChanged()
    }

    fun clear(){
        mList?.clear()
        notifyDataSetChanged()
    }

    fun resetAll(list: ArrayList<T>?){
        if (mList == null) return
        mList?.clear()
        mList?.addAll(list!!)
    }

    fun getData() : ArrayList<T> {
        return mList!!;
    }


}