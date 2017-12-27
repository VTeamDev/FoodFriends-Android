package com.vteam.foodfriends.data.remote

/**
 * Created by H2PhySicS on 12/28/2017.
 */
interface Callback<T> {
    fun onSuccess(t : T)
    fun onFailed()
}