package com.vteam.foodfriends.data.model

/**
 * Created by H2PhySicS on 12/13/2017.
 */
class Pending(val uid : String,
              val time : String,
              val lat : Double,
              val lon : Double,
              val members : ArrayList<String>){

    lateinit var boxId : String

    constructor(boxId : String,
                uid: String,
                time: String,
                lat: Double,
                lon: Double,
                members: ArrayList<String>) : this(uid, time, lat, lon, members){
        this.boxId = boxId
    }
}