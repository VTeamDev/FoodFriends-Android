package com.vteam.foodfriends.ui.waiting_single

import android.content.Context
import com.google.firebase.firestore.DocumentSnapshot
import com.vteam.foodfriends.data.model.Pending
import com.vteam.foodfriends.data.remote.FirebasePendingService
import com.vteam.foodfriends.data.remote.FirebaseUserService
import com.vteam.foodfriends.utils.Constant

/**
 * Created by H2PhySicS on 12/13/2017.
 */
class WaitingSinglePresenter(val context: Context,
                             val view : WaitingSingleContract.View) : WaitingSingleContract.Presenter{
    val mUserService = FirebaseUserService(context)
    val mPendingService = FirebasePendingService(context)

    init {
        view.presenter = this
    }

    override fun start() {

    }

    override fun destroy() {

    }

    override fun getPairs(resId: String) {
        mPendingService.getPairs(resId)
                .addSnapshotListener{querySnapshot, firebaseFirestoreException ->

                val pendings = ArrayList<Pending>()
                for (documentSnapshot : DocumentSnapshot in querySnapshot){
                    val uid : String = documentSnapshot.getString(Constant.FIREBASE_PENDING_USER_CREATED_ID)
                    val time : String = documentSnapshot.getString(Constant.FIREBASE_PENDING_TIME)
                    val lat : Double = documentSnapshot.getDouble(Constant.FIREBASE_PENDING_LAT)
                    val lon : Double = documentSnapshot.getDouble(Constant.FIREBASE_PENDING_LON)
                    val memberId : ArrayList<String> = documentSnapshot.get(Constant.FIREBASE_PENDING_MEMBERS) as ArrayList<String>

                    val pending = Pending(documentSnapshot.id, uid, time, lat, lon, memberId)
                    pendings.add(pending)
                }
                view.showPairs(pendings)
        }
    }

}