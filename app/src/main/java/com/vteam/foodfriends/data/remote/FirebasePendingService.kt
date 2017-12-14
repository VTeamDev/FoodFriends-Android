package com.vteam.foodfriends.data.remote

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.vteam.foodfriends.data.model.User
import com.vteam.foodfriends.utils.Constant

/**
 * Created by H2PhySicS on 12/13/2017.
 */
class FirebasePendingService(context: Context){
    private val mDatabase = FirebaseFirestore.getInstance()

    fun getPairs(resId : String) : CollectionReference{
        return mDatabase.collection(Constant.FIREBASE_PENDING).document(resId).collection(Constant.FIREBASE_PENDING_PAIR)
    }

    fun getGroups(resId: String) : CollectionReference{
        return mDatabase.collection(Constant.FIREBASE_PENDING).document(resId).collection(Constant.FIREBASE_PENDING_GROUP)
    }

    fun createPair(resId: String, uid : String, expectedTime : String, lat : Double, lon : Double) : Task<DocumentReference>{
        val pairMap = HashMap<String, Any>()
        pairMap.put(Constant.FIREBASE_PENDING_USER_CREATED_ID, uid)
        pairMap.put(Constant.FIREBASE_PENDING_TIME, expectedTime)
        pairMap.put(Constant.FIREBASE_PENDING_LAT, lat)
        pairMap.put(Constant.FIREBASE_PENDING_LON, lon)
        val member = ArrayList<String>()
        member.add(uid)
        pairMap.put(Constant.FIREBASE_PENDING_MEMBERS, member)

        return mDatabase.collection(Constant.FIREBASE_PENDING).document(resId).collection(Constant.FIREBASE_PENDING_PAIR)
                .add(pairMap)
    }

    fun createGroup(resId: String, uid : String, expectedTime : String, lat : Double, lon : Double) : Task<DocumentReference>{
        val groupMap = HashMap<String, Any>()
        groupMap.put(Constant.FIREBASE_PENDING_USER_CREATED_ID, uid)
        groupMap.put(Constant.FIREBASE_PENDING_TIME, expectedTime)
        groupMap.put(Constant.FIREBASE_PENDING_LAT, lat)
        groupMap.put(Constant.FIREBASE_PENDING_LON, lon)
        val member = ArrayList<String>()
        member.add(uid)
        groupMap.put(Constant.FIREBASE_PENDING_MEMBERS, member)

        return mDatabase.collection(Constant.FIREBASE_PENDING).document(resId).collection(Constant.FIREBASE_PENDING_GROUP)
                .add(groupMap)
    }

    fun createChatRoom(boxId : String, uid : String){
        val map = HashMap<String, Any>()
        map.put(Constant.FIREBASE_MESSAGE_CHAT_LAST_MESSAGE, "")
        map.put(Constant.FIREBASE_MESSAGE_CHAT_TIMESTAMP, FieldValue.serverTimestamp())
        map.put(Constant.FIREBASE_MESSAGE_CHAT_TITLE, uid)
        mDatabase.collection(Constant.FIREBASE_MESSAGE).document(Constant.FIREBASE_MESSAGE)
                .collection(Constant.FIREBASE_MESSAGE_CHAT)
                .document(boxId)
                .set(map)

    }

    fun createChatMembers(boxId: String, uid: String){
        val map = HashMap<String, Any>()
        val members = ArrayList<String>()
        members.add(uid)
        map.put(Constant.FIREBASE_MESSAGE_MEMBERS_LIST, members)
        mDatabase.collection(Constant.FIREBASE_MESSAGE).document(Constant.FIREBASE_MESSAGE)
                .collection(Constant.FIREBASE_MESSAGE_MEMBERS)
                .document(boxId)
                .set(map)
    }

    fun updateMember(boxId: String, id : String){
        val task = mDatabase.collection(Constant.FIREBASE_MESSAGE).document(Constant.FIREBASE_MESSAGE)
                .collection(Constant.FIREBASE_MESSAGE_MEMBERS)
                .document(boxId)
                .get()
        task.addOnSuccessListener { documentSnapshot ->
            val list : ArrayList<String> = documentSnapshot.get(Constant.FIREBASE_MESSAGE_MEMBERS_LIST) as ArrayList<String>
            if (list.contains(id)) return@addOnSuccessListener

            list.add(id)
            val map = HashMap<String, Any>()

            map.put(Constant.FIREBASE_MESSAGE_MEMBERS_LIST, list)
            mDatabase.collection(Constant.FIREBASE_MESSAGE).document(Constant.FIREBASE_MESSAGE)
                    .collection(Constant.FIREBASE_MESSAGE_MEMBERS)
                    .document(boxId)
                    .set(map)
        }


    }

    fun createChatMessages(boxId: String){
        mDatabase.collection(Constant.FIREBASE_MESSAGE).document(Constant.FIREBASE_MESSAGE)
                .collection(Constant.FIREBASE_MESSAGE_MESSAGES)
                .document(boxId)
                .collection(Constant.FIREBASE_MESSAGE_MESSAGES_CONTENT)

    }

}