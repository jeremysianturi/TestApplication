package com.example.aldodokter.controller.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.aldodokter.R
import com.example.aldodokter.Util.UserPreference
import com.example.aldodokter.controller.activity.Login
import com.example.aldodokter.entity.PreferenceModel
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment(), View.OnClickListener {

    private val tags = ProfileFragment::class.java.simpleName

    // session
    private lateinit var mUserPreference: UserPreference
    private lateinit var session: PreferenceModel

    // double back pressed
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // define SharedFreference
        mUserPreference = UserPreference(requireActivity())
        session = mUserPreference.getUser()

        Log.d(tags," check value session : \n email : ${session.email} \n password : ${session.password} \n islogin : ${session.isLogin} \n " +
                " name : ${session.name} \n gender : ${session.gender} \n mobile number : ${session.mobileNumber}")

        name_profile.text = session.name
        gender_profile.text = session.gender
        handphone_profile.text = session.mobileNumber

        button_logout.setOnClickListener(this)
    }

    private fun logoutSession(){
        session.isLogin = false
        mUserPreference.setUser(session)
        val mIntent = Intent(activity,Login::class.java)
        mIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        mIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(mIntent)
        activity?.finish()
    }

//    private fun clearRegisterSession() {
//        // register session
//        mUserPreference = UserPreference(this)
//        modelRegisterSession = mRegisterPreference.getData()
//
//        modelRegisterSession.nama = ""
//        modelRegisterSession.phone = ""
//        modelRegisterSession.email = ""
//        modelRegisterSession.unitId = ""
//        modelRegisterSession.unitName = ""
//        modelRegisterSession.suUnit = ""
//        modelRegisterSession.cityId = ""
//        modelRegisterSession.cityName = ""
//        modelRegisterSession.address = ""
//        modelRegisterSession.password = ""
//        modelRegisterSession.rePassword = ""
//        mRegisterPreference.setData(modelRegisterSession)
//    }

    companion object {

    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.button_logout -> {
                Log.d(tags,"clearing session")
                logoutSession()
            }
        }
    }
}