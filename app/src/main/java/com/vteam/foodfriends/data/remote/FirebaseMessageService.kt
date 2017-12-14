package com.vteam.foodfriends.data.remote

import android.content.Context
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import com.vteam.foodfriends.data.model.Message
import com.vteam.foodfriends.data.model.Pending
import com.vteam.foodfriends.utils.Constant

/**
 * Created by H2PhySicS on 12/14/2017.
 */
class FirebaseMessageService(context: Context){
    private var mDatabase = FirebaseFirestore.getInstance()

    fun getMessages(boxId : String) : Query{
        return mDatabase.collection(Constant.FIREBASE_MESSAGE).document(Constant.FIREBASE_MESSAGE)
                .collection(Constant.FIREBASE_MESSAGE_MESSAGES)
                .document(boxId)
                .collection(Constant.FIREBASE_MESSAGE_MESSAGES_CONTENT)
                .orderBy(Constant.FIREBASE_MESSAGE_MESSAGES_TIMESTAMP)
    }

    fun sendMessage(boxId: String, message: Message) : Task<DocumentReference> {
        val map = HashMap<String, Any>()
        map.put(Constant.FIREBASE_MESSAGE_MESSAGES_MESSAGE, message.content)
        map.put(Constant.FIREBASE_MESSAGE_MESSAGES_NAME, message.uid)
        map.put(Constant.FIREBASE_MESSAGE_MESSAGES_TIMESTAMP, FieldValue.serverTimestamp())
        return mDatabase.collection(Constant.FIREBASE_MESSAGE).document(Constant.FIREBASE_MESSAGE)
                .collection(Constant.FIREBASE_MESSAGE_MESSAGES)
                .document(boxId)
                .collection(Constant.FIREBASE_MESSAGE_MESSAGES_CONTENT)
                .add(map)

    }

    fun updateChatRoom(boxId: String, message: Message){
        val map = HashMap<String, Any>()
        map.put(Constant.FIREBASE_MESSAGE_CHAT_LAST_MESSAGE, message.content)
        map.put(Constant.FIREBASE_MESSAGE_CHAT_TITLE, message.uid)
        map.put(Constant.FIREBASE_MESSAGE_CHAT_TIMESTAMP, FieldValue.serverTimestamp())

        mDatabase.collection(Constant.FIREBASE_MESSAGE).document(Constant.FIREBASE_MESSAGE)
                .collection(Constant.FIREBASE_MESSAGE_CHAT)
                .document(boxId)
                .set(map)

    }

    fun getChatRoomId(uid : String){
        mDatabase.collection(Constant.FIREBASE_MESSAGE)
                .document(Constant.FIREBASE_MESSAGE)
                .collection(Constant.FIREBASE_MESSAGE_MEMBERS)

    }

    fun getChatRoom(uid : String) : CollectionReference{
        return mDatabase.collection(Constant.FIREBASE_MESSAGE)
                .document(Constant.FIREBASE_MESSAGE)
                .collection(Constant.FIREBASE_MESSAGE_MEMBERS)

    }
}