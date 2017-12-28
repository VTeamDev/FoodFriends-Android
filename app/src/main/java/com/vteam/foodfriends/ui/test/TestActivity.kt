package com.vteam.foodfriends.ui.test

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.FieldValue
import com.vteam.foodfriends.R
import com.vteam.foodfriends.data.model.Message
import com.vteam.foodfriends.data.remote.Authentication
import com.vteam.foodfriends.data.remote.Callback
import com.vteam.foodfriends.data.remote.FirebaseMessageService
import com.vteam.foodfriends.ui.base.BaseActivity

class TestActivity : BaseActivity() {
    private val LOG_TAG = TestActivity::class.java.simpleName
    val mMessageService = FirebaseMessageService(this)

    init {
        contentView = R.layout.activity_login
    }

    override fun initView() {

    }

    override fun initData() {
        //
    }

}
