package com.example.aldodokter.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PreferenceModel (
    var email: String? = null,
    var password: String? = null,
    var rememberLogin: Boolean = false,
    var isLogin : Boolean = false,
    var name : String? = null,
    var gender : String? = null,
    var mobileNumber : String? = null
    ) : Parcelable