package com.vteam.foodfriends.ui.register

import android.content.Context
import android.util.Log
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.vteam.foodfriends.R
import com.vteam.foodfriends.data.model.User
import com.vteam.foodfriends.data.remote.FirebaseUserService
import com.vteam.foodfriends.utils.AppUtils

/**
 * Created by H2PhySicS on 12/11/2017.
 */
class RegisterPresenter(val context: Context,
                        val view : RegisterContract.View) : RegisterContract.Presenter{
    val LOG_TAG = RegisterPresenter::class.java.simpleName
    val mUserService = FirebaseUserService(context)
    val utils = AppUtils(context)

    init {
        view.presenter = this
    }

    override fun start() {

    }

    override fun destroy() {

    }

    override fun register(email : String, password : String, name : String, phone : String, dob : String, gender : Boolean) {
        view.showLoadingIndicator(context.getString(R.string.signing_up))
        mUserService.createWithEmail(email, password)
                .addOnSuccessListener { auth ->
                    val firebaseUser : FirebaseUser? = mUserService.getCurrentFirebaseUser()
                    val user = User(firebaseUser!!.uid, email, password, name, phone, dob,  "", gender)
                    mUserService.insertUser(user)
                            .addOnCompleteListener{task: Task<Void> ->
                                Log.e(LOG_TAG, "Create successful")
                            }
                    view.openMain()
                    view.hideLoadingIndicator()

                }
                .addOnFailureListener {exception ->
                    view.hideLoadingIndicator()
                }
    }

    override fun validateInput(email: String?, username: String?, password: String?, passwordConfirm: String?, phone: String?, birthday: String?) : Boolean {
        var error = StringBuilder()
        if (!utils.isEmail(email!!)){
            error.append(context.getString(R.string.error_email_invalid) + "\n")
        }
        if (password!!.length < 6 || password.length > 32){
            error.append(context.getString(R.string.error_password_invalid) + "\n")
        }
        if (password != passwordConfirm!!){
            error.append(context.getString(R.string.error_password_confirm_invalid) + "\n")
        }
        if (!utils.isPhoneNumber(phone!!)){
            error.append(context.getString(R.string.error_phone_invalid) + "\n")
        }
        if (!utils.isBirthday(birthday!!)){
            error.append(context.getString(R.string.error_birthday_invalid) + "\n")
        }

        Log.e(LOG_TAG, "Error :" + error.toString())
        return true
    }

}