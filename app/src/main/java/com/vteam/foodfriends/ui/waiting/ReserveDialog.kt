package com.vteam.foodfriends.ui.waiting

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import android.text.TextUtils
import android.util.Log
import android.view.View
import com.vteam.foodfriends.R
import com.vteam.foodfriends.ui.custom.TimeTextView
import com.vteam.foodfriends.utils.AppUtils
import kotlinx.android.synthetic.main.dialog_reserve.*
import kotlinx.android.synthetic.main.item_comment.*
import java.util.*

/**
 * Created by H2PhySicS on 12/13/2017.
 */
class ReserveDialog(context: Context,
                    val listener : OnClickListener) : Dialog(context), View.OnClickListener{
    private val LOG_TAG = ReserveDialog::class.java.simpleName
    private lateinit var currentTime : String
    private var isSingle = false
    private var index = 0
    private lateinit var dates : Array<String?>
    private lateinit var utils : AppUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_reserve)
        reserve.setOnClickListener(this)
        (day1 as TimeTextView).setOnClickListener(this)
        day2.setOnClickListener(this)
        day3.setOnClickListener(this)
        day4.setOnClickListener(this)
        day5.setOnClickListener(this)
        single.setOnClickListener(this)
        group.setOnClickListener(this)

        initData()
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.reserve -> {
                if (TextUtils.isEmpty(hour.text) || TextUtils.isEmpty(minute.text)) return

                val date : String = dates[index]!!
                val hour : Int = hour.text.toString().toInt()
                val minute : Int = minute.text.toString().toInt()
                val newDate : Date = utils.convertStringToDate(date)!!
                newDate.hours = hour
                newDate.minutes = minute

                listener.onClick(utils.convertDateToString(newDate)!!, isSingle)

                dismiss()

            }
            R.id.day1 -> setSelectedDay(0)
            R.id.day2 -> setSelectedDay(1)
            R.id.day3 -> setSelectedDay(2)
            R.id.day4 -> setSelectedDay(3)
            R.id.day5 -> setSelectedDay(4)
            R.id.single -> isSingle = true
            R.id.group -> isSingle = false
        }

        setTypeReserve(isSingle)
    }

    interface OnClickListener{
        fun onClick(time : String, isSingle : Boolean)
    }

    fun initData(){
        utils = AppUtils(context)
        hour.filters = arrayOf<InputFilter>(InputFilterMinMax("0", "23"))
        minute.filters = arrayOf<InputFilter>(InputFilterMinMax("0", "59"))
        currentTime = utils.getCurrentTime()
        val timeSplit = currentTime.split(":")
        hour.setText(timeSplit[0])
        minute.setText(timeSplit[1])

        dates = utils.getDates(5)
        val convertedDates = arrayOfNulls<Date>(5)
        for (i in 0..(dates.size - 1)){
            convertedDates[i] = utils.convertStringToDate(dates[i]!!)
        }

        day1.text = utils.getDayOfDate(convertedDates[0]!!)
        day2.text = utils.getDayOfDate(convertedDates[1]!!)
        day3.text = utils.getDayOfDate(convertedDates[2]!!)
        day4.text = utils.getDayOfDate(convertedDates[3]!!)
        day5.text = utils.getDayOfDate(convertedDates[4]!!)

        setTypeReserve(isSingle)
        setSelectedDay(0)

    }

    private fun setSelectedDay(position : Int){
        index = position
        when (position) {
            1 -> {
                (day2 as TimeTextView).setSelectedItem()
                (day1 as TimeTextView).removeSelectedItem()
                (day3 as TimeTextView).removeSelectedItem()
                (day4 as TimeTextView).removeSelectedItem()
                (day5 as TimeTextView).removeSelectedItem()
            }
            2 -> {
                (day3 as TimeTextView).setSelectedItem()
                (day1 as TimeTextView).removeSelectedItem()
                (day2 as TimeTextView).removeSelectedItem()
                (day4 as TimeTextView).removeSelectedItem()
                (day5 as TimeTextView).removeSelectedItem()
            }
            3 -> {
                (day4 as TimeTextView).setSelectedItem()
                (day1 as TimeTextView).removeSelectedItem()
                (day3 as TimeTextView).removeSelectedItem()
                (day2 as TimeTextView).removeSelectedItem()
                (day5 as TimeTextView).removeSelectedItem()
            }
            4 -> {
                (day5 as TimeTextView).setSelectedItem()
                (day1 as TimeTextView).removeSelectedItem()
                (day3 as TimeTextView).removeSelectedItem()
                (day4 as TimeTextView).removeSelectedItem()
                (day2 as TimeTextView).removeSelectedItem()
            }
            0 -> {
                (day1 as TimeTextView).setSelectedItem()
                (day2 as TimeTextView).removeSelectedItem()
                (day3 as TimeTextView).removeSelectedItem()
                (day4 as TimeTextView).removeSelectedItem()
                (day5 as TimeTextView).removeSelectedItem()
            }
            else -> {
                (day1 as TimeTextView).setSelectedItem()
                (day2 as TimeTextView).removeSelectedItem()
                (day3 as TimeTextView).removeSelectedItem()
                (day4 as TimeTextView).removeSelectedItem()
                (day5 as TimeTextView).removeSelectedItem()
            }
        }
    }

    private fun setTypeReserve(isSingle: Boolean){
        if (isSingle){
            single.setImageDrawable(context.getDrawable(R.drawable.pair_active))
            group.setImageDrawable(context.getDrawable(R.drawable.group_inactive))
        } else {
            single.setImageDrawable(context.getDrawable(R.drawable.pair_inactive))
            group.setImageDrawable(context.getDrawable(R.drawable.group_active))
        }
    }

}