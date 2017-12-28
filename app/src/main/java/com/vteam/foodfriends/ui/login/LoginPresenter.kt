package com.vteam.foodfriends.ui.login

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.support.v4.app.ActivityCompat
import android.util.Log
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.vteam.foodfriends.Manifest
import com.vteam.foodfriends.R
import com.vteam.foodfriends.data.preferences.AppPreferences
import com.vteam.foodfriends.data.remote.Authentication
import com.vteam.foodfriends.data.remote.Callback
import com.vteam.foodfriends.data.remote.FirebaseUserService
import com.vteam.foodfriends.utils.AppUtils

/**
 * Created by H2PhySicS on 12/11/2017.
 */
class LoginPresenter(val context: Context,
                     val view : LoginContract.View) : LoginContract.Presenter {
    val LOG_TAG : String = LoginPresenter::class.java.simpleName
    val authentication = Authentication()
    val utils = AppUtils(context)
    val pref = AppPreferences(context)
    init {
        view.presenter = this
    }


    override fun start() {

    }

    override fun destroy() {

    }

    override fun login(email: String, password: String) {
        view.showLoadingIndicator(context.getString(R.string.signing_in))
        authentication.login(email, password, object : Callback<LoginMutation.AuthResponse>{
            override fun onSuccess(t: LoginMutation.AuthResponse) {
                pref.setToken(t.token(), t.expiresIn())
                view.hideLoadingIndicator()
                view.openMain()
            }

            override fun onFailed() {
                view.hideLoadingIndicator()
                view.showAlertError(context.getString(R.string.error_login_invalidate_title), context.getString(R.string.error_login_unsuccessful))
            }

        })
    }

    override fun validateInput(email: String, password: String) : Boolean {
        if (!utils.isEmail(email) || !utils.isPassword(password)){
            return false
        }

        return true
    }

}