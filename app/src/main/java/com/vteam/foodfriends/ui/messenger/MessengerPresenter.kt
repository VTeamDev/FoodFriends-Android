package com.vteam.foodfriends.ui.messenger

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.vteam.foodfriends.data.model.ChatRoom
import com.vteam.foodfriends.data.remote.FirebaseMessageService
import com.vteam.foodfriends.data.remote.FirebaseUserService
import com.vteam.foodfriends.utils.AppUtils
import com.vteam.foodfriends.utils.Constant
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by H2PhySicS on 12/14/2017.
 */
class MessengerPresenter(val context: Context,
                         val view : MessengerContract.View) : MessengerContract.Presenter{
    private val LOG_TAG = MessengerPresenter::class.java.simpleName
    val mUserService = FirebaseUserService(context)
    val mMessageService = FirebaseMessageService(context)
    val utils = AppUtils(context)
    val mDatabase = FirebaseFirestore.getInstance()

    override fun start() {

    }

    override fun destroy() {

    }

    override fun getChatRooms() {
        val firebaseUser = mUserService.getCurrentFirebaseUser()
        mDatabase.collection(Constant.FIREBASE_MESSAGE).document(Constant.FIREBASE_MESSAGE)
                .collection(Constant.FIREBASE_MESSAGE_MEMBERS)
                .addSnapshotListener { querySnapshot, exception ->
                    val listRoomId = ArrayList<String>()

                    for (document : DocumentSnapshot in querySnapshot){
                        val list : ArrayList<String> = document.get(Constant.FIREBASE_MESSAGE_MEMBERS_LIST) as ArrayList<String>
                        if (list.contains(firebaseUser!!.uid)) listRoomId.add(document.id)
                    }

                    Log.e(LOG_TAG, listRoomId.size.toString())

                    val rooms = ArrayList<ChatRoom>()

                    mDatabase.collection(Constant.FIREBASE_MESSAGE).document(Constant.FIREBASE_MESSAGE)
                            .collection(Constant.FIREBASE_MESSAGE_CHAT)
                            .addSnapshotListener { querySnapshot, exception ->
                                if (querySnapshot.isEmpty) return@addSnapshotListener

                                for (document : DocumentSnapshot in querySnapshot){
                                    if (listRoomId.contains(document.id)){
                                        val title : String = document.getString(Constant.FIREBASE_MESSAGE_CHAT_TITLE)
                                        val lastMessage : String = document.getString(Constant.FIREBASE_MESSAGE_CHAT_LAST_MESSAGE)
//                                        val time : Date? = document.get(Constant.FIREBASE_MESSAGE_CHAT_TIMESTAMP) as Date
                                        val room = ChatRoom(document.id, title, lastMessage)
                                        rooms.add(room)
                                    }
                                }

                                view.showChatRooms(rooms)

                            }

                }


//        mMessageService.getChatRoom(firebaseUser!!.uid)
//                .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
//                    if (querySnapshot.isEmpty) return@addSnapshotListener
//
//                    val listRoomId = ArrayList<String>()
//
//                    for (document : DocumentSnapshot in querySnapshot){
//                        val list : ArrayList<String> = document.get(Constant.FIREBASE_MESSAGE_MEMBERS_LIST) as ArrayList<String>
//                        if (list.contains(firebaseUser.uid)) listRoomId.add(document.id)
//                    }
//
//
//
//                    val rooms = ArrayList<ChatRoom>()
//
//                    for (document : DocumentSnapshot in querySnapshot){
//                        val title : String = document.getString(Constant.FIREBASE_MESSAGE_CHAT_TITLE)
//                        val lastMessage : String = document.getString(Constant.FIREBASE_MESSAGE_CHAT_LAST_MESSAGE)
//                        val time : Date = document.get(Constant.FIREBASE_MESSAGE_CHAT_TIMESTAMP) as Date
//                        val room = ChatRoom(title, lastMessage)
//                        rooms.add(room)
//                    }
//
//                    view.showChatRooms(rooms)
//                }

    }

}