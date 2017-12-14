package com.vteam.foodfriends.ui.chat

import android.content.Context
import com.google.firebase.firestore.DocumentSnapshot
import com.vteam.foodfriends.data.model.Message
import com.vteam.foodfriends.data.remote.FirebaseMessageService
import com.vteam.foodfriends.data.remote.FirebasePendingService
import com.vteam.foodfriends.data.remote.FirebaseUserService
import com.vteam.foodfriends.utils.AppUtils
import com.vteam.foodfriends.utils.Constant

/**
 * Created by H2PhySicS on 12/14/2017.
 */
class ChatPresenter(val context: Context,
                    val view : ChatContract.View) : ChatContract.Presenter{
    private val LOG_TAG = ChatPresenter::class.java.simpleName
    private val mMessageService = FirebaseMessageService(context)
    private val mUserService = FirebaseUserService(context)
    private val mPendingService = FirebasePendingService(context)
    private val utils = AppUtils(context)

    init {
        view.presenter = this
    }

    override fun start() {

    }

    override fun destroy() {

    }

    override fun getChatList(boxId: String) {
        mMessageService.getMessages(boxId)
                .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
                    val messList = ArrayList<Message>()
                    if (!querySnapshot.isEmpty){
                        for (document : DocumentSnapshot in querySnapshot){
                            val content : String = document.getString(Constant.FIREBASE_MESSAGE_MESSAGES_MESSAGE)
                            val uid : String = document.getString(Constant.FIREBASE_MESSAGE_MESSAGES_NAME)
                            val mess = Message(uid, content)
                            messList.add(mess)
                        }
                    }

                    view.showChatList(messList)
                }
    }

    override fun sendMessage(boxId: String, message: String) {
        val firebaseUser = mUserService.getCurrentFirebaseUser()

        val mess = Message(firebaseUser!!.uid, message)
        mMessageService.sendMessage(boxId, mess)
        mMessageService.updateChatRoom(boxId, mess)
        mPendingService.updateMember(boxId, firebaseUser.uid)
    }

}