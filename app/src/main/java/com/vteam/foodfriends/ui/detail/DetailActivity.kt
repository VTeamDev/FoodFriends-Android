package com.vteam.foodfriends.ui.detail

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.widget.SnapHelper
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.GridLayout
import android.widget.RatingBar
import com.bumptech.glide.Glide
import com.vteam.foodfriends.R
import com.vteam.foodfriends.data.model.Comment
import com.vteam.foodfriends.data.model.Restaurant
import com.vteam.foodfriends.ui.adapter.CommentAdapter
import com.vteam.foodfriends.ui.base.BaseActivity
import com.vteam.foodfriends.ui.waiting.WaitingActivity
import com.vteam.foodfriends.utils.AppUtils
import com.vteam.foodfriends.utils.Constant
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : BaseActivity(), DetailContract.View, AppBarLayout.OnOffsetChangedListener, RatingBar.OnRatingBarChangeListener, View.OnClickListener {

    private val LOG_TAG : String = DetailActivity::class.java.simpleName
    private lateinit var mAdapter : CommentAdapter

    override lateinit var presenter: DetailContract.Presenter
    private lateinit var screenSize : IntArray
    val utils = AppUtils(this)
    var isChoose : Boolean = false
    var isMovingHalf : Boolean = false

    private lateinit var id : String

    init {
        contentView = R.layout.activity_detail
    }

    override fun initView() {
        commentList.addItemDecoration(CommentDecoration(getDrawable(R.drawable.divider)))
        val snapHelper : SnapHelper = CommentSnapHelper()
        snapHelper.attachToRecyclerView(commentList)
        commentList.clearFocus()
    }

    override fun initData() {
        presenter = DetailPresenter(this, this)
        mAdapter = CommentAdapter(this)
        screenSize = utils.getScreenSize(this)
        optimizeGallery()

        id = intent.getStringExtra(Constant.EXTRA_RESTAURANT_ID)
        appbar.addOnOffsetChangedListener(this)
        presenter.getRestaurant(id)
        userRating.onRatingBarChangeListener = this
        back.setOnClickListener(this)
        reserve1.setOnClickListener(this)
        reserve2.setOnClickListener(this)
        writeComment.setOnClickListener(this)
    }

    override fun showRestDetail(restaurant: Restaurant) {
        Glide.with(this).load(restaurant.photoUrl)
                .asBitmap()
                .into(header)
        restName.text = restaurant.name
        openTime.text = restaurant.timeOpen + " - " + restaurant.timeClose
        if (utils.isOpening(restaurant.timeOpen!!, restaurant.timeClose!!)){
            openTimeStatus.text = getString(R.string.rest_open)
        } else {
            openTimeStatus.text = getString(R.string.rest_close)
        }
        appbarTitle.text = restaurant.name
        ratingNumber.text = restaurant.rating.toString()
        rating.rating = restaurant.rating!!.toFloat()
        totalComment.text = restaurant.comments.size.toString() + " đánh giá"
    }

    override fun showLoadingIndicator(message: String?) {
        if (message == null){
            showLoading()
        } else {
            showLoading(message)
        }
        enableTouchable(false)
    }

    override fun hideLoadingIndicator() {
        hideLoading()
        enableTouchable(true)
    }

    override fun openWriteComment(id: String) {

    }

    override fun openWaitingRoom(id : String) {
        val intent = Intent(this@DetailActivity, WaitingActivity::class.java)
        intent.putExtra(Constant.EXTRA_RESTAURANT_ID, id)
        startActivityWithAnimation(intent)
    }

    override fun showComments(comment: ArrayList<Comment>) {
        if (comment.size > 0){
            mAdapter.clear()
            mAdapter.addAll(comment)
            commentList.adapter = mAdapter
        } else {
            commentList.visibility = View.GONE
        }
    }

    override fun onOffsetChanged(appBarLayout: AppBarLayout?, verticalOffset: Int) {
        val offset : Int = Math.abs(verticalOffset)
        val alphaToolbar = (offset / appbar.totalScrollRange).toFloat()
        val alphaHeader = 1 - alphaToolbar
        setAlpha(headerLayout, alphaHeader)
        if (offset >= appbar.totalScrollRange && isChoose){
            isChoose = false
            endCollapsed(true)
        } else if(offset < appbar.totalScrollRange && !isChoose){
            isChoose = true
            endCollapsed(false)
        }

        if (offset >= appbar.totalScrollRange / 2 && isMovingHalf){
            isMovingHalf = false
            back.setImageDrawable(getDrawable(R.drawable.ic_arrow_orange))
        } else if(offset < appbar.totalScrollRange / 2 && !isMovingHalf){
            isMovingHalf = true
            back.setImageDrawable(getDrawable(R.drawable.ic_arrow_back))
        }

    }

    override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {
        Log.e(LOG_TAG, rating.toString())
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.reserve1 -> openWaitingRoom(id)
            R.id.reserve2 -> openWaitingRoom(id)
            R.id.writeComment -> openWriteComment(id)
            R.id.back -> {
                onBackPressed()
                finish()
            }
        }
    }


    private fun optimizeGallery(){
        val params1 = mainImage.layoutParams as GridLayout.LayoutParams
        params1.width = screenSize[0] / 2
        params1.height = screenSize[0] / 2
        mainImage.layoutParams = params1

        val params2 = galleryDetailLayout.layoutParams as GridLayout.LayoutParams
        params2.width = screenSize[0] / 2
        params2.height = screenSize[0] / 2
        galleryDetailLayout.layoutParams = params2

        val detailParams1 = detailImage1.layoutParams as GridLayout.LayoutParams
        detailParams1.width = screenSize[0] / 4
        detailParams1.height = screenSize[0] / 4
        detailImage1.layoutParams = detailParams1

        val detailParams2 = detailImage2.layoutParams as GridLayout.LayoutParams
        detailParams2.width = screenSize[0] / 4
        detailParams2.height = screenSize[0] / 4
        detailImage2.layoutParams = detailParams2

        val detailParams3 = detailImage3.layoutParams as GridLayout.LayoutParams
        detailParams3.width = screenSize[0] / 4
        detailParams3.height = screenSize[0] / 4
        detailImage3.layoutParams = detailParams3

        val detailParams4 = detailImage4.layoutParams as GridLayout.LayoutParams
        detailParams4.width = screenSize[0] / 4
        detailParams4.height = screenSize[0] / 4
        detailImage4.layoutParams = detailParams4

    }

    private fun setAlpha(view : View, alpha : Float){
        view.alpha = alpha
    }

    private fun endCollapsed(isEnd : Boolean){
        val fadeIn : Animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val fadeOut : Animation = AnimationUtils.loadAnimation(this, R.anim.fade_out)

        if (isEnd){
            reserveLayout.clearAnimation()
            reserveLayout.startAnimation(fadeOut)
            reserveLayout.alpha = 0f
            appbarTitle.clearAnimation()
            appbarTitle.startAnimation(fadeIn)
            appbarTitle.alpha = 1f
            reserve2.clearAnimation()
            reserve2.startAnimation(fadeIn)
            reserve2.alpha = 1f
        } else {
            reserveLayout.clearAnimation()
            reserveLayout.startAnimation(fadeIn)
            reserveLayout.alpha = 1f
            appbarTitle.clearAnimation()
            appbarTitle.startAnimation(fadeOut)
            appbarTitle.alpha = 0f
            reserve2.clearAnimation()
            reserve2.startAnimation(fadeOut)
            reserve2.alpha = 0f
            back.setImageDrawable(getDrawable(R.drawable.ic_arrow_back))
        }
    }
}
