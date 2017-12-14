package com.vteam.foodfriends.ui.test

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.vteam.foodfriends.R
import com.vteam.foodfriends.data.model.Message
import com.vteam.foodfriends.data.remote.FirebaseMessageService
import com.vteam.foodfriends.ui.base.BaseActivity

class TestActivity : BaseActivity() {

    val mMessageService = FirebaseMessageService(this)

    init {
        contentView = R.layout.activity_chat
    }

    override fun initView() {

    }

    override fun initData() {

        mMessageService.getMessages("gUON80Q8wjYMh0okogtr")
                .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    Log.e("TestActivity", querySnapshot.isEmpty.toString())
                }

        val message = Message("v1ewYqwbgnduUqCMHgQFKX4G6au2", "Hello")
        mMessageService.sendMessage("gUON80Q8wjYMh0okogtr", message)
                .addOnSuccessListener { documentReference ->
                    Log.e("TestActivity", documentReference.id)
                }


    }

}
