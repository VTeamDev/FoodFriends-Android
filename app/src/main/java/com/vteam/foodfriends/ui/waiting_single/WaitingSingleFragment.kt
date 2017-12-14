package com.vteam.foodfriends.ui.waiting_single

import android.content.Intent
import android.view.View
import com.vteam.foodfriends.R
import com.vteam.foodfriends.data.model.Pending
import com.vteam.foodfriends.ui.adapter.WaitingSingleAdapter
import com.vteam.foodfriends.ui.base.BaseActivity
import com.vteam.foodfriends.ui.base.BaseFragment
import com.vteam.foodfriends.ui.chat.ChatActivity
import com.vteam.foodfriends.utils.Constant
import kotlinx.android.synthetic.main.activity_waiting.*
import kotlinx.android.synthetic.main.fragment_waiting.*

/**
 * Created by H2PhySicS on 12/13/2017.
 */
class WaitingSingleFragment : BaseFragment(), WaitingSingleContract.View, View.OnClickListener, WaitingSingleAdapter.OnItemClickListener {

    override lateinit var presenter : WaitingSingleContract.Presenter

    private lateinit var mAdapter : WaitingSingleAdapter
    private lateinit var resId : String

    init {
        contentView = R.layout.fragment_waiting
    }

    override fun initView(root: View?) {

    }

    override fun initData() {
        presenter = WaitingSinglePresenter(context!!, this)
        mAdapter = WaitingSingleAdapter(context!!)
        val bundle = arguments
        resId = bundle!!.getString(Constant.EXTRA_RESTAURANT_ID)

        presenter.getPairs(resId)
        mAdapter.setOnItemClickListener(this)

    }

    override fun showPairs(pairs: ArrayList<Pending>) {
        mAdapter.clear()
        mAdapter.addAll(pairs)
        waitingList.adapter = mAdapter
    }

    override fun showLoadingIndicator(message: String?) {
        if (message == null){
            showLoading(view)
        } else {
            showLoading(message, view)
        }

        enableTouchable(false)
    }

    override fun hideLoadingIndicator() {
        hideLoading()
        enableTouchable(true)
    }

    //View.OnClickListener
    override fun onClick(v: View?) {

    }

    //WaitingSingleAdapter.OnItemClickListener
    override fun onClick(view: View?, position: Int) {
        val pending = mAdapter.getData()[position]
        val intent = Intent(mContext, ChatActivity::class.java)
        intent.putExtra(Constant.EXTRA_ROOM_ID, pending.boxId)
        (mContext as BaseActivity).startActivityWithAnimation(intent)
    }

}