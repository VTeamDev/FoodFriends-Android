package com.vteam.foodfriends.ui.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.vteam.foodfriends.ui.login.LoginActivity

class SplashActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
    }
}
