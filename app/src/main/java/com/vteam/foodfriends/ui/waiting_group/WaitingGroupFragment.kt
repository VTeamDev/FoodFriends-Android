package com.vteam.foodfriends.ui.waiting_group

import android.view.View
import com.vteam.foodfriends.R
import com.vteam.foodfriends.data.model.Pending
import com.vteam.foodfriends.ui.adapter.WaitingGroupAdapter
import com.vteam.foodfriends.ui.base.BaseFragment
import com.vteam.foodfriends.utils.Constant
import kotlinx.android.synthetic.main.fragment_waiting.*

/**
 * Created by H2PhySicS on 12/13/2017.
 */
class WaitingGroupFragment : BaseFragment(), WaitingGroupContract.View {
    override lateinit var presenter: WaitingGroupContract.Presenter
    private lateinit var mAdapter : WaitingGroupAdapter
    private lateinit var resId : String
    init {
        contentView = R.layout.fragment_waiting
    }

    override fun initView(root: View?) {

    }

    override fun initData() {
        presenter = WaitingGroupPresenter(context!!, this)
        mAdapter = WaitingGroupAdapter(context!!)
        val bundle = arguments
        resId = bundle!!.getString(Constant.EXTRA_RESTAURANT_ID)

        presenter.getGroups(resId)
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

    override fun showGroups(groups: ArrayList<Pending>) {
        mAdapter.clear()
        mAdapter.addAll(groups)
        waitingList.adapter = mAdapter
    }

}