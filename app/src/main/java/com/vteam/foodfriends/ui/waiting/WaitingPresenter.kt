package com.vteam.foodfriends.ui.waiting

import android.content.Context
import android.location.Location
import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.vteam.foodfriends.data.model.User
import com.vteam.foodfriends.data.remote.FirebasePendingService
import com.vteam.foodfriends.data.remote.FirebaseUserService
import com.vteam.foodfriends.utils.AppUtils
import com.vteam.foodfriends.utils.Constant
import com.vteam.foodfriends.utils.LocationCallback

/**
 * Created by H2PhySicS on 12/13/2017.
 */
class WaitingPresenter(val context: Context,
                       val view : WaitingContract.View) : WaitingContract.Presenter{
    val LOG_TAG = WaitingPresenter::class.java.simpleName
    val mUserService = FirebaseUserService(context)
    val mPendingService = FirebasePendingService(context)
    val utils = AppUtils(context)

    init {
        view.presenter = this
    }

    override fun start() {

    }

    override fun destroy() {

    }

    override fun createPending(resId : String, expectedTime: String, isSingle: Boolean) {
        val firebaseUser = mUserService.getCurrentFirebaseUser()

        utils.getLastLocation(context, object : LocationCallback{
            override fun onSuccess(location: Location?) {
                if (location == null) return

                if (isSingle){
                    mPendingService.createPair(resId, firebaseUser!!.uid, expectedTime, location.latitude, location.longitude)
                            .addOnSuccessListener { documentReference ->
                                mPendingService.createChatMembers(documentReference.id, firebaseUser.uid)
                            }
                } else {
                    mPendingService.createGroup(resId, firebaseUser!!.uid, expectedTime, location.latitude, location.longitude)
                            .addOnSuccessListener { documentReference ->
                                mPendingService.createChatMembers(documentReference.id, firebaseUser.uid)
                            }
                }

            }

        })

    }

}