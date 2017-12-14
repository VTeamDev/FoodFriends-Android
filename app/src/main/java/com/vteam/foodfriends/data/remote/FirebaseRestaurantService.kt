package com.vteam.foodfriends.data.remote

import android.content.Context
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.vteam.foodfriends.utils.Constant

/**
 * Created by H2PhySicS on 12/11/2017.
 */
class FirebaseRestaurantService(context: Context){
    lateinit var mDatabase : FirebaseFirestore
    init {
        mDatabase  = FirebaseFirestore.getInstance()
    }

    fun getRestaurants() : CollectionReference {
        return mDatabase.collection(Constant.FIREBASE_PRODUCT)
    }

    fun getRestaurant(id : String) : DocumentReference{
        return mDatabase.collection(Constant.FIREBASE_PRODUCT)
                .document(id)
    }


}