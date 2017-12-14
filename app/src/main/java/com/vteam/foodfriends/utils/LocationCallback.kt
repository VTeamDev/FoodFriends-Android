package com.vteam.foodfriends.utils

import android.location.Location

/**
 * Created by H2PhySicS on 12/13/2017.
 */
interface LocationCallback{
    fun onSuccess(location: Location?)
}