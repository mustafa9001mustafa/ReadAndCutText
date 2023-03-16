package com.konden.readandcuttext.Shard

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class ShardPreferans private constructor() {

    companion object {
        private val sharePref = ShardPreferans()
        private lateinit var sharedPreferences: SharedPreferences

        private val Dark = "dark"
        private val Str = "str"
        private val LastStr = "last_str"

        fun getInstance(context: Context): ShardPreferans {
            if (!::sharedPreferences.isInitialized) {
                synchronized(ShardPreferans::class.java) {
                    if (!::sharedPreferences.isInitialized) {
                        sharedPreferences =
                            context.getSharedPreferences(context.packageName, Activity.MODE_PRIVATE)
                    }
                }
            }
            return sharePref
        }
    }

    var dark: Boolean = true
        get() = sharedPreferences.getBoolean(Dark, field)

    fun saveDark(dark: Boolean) {
        sharedPreferences.edit()
            .putBoolean(Dark, dark)
            .apply()
    }


    val str: String? = null
        get() = sharedPreferences.getString(Str, field)

    fun saveStr(str: String) {
        sharedPreferences.edit()
            .putString(Str, str)
            .apply()
    }


    var Last_str: Boolean = false
        get() = sharedPreferences.getBoolean(LastStr, field)

    fun saveLast_str(last_string: Boolean) {
        sharedPreferences.edit()
            .putBoolean(LastStr, last_string)
            .apply()
    }


    fun isFirstTimeOther(): Boolean {
        val ranBefore = sharedPreferences.getBoolean("RanBefore", false)
        if (!ranBefore) {
            sharedPreferences.edit().putBoolean("RanBefore", true)
                .apply()
        }
        return !ranBefore
    }


}