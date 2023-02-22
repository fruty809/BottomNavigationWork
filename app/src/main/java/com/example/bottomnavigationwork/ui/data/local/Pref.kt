package com.example.bottomnavigationwork.ui.data.local

import android.content.Context.MODE_PRIVATE
import com.example.bottomnavigationwork.MainActivity

class Pref(context: MainActivity) {

    private val pref = context.getSharedPreferences(PREF_NAME,MODE_PRIVATE)


        fun isUserSeen(): Boolean{
            return pref.getBoolean(SEEN_KEY,false)
        }


    companion object{
        const val PREF_NAME = "pref.task"
        const val SEEN_KEY = "seen.key"
    }
}