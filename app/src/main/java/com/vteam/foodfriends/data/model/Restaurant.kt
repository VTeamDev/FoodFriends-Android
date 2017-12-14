package com.vteam.foodfriends.data.model

import kotlin.properties.Delegates

/**
 * Created by H2PhySicS on 12/11/2017.
 */
class Restaurant(val id : String,
                 val name: String?,
                 val address : String?,
                 val photoUrl : String?,
                 val timeOpen : String?,
                 val timeClose : String?,
                 val rating : Long?,
                 val discount : LongArray?,
                 val lat : Double?,
                 val lon : Double?){

    var comments : ArrayList<Comment> by Delegates.notNull()

    constructor(id : String,
                name: String?,
                address : String?,
                photoUrl : String?,
                timeOpen : String?,
                timeClose : String?,
                rating : Long?,
                discount : LongArray?,
                lat : Double?,
                lon : Double?,
                comments : ArrayList<Comment>) : this(id, name, address, photoUrl, timeOpen, timeClose, rating, discount, lat, lon){
        this.comments = comments
    }

}
