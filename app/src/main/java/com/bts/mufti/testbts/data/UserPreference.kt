package com.bts.mufti.testbts.data

import android.content.Context

internal class UserPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val API_KEY = "api_key"
    }
    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setApiKey(key: String) {
        val editor = preferences.edit()
        editor.putString(API_KEY, key)
        editor.apply()
    }
    fun getUser(): String {
        return preferences.getString(API_KEY, "").toString()
    }
}