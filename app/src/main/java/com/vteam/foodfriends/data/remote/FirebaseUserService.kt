package com.vteam.foodfriends.data.remote

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.vteam.foodfriends.data.model.User
import com.vteam.foodfriends.utils.Constant
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by H2PhySicS on 12/11/2017.
 */
class FirebaseUserService(context: Context){
    private var mAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private var mDatabase : FirebaseFirestore = FirebaseFirestore.getInstance()

    fun signInWithEmail(email : String, password : String) : Task<AuthResult> {
        return mAuth.signInWithEmailAndPassword(email, password)
    }

    fun createWithEmail(email: String, password: String) : Task<AuthResult> {
        return mAuth.createUserWithEmailAndPassword(email, password)
    }

    fun getCurrentFirebaseUser() : FirebaseUser?{
        return mAuth.currentUser
    }

    fun getCurrentUser() : DocumentReference{
        val firebaseUser = getCurrentFirebaseUser()
        val id = firebaseUser?.uid
        val email = firebaseUser?.email
        return mDatabase.collection(Constant.FIREBASE_USER)
                .document(id!!)

    }

    fun insertUser(user : User) : Task<Void>{
        val userMap = HashMap<String, Any>()
        userMap.put(Constant.FIREBASE_USER_USERNAME, user.name)
        userMap.put(Constant.FIREBASE_USER_PHONE, user.phone)
        userMap.put(Constant.FIREBASE_USER_DOB, user.dob)
        userMap.put(Constant.FIREBASE_USER_GENDER, user.gender)
        userMap.put(Constant.FIREBASE_USER_AVATAR, user.avatar!!)

        return mDatabase.collection(Constant.FIREBASE_USER)
                .document(user.id)
                .set(userMap)
    }

    fun getUser(uid : String) : DocumentReference{
        return mDatabase.collection(Constant.FIREBASE_USER)
                .document(uid)
    }

    fun logout(){
        mAuth.signOut()
    }
}