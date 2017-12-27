package com.vteam.foodfriends.data.model

/**
 * Created by H2PhySicS on 12/12/2017.
 */
data class User(val id : String,
           val name : String,
           val phone : String,
           val dob : String,
           val avatar : String?,
           val gender : Boolean){

    constructor(id : String,
                email : String,
                password : String?,
                name : String,
                phone : String,
                dob : String,
                avatar : String?,
                gender : Boolean) : this(id, name, phone, dob, avatar, gender){

    }

    constructor(id : String,
                email : String,
                password : String?,
                name : String,
                phone : String,
                dob : String,
                avatar : String?,
                gender : Boolean,
                follows : ArrayList<String>) : this(id, email, password, name, phone, dob, avatar, gender)

}