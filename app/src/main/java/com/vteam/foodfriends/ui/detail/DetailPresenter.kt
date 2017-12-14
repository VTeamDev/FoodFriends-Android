package com.vteam.foodfriends.ui.detail

import android.content.Context
import com.vteam.foodfriends.data.model.Comment
import com.vteam.foodfriends.data.model.Restaurant
import com.vteam.foodfriends.data.remote.FirebaseRestaurantService
import com.vteam.foodfriends.ui.base.BasePresenter
import com.vteam.foodfriends.ui.base.BaseView
import com.vteam.foodfriends.utils.Constant

/**
 * Created by H2PhySicS on 12/13/2017.
 */
class DetailPresenter(val context: Context,
                      val view : DetailContract.View) : DetailContract.Presenter{

    val mRestService = FirebaseRestaurantService(context)

    override fun start() {

    }

    override fun destroy() {

    }

    override fun getRestaurant(id: String) {

        mRestService.getRestaurant(id)
                .addSnapshotListener{documentSnapshot, firebaseFirestoreException ->
                    val address : String? = documentSnapshot.getString(Constant.FIREBASE_PRODUCT_ADDRESS)
                    val rating : Long? = documentSnapshot.getLong(Constant.FIREBASE_PRODUCT_AVERATE_RATING)
                    val lat : Double? = documentSnapshot.getDouble(Constant.FIREBASE_PRODUCT_LAT)
                    val lon : Double? = documentSnapshot.getDouble(Constant.FIREBASE_PRODUCT_LON)
                    val name : String? = documentSnapshot.getString(Constant.FIREBASE_PRODUCT_NAME)
                    val commmentList : ArrayList<Map<String, Any>> = documentSnapshot.get(Constant.FIREBASE_PRODUCT_REVIEWS) as ArrayList<Map<String, Any>>
                    val offers : Map<String, Long> = documentSnapshot.get(Constant.FIREBASE_PRODUCT_DISCOUNT) as Map<String, Long>
                    val discount = longArrayOf(offers.get(Constant.FIREBASE_PRODUCT_DISCOUNT_GROUP)!!, offers.get(Constant.FIREBASE_PRODUCT_DISCOUNT_PAIR)!!)
                    val photoUrl : String? = documentSnapshot.getString(Constant.FIREBASE_PRODUCT_PHOTOURL)
                    val timeClose : String? = documentSnapshot.getString(Constant.FIREBASE_PRODUCT_TIME_CLOSE)
                    val timeOpen : String? = documentSnapshot.getString(Constant.FIREBASE_PRODUCT_TIME_OPEN)
                    val comments = ArrayList<Comment>()
                    for (m : Map<String, Any> in commmentList){
                        val author : String? = m[Constant.FIREBASE_COMMENT_AUTHOR] as String?
                        val title : String? = m[Constant.FIREBASE_COMMENT_TITLE] as String?
                        val time : String? = m[Constant.FIREBASE_COMMENT_TIME] as String?
                        val content : String? = m[Constant.FIREBASE_COMMENT_CONTENT] as String?
                        val rating : Long? = m[Constant.FIREBASE_COMMENT_RATING] as Long?
                        comments.add(Comment(author!!, content!!, rating!!, time!!, title))
                    }
                    val rest = Restaurant(documentSnapshot.id, name, address, photoUrl, timeOpen, timeClose, rating, discount, lat, lon, comments)

                    view.showRestDetail(rest)
                    view.showComments(comments)

        }
    }

}