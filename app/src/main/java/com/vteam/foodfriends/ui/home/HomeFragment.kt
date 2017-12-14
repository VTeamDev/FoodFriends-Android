package com.vteam.foodfriends.ui.home

import android.content.Intent
import android.view.View
import com.vteam.foodfriends.R
import com.vteam.foodfriends.data.model.Restaurant
import com.vteam.foodfriends.ui.adapter.RestaurantAdapter
import com.vteam.foodfriends.ui.base.BaseActivity
import com.vteam.foodfriends.ui.base.BaseFragment
import com.vteam.foodfriends.ui.detail.DetailActivity
import com.vteam.foodfriends.utils.Constant
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Created by H2PhySicS on 12/11/2017.
 */
class HomeFragment : BaseFragment(), HomeContract.View, RestaurantAdapter.OnItemClickListener {

    private val LOG_TAG : String = HomeFragment::class.java.simpleName

    private lateinit var mAdapter : RestaurantAdapter

    override lateinit var presenter: HomeContract.Presenter

    init {
        contentView =  R.layout.fragment_home
    }

    override fun initView(root: View?) {

    }

    override fun initData() {
        searchView.queryHint = getString(R.string.search_hint)
        searchView.isIconified = false
        searchView.clearFocus()
        presenter = HomePresenter(mContext!!, this)
        mAdapter = RestaurantAdapter(mContext!!)
        presenter.start()
        presenter.loadRestaurants()
        mAdapter.setOnItemClickListener(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun showRestaurants(rests: ArrayList<Restaurant>) {
        mAdapter.clear()
        mAdapter.addAll(rests)
        restList.adapter = mAdapter

    }

    override fun openDetail(id: String) {
        val intent = Intent(mContext, DetailActivity::class.java)
        intent.putExtra(Constant.EXTRA_RESTAURANT_ID, id)

        (mContext as BaseActivity).startActivityWithAnimation(intent)
    }

    override fun showLoadingIndicator(message: String?) {
        if(message == null){
            showLoading(view)
        } else{
            showLoading(message, view)
        }
        enableTouchable(false)
    }

    override fun hideLoadingIndicator() {
        hideLoading()
        enableTouchable(true)
    }

    override fun onItemClick(view: View?, position: Int) {
        when(view?.id){
            R.id.cardView -> {
                openDetail(mAdapter.getData()[position].id)
            }
            R.id.follow -> {

            }
            R.id.direct -> {

            }
        }
    }


}