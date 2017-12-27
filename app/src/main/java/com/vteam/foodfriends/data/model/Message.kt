package com.vteam.foodfriends.data.model

/**
 * Created by H2PhySicS on 12/14/2017.
 */
data class Message(val uid : String,
              val content : String){

    constructor(uid: String,
                content: String,
                time : String) : this(uid, content)

}