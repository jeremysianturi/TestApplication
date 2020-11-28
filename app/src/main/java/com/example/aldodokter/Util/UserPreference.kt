package com.example.aldodokter.Util

import android.content.Context
import androidx.core.content.edit
import com.example.aldodokter.entity.PreferenceModel

internal class UserPreference(context: Context) {
    companion object{
        private const val ISLOGIN = "islogin"
        private const val PREF_EMAIL = "email"
        private const val PREF_PASSWORD = "password"
        private const val REMEMBER_LOGIN = "remember_login"
        private const val PREF_NAME = "name"
        private const val PREF_GENDER = "gender"
        private const val PREF_MOBILE_NUMBER = "mobile_number"

    }

    private val preferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE)

    fun setUser(value : PreferenceModel) {
        preferences.edit {
            putString(PREF_EMAIL, value.email)
            putString(PREF_PASSWORD, value.password)
            putBoolean(REMEMBER_LOGIN, value.rememberLogin)
            putBoolean(ISLOGIN, value.isLogin)
            putString(PREF_NAME, value.name)
            putString(PREF_GENDER,value.gender)
            putString(PREF_MOBILE_NUMBER,value.mobileNumber)
        }
    }

    fun getUser() : PreferenceModel {

        val model = PreferenceModel()

        model.email = preferences.getString(PREF_EMAIL, "")
        model.password = preferences.getString(PREF_PASSWORD, "")
        model.rememberLogin = preferences.getBoolean(REMEMBER_LOGIN, false)
        model.isLogin = preferences.getBoolean(ISLOGIN, false)
        model.name = preferences.getString(PREF_NAME, "")
        model.gender = preferences.getString(PREF_GENDER, "")
        model.mobileNumber = preferences.getString(PREF_MOBILE_NUMBER, "")

        return model
    }
}