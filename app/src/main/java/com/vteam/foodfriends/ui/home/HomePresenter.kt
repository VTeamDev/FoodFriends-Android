package com.vteam.foodfriends.ui.home

import android.content.Context
import com.google.firebase.firestore.DocumentSnapshot
import com.vteam.foodfriends.data.model.Restaurant
import com.vteam.foodfriends.data.remote.FirebaseRestaurantService
import com.vteam.foodfriends.utils.Constant


/**
 * Created by H2PhySicS on 12/11/2017.
 */
class HomePresenter(val context: Context, val view : HomeContract.View) : HomeContract.Presenter {

    var mRestService : FirebaseRestaurantService
    init {
        view.presenter = this
        mRestService = FirebaseRestaurantService(context)
    }

    override fun start() {

    }

    override fun destroy() {

    }

    override fun loadRestaurants() {
        view.showLoadingIndicator(null)
        mRestService.getRestaurants().addSnapshotListener { querySnapshot, exception ->
            val rests : ArrayList<Restaurant> = ArrayList()
            for (documentSnapshot : DocumentSnapshot in querySnapshot){
                val address : String? = documentSnapshot.getString(Constant.FIREBASE_PRODUCT_ADDRESS)
                val rating : Long? = documentSnapshot.getLong(Constant.FIREBASE_PRODUCT_AVERATE_RATING)
                val lat : Double? = documentSnapshot.getDouble(Constant.FIREBASE_PRODUCT_LAT)
                val lon : Double? = documentSnapshot.getDouble(Constant.FIREBASE_PRODUCT_LON)
                val name : String? = documentSnapshot.getString(Constant.FIREBASE_PRODUCT_NAME)
                val offers : Map<String, Long> = documentSnapshot.get(Constant.FIREBASE_PRODUCT_DISCOUNT) as Map<String, Long>
                val discount = longArrayOf(offers.get(Constant.FIREBASE_PRODUCT_DISCOUNT_GROUP)!!, offers.get(Constant.FIREBASE_PRODUCT_DISCOUNT_PAIR)!!)
                val photoUrl : String? = documentSnapshot.getString(Constant.FIREBASE_PRODUCT_PHOTOURL)
                val timeClose : String? = documentSnapshot.getString(Constant.FIREBASE_PRODUCT_TIME_CLOSE)
                val timeOpen : String? = documentSnapshot.getString(Constant.FIREBASE_PRODUCT_TIME_OPEN)
                rests.add(Restaurant(documentSnapshot.id, name, address, photoUrl, timeOpen, timeClose, rating, discount, lat, lon))
            }
            view.showRestaurants(rests)
            view.hideLoadingIndicator()
        }
    }

    override fun follow() {

    }

    override fun direct() {

    }
}