package com.vteam.foodfriends.data.preferences

import android.content.Context
import android.content.SharedPreferences
import com.vteam.foodfriends.utils.Constant

/**
 * Created by H2PhySicS on 12/28/2017.
 */
class AppPreferences(val context : Context){
    private var mPref : SharedPreferences = context.getSharedPreferences(Constant.PREF_NAME, Context.MODE_PRIVATE)
    private var mEditor : SharedPreferences.Editor = mPref.edit()

    fun setToken(token : String, expire : Long){
        mEditor.putString(Constant.PREF_TOKEN, token)
        mEditor.putLong(Constant.PREF_TOKEN_EXPIRE, expire)
        mEditor.commit()
    }

    fun clearToken(){
        mEditor.putString(Constant.PREF_TOKEN, null)
        mEditor.putInt(Constant.PREF_TOKEN_EXPIRE, -1)
        mEditor.commit()
    }
}