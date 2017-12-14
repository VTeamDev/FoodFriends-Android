package com.vteam.foodfriends.ui.waiting

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.View
import com.vteam.foodfriends.R
import com.vteam.foodfriends.ui.adapter.PagerAdapter
import com.vteam.foodfriends.ui.base.BaseActivity
import com.vteam.foodfriends.utils.Constant
import kotlinx.android.synthetic.main.activity_waiting.*

class WaitingActivity : BaseActivity(), WaitingContract.View, View.OnClickListener {
    private val LOG_TAG = WaitingActivity::class.java.simpleName

    override lateinit var presenter: WaitingContract.Presenter
    private lateinit var mAdapter : PagerAdapter
    private var id : String? = null
    init {
        contentView = R.layout.activity_waiting
    }

    override fun initView() {
        tab.setupWithViewPager(pager)
        pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab))
    }

    override fun initData() {
        presenter = WaitingPresenter(this, this)
        val intent = intent
        id = intent.getStringExtra(Constant.EXTRA_RESTAURANT_ID)

        mAdapter = PagerAdapter(this, supportFragmentManager, id!!)

        pager.adapter = mAdapter
        skip.setOnClickListener(this)
        back.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun showCreateDialog() {
        val dialogReserve = ReserveDialog(this, object : ReserveDialog.OnClickListener{
            override fun onClick(time: String, isSingle: Boolean) {
                presenter.createPending(id!!, time, isSingle)
            }
        })

        dialogReserve.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogReserve.show()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.skip -> {
                showCreateDialog()
            }
            R.id.back -> {
                onBackPressed()
                finish()
            }
        }
    }


}
