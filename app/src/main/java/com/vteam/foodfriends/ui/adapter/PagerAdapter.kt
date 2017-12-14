package com.vteam.foodfriends.ui.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.content.ContextCompat
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan
import com.vteam.foodfriends.R
import com.vteam.foodfriends.ui.waiting_group.WaitingGroupFragment
import com.vteam.foodfriends.ui.waiting_single.WaitingSingleFragment
import com.vteam.foodfriends.utils.Constant

/**
 * Created by H2PhySicS on 12/13/2017.
 */
class PagerAdapter(val context: Context,
                   fragmentManager: FragmentManager,
                   val resId : String) : FragmentStatePagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        var fragment : Fragment? = null
        val bundle = Bundle()
        bundle.putString(Constant.EXTRA_RESTAURANT_ID, resId)

        when(position){
            0 -> {
                fragment = WaitingSingleFragment()
                fragment.arguments = bundle
            }
            1 -> {
                fragment = WaitingGroupFragment()
                fragment.arguments = bundle
            }
        }
        return fragment!!
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var text : String? = null

        var drawable : Drawable? = null
        when(position){
            0 -> {
                drawable = ContextCompat.getDrawable(context, R.drawable.user)
                text = "Single"
            }
            1 -> {
                drawable = ContextCompat.getDrawable(context, R.drawable.group)
                text = "Group"
            }
        }

        val sb = SpannableStringBuilder("   ")
        drawable?.setBounds(5, 5, drawable.intrinsicWidth, drawable.intrinsicHeight)
        val span = ImageSpan(drawable, DynamicDrawableSpan.ALIGN_BASELINE)
        sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        return text
    }

}