package com.vteam.foodfriends.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Criteria
import android.location.Location
import android.location.LocationManager
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.DisplayMetrics
import android.util.Log
import com.google.android.gms.location.LocationServices
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by H2PhySicS on 12/11/2017.
 */
class AppUtils(val context: Context){
    companion object {
        private val LOG_TAG : String = AppUtils::class.java.simpleName
        val VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)
        val VALID_PHONE_REGEX = Pattern.compile("^(?:(?:\\\\+?1\\\\s*(?:[.-]\\\\s*)?)?(?:\\\\(\\\\s*([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9])\\\\s*\\\\)|([2-9]1[02-9]|[2-9][02-8]1|[2-9][02-8][02-9]))\\\\s*(?:[.-]\\\\s*)?)?([2-9]1[02-9]|[2-9][02-9]1|[2-9][02-9]{2})\\\\s*(?:[.-]\\\\s*)?([0-9]{4})(?:\\\\s*(?:#|x\\\\.?|ext\\\\.?|extension)\\\\s*(\\\\d+))?\$")
    }

    fun getCurrentTime() : String{
        val date : Date = Date()
        val simpleDateFormat = SimpleDateFormat("HH:mm")
        return simpleDateFormat.format(date)
    }

    fun getDates(numDay : Int) : Array<String?>{
        var date = Date()

        var currentTime : Long = date.time
        var dates = arrayOfNulls<String>(numDay)
        for (i in 0..(numDay - 1)){
            date.time = currentTime
            var format = SimpleDateFormat("HH:mm/dd/MM/yyyy")
            dates[i] = format.format(date)
            currentTime += TimeUnit.DAYS.toMillis(1)
        }

        return dates
    }

    fun getDistance(latUser : Double, lonUser : Double, latShop : Double, lonShop : Double) : Int{
        return -1
    }

    fun isOpening(openTime : String, closeTime : String) : Boolean{
        var isOpen = false
        val open = getTimeFromString(openTime)
        val close = getTimeFromString(closeTime)
        val current = Date()
        if (open.compareTo(current) < 1 && current.compareTo(close) < 1){
            isOpen = true
        }

        return isOpen
    }

    fun getTimeFromString(time : String) : Date{
        val str = time.split(":")
        val date = Date()
        date.hours = str[0].toInt()
        date.minutes = str[1].toInt()
        return date
    }

    fun getAge(birthday : String) : Int{
        var age = 0

        val simpleFormat = SimpleDateFormat("dd/MM/yyyy")
        val dob = simpleFormat.parse(birthday)
        val current = Date()
        age = current.year - dob.year
        return age
    }

    fun convertStringToDate(s : String) : Date?{
        val simpleFormat = SimpleDateFormat("HH:mm/dd/MM/yyyy")
        val date : Date? = simpleFormat.parse(s)
        return date
    }

    fun convertDateToString(date : Date) : String?{
        val simpleFormat = SimpleDateFormat("HH:mm/dd/MM/yyyy")
        return simpleFormat.format(date)
    }

    fun getDayOfDate(date: Date) : String{
        val simpleDateFormat = SimpleDateFormat("dd");
        return simpleDateFormat.format(date);
    }

    fun isEmail(email : String) : Boolean{
        val matcher : Matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email)
        return matcher.find()
    }

    fun isPhoneNumber(phone : String) : Boolean{
        val matcher : Matcher = VALID_PHONE_REGEX.matcher(phone)
        return matcher.find()
    }

    fun isBirthday(brithday : String) : Boolean{
        return true
    }

    fun getScreenSize(activity : AppCompatActivity) : IntArray{
        val displayMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels
        val height = displayMetrics.heightPixels
        return intArrayOf(width, height)
    }

    fun getLastLocation(context: Context, callback: LocationCallback){
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            Log.e(LOG_TAG, "Lack of location permission")
            return
        }
        val locationClient = LocationServices.getFusedLocationProviderClient(context)
        locationClient.lastLocation.addOnSuccessListener { location: Location? ->
            callback.onSuccess(location)
        }
    }

}