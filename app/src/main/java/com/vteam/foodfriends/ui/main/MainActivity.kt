package com.vteam.foodfriends.ui.main

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceOnClickListener

import com.vteam.foodfriends.R
import com.vteam.foodfriends.ui.base.BaseActivity
import com.vteam.foodfriends.ui.home.HomeFragment
import com.vteam.foodfriends.ui.messenger.MessengerActivity
import com.vteam.foodfriends.utils.ScreenManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainContract.View {
    override lateinit var presenter: MainContract.Presenter


    init {
        contentView = R.layout.activity_main
    }

    override fun initView() {
        ScreenManager.openFragment(supportFragmentManager, HomeFragment(), R.id.contentLayout)
        navigation.addSpaceItem(SpaceItem("", R.drawable.home))
        navigation.addSpaceItem(SpaceItem("", R.drawable.heart))
        navigation.addSpaceItem(SpaceItem("", R.drawable.bell))
        navigation.addSpaceItem(SpaceItem("", R.drawable.menu))
        navigation.changeCenterButtonIcon(R.drawable.messenger)

        navigation.setSpaceOnClickListener(object : SpaceOnClickListener {
            override fun onCentreButtonClick() {
                openMessenger()
            }

            override fun onItemReselected(itemIndex: Int, itemName: String?) {

            }

            override fun onItemClick(itemIndex: Int, itemName: String?) {
                when(itemIndex){
                    0 -> ScreenManager.openFragment(supportFragmentManager, HomeFragment(), R.id.contentLayout)
                    1 -> ScreenManager.openFragment(supportFragmentManager, HomeFragment(), R.id.contentLayout)
                    2 -> ScreenManager.openFragment(supportFragmentManager, HomeFragment(), R.id.contentLayout)
                    3 -> ScreenManager.openFragment(supportFragmentManager, HomeFragment(), R.id.contentLayout)
                }
            }

        })
    }

    override fun initData() {
        presenter = MainPresenter(this, this)
    }

    override fun openMessenger() {
        startActivityWithAnimation(Intent(this@MainActivity, MessengerActivity::class.java))
    }


}
