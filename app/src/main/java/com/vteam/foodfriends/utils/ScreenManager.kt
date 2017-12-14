package com.vteam.foodfriends.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction

/**
 * Created by H2PhySicS on 12/11/2017.
 */
class ScreenManager{
    companion object {
        public fun openFragment(fragmentManager: FragmentManager, fragment : Fragment, layoutId : Int){
            val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(layoutId, fragment)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

//        @SuppressLint("ResourceType")
//        public fun replaceFragment(fragmentManager: FragmentManager, fragment: Fragment, @LayoutRes layoutId: Int){
//            val fragmentTransaction : FragmentTransaction = fragmentManager.beginTransaction()
//            fragmentTransaction.addToBackStack(fragment::class.java.simpleName)
//            fragment.onCreateAnimation()
//            fragmentTransaction.commit()
//        }
    }


}