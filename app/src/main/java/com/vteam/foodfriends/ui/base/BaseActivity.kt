package com.vteam.foodfriends.ui.base

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.vteam.foodfriends.R
import kotlinx.android.synthetic.main.loading_view.view.*

/**
 * Created by H2PhySicS on 12/11/2017.
 */
abstract class BaseActivity : AppCompatActivity() {
    companion object {
        private val LOG_TAG : String = BaseActivity::class.java.simpleName
    }

    @LayoutRes
    var contentView : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(contentView!!)
        initView()
        initData()
    }

    abstract fun initView()
    abstract fun initData()

    /**
     * Show keyboard in activity
     * @param view
     */
    fun showKeyboard(view : View?){
        if (view == null) return
        (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    /**
     * Hide keyboard in activity
     */
    fun hideKeyboard(){
        val inputMethodManager : InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view : View = currentFocus ?: return
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Show loading indicator
     * @param message
     */
    fun showLoading(message : String?){
        try{
            runOnUiThread( {
                val view : View? = findViewById(R.id.loadingView)
                if (view == null) {
                    Log.d(LOG_TAG, getString(R.string.loading_view_null))
                    return@runOnUiThread
                }

                val title : TextView? = view.loadingTitle
                if (TextUtils.isEmpty(message)){
                    title?.visibility = View.GONE
                } else {
                    title?.text = message
                    title?.visibility = View.VISIBLE
                }

                if (!isFinishing){
                    view.visibility = View.VISIBLE
                }

            })
        } catch (e : Exception){
            e.printStackTrace()
        }
    }

    /**
     * Show loading indicator
     */
    fun showLoading(){
        showLoading(null)
    }

    /**
     * Show loading indicator
     * @param resId
     */
    fun showLoading(@StringRes resId : Int){
        showLoading(getString(resId))
    }

    /**
     * Hide loading indicator
     */
    fun hideLoading(){
        val view : View? = findViewById(R.id.loadingView)
        if (view == null){
            Log.d(LOG_TAG, getString(R.string.loading_view_null))
            return
        }

        if (!isFinishing){
            runOnUiThread({
                view.visibility = View.GONE
            })
        }
    }

    /**
     * Start activity using simple animation
     * @param intent
     */
    fun startActivityWithAnimation(intent: Intent){
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left)
    }

    /**
     * Finish activity with using simple animation
     */
    fun finishActivityWithAnimation(){
        finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right)
    }

    /**
     * Show alert dialog
     * @param title
     * @param message
     */
    fun showAlert(title : String, message : String){
        if (!isFinishing){
            runOnUiThread( {
                try {
                    AlertDialog.Builder(this@BaseActivity)
                            .setTitle(title)
                            .setMessage(message)
                            .setNegativeButton(android.R.string.ok, null)
                            .create()
                            .show()
                } catch (exception : Exception){
                    exception.printStackTrace()
                }
            })
        }
    }

    /**
     * Show alert dialog
     * @param resIdTitle
     * @param resIdMessage
     */
    fun showAlert(@StringRes resIdTitle : Int, @StringRes resIdMessage : Int){
        if (!isFinishing){
            runOnUiThread( {
                try {
                    AlertDialog.Builder(this@BaseActivity)
                            .setTitle(getString(resIdTitle))
                            .setMessage(getString(resIdMessage))
                            .setNegativeButton(android.R.string.ok, null)
                            .create()
                            .show()
                } catch (exception : Exception){
                    exception.printStackTrace()
                }
            })
        }
    }

    /**
     *Show alert dialog
     * @param title
     * @param message
     * @param listener
     */
    fun showAlert(title : String, message : String, listener : AlertListener?){
        if (!isFinishing){
            runOnUiThread( {
                try {
                    AlertDialog.Builder(this@BaseActivity)
                            .setTitle(title)
                            .setMessage(message)
                            .setNegativeButton(android.R.string.ok, { dialog: DialogInterface?, which: Int ->
                                if (listener != null){
                                    listener.yesOnClick()
                                }
                            })
                            .create()
                            .show()
                } catch (exception : Exception){
                    exception.printStackTrace()
                }
            })
        }
    }

    /**
     * Show alert dialog
     * @param title
     * @param message
     * @param yesString
     * @param noString
     * @param listener
     */
    fun showAlert(title: String, message: String, yesString: String, noString: String, listener : AlertListener?){
        if (!isFinishing){
            runOnUiThread( {
                try {
                    AlertDialog.Builder(this@BaseActivity)
                            .setTitle(title)
                            .setMessage(message)
                            .setPositiveButton(yesString, {dialog, which ->
                                if (listener != null){
                                    listener.yesOnClick()
                                }
                            })
                            .setNegativeButton(noString, {dialog, which ->

                            })
                            .create()
                            .show()
                } catch (exception : Exception){
                    exception.printStackTrace()
                }
            })
        }
    }

    interface AlertListener{
        fun yesOnClick()
    }

    /**
     * Post delay in activity
     * @param runnable
     * @param delay
     */
    fun postDelayed(runnable: Runnable, delay : Long){
        Handler().postDelayed(runnable, delay)
    }

    fun enableTouchable(enable : Boolean){
        if (!enable){
            window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

}