package com.vteam.foodfriends.ui.base

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.annotation.StringRes
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import com.vteam.foodfriends.R
import kotlinx.android.synthetic.main.loading_view.view.*

/**
 * Created by H2PhySicS on 12/11/2017.
 */
abstract class BaseFragment : Fragment() {
    companion object {
        private val LOG_TAG : String = BaseFragment::class.java.simpleName
    }

    protected var mContext : Context? = null

    @LayoutRes
    var contentView : Int? = null
    public abstract fun initView(root : View?)
    public abstract fun initData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root : View = inflater.inflate(contentView!!, container, false)
        initView(root)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mContext = activity
        initData()
    }

    public fun runOnUiThread(runnable: Runnable){
        if (activity != null){
            activity?.runOnUiThread(runnable)
        }
    }

    /**
     * Show keyboard in activity
     * @param view
     */
    fun showKeyboard(view : View?){
        if (view == null) return
        (activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    /**
     * Hide keyboard in activity
     */
    fun hideKeyboard(){
        val inputMethodManager : InputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view : View = activity?.currentFocus ?: return
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    /**
     * Show loading indicator
     * @param message
     * @param parent
     */
    fun showLoading(message : String?, parent : View?){
        try {
            runOnUiThread(runnable = Runnable {
                if (parent == null) {
                    Log.d(LOG_TAG, getString(R.string.parent_view_null))
                    return@Runnable
                }

                val view : View? = parent.findViewById(R.id.loadingView)

                if (view == null){
                    Log.e(LOG_TAG, getString(R.string.loading_view_null))
                    return@Runnable
                }

                val title : TextView? = view.loadingTitle
                if (TextUtils.isEmpty(message)){
                    title?.visibility = View.GONE
                } else {
                    title?.visibility = View.VISIBLE
                    title?.text = message
                }
                if (isAdded){
                    view.visibility = View.VISIBLE
                }

            })
        } catch (e : Exception){
            e.printStackTrace()
        }
    }

    /**
     * Show loading indicator
     * @param parent
     */
    fun showLoading(parent: View?){
        showLoading(null, parent)
    }

    /**
     * Show loading indicator
     * @param resId
     * @param parent
     */
    fun showLoading(@StringRes resId : Int, parent: View?){
        showLoading(getString(resId), parent)
    }

    /**
     * Hide loading indicator
     */
    fun hideLoading(){
        if (view == null) return
        val v : View = view?.findViewById(R.id.loadingView) ?: return
        runOnUiThread(Runnable {
            if (isAdded){
                v.visibility = View.GONE
            }
        })
    }

    /**
     * Show alert dialog
     * @param title
     * @param message
     */
    fun showAlert(title : String, message : String){
        runOnUiThread(runnable = Runnable {
            try {
                AlertDialog.Builder(mContext!!)
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

    /**
     * Show alert dialog
     * @param resIdTitle
     * @param resIdMessage
     */
    fun showAlert(@StringRes resIdTitle : Int, @StringRes resIdMessage : Int){
        runOnUiThread(runnable = Runnable {
            try {
                AlertDialog.Builder(mContext!!)
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

    /**
     *Show alert dialog
     * @param title
     * @param message
     * @param listener
     */
    fun showAlert(title : String, message : String, listener : BaseActivity.AlertListener?){
        runOnUiThread(runnable = Runnable {
            try {
                AlertDialog.Builder(mContext!!)
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

    /**
     * Show alert dialog
     * @param title
     * @param message
     * @param yesString
     * @param noString
     * @param listener
     */
    fun showAlert(title: String, message: String, yesString: String, noString: String, listener : BaseActivity.AlertListener?){
        runOnUiThread(runnable = Runnable {
            try {
                AlertDialog.Builder(mContext!!)
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

    protected fun isCurrentFragmentVisible(fragment: Fragment?) : Boolean{
        if (fragment == null) return false
        if (fragment.isAdded || fragment.isVisible || fragment.isResumed) return true
        return false
    }

    fun enableTouchable(enable : Boolean){
        if (!enable){
            activity!!.window.setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        } else {
            activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

}