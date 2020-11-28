package com.example.aldodokter.controller.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import com.example.aldodokter.R
import com.example.aldodokter.Util.UserPreference
import com.example.aldodokter.entity.PreferenceModel
import com.github.dhaval2404.form_validation.rule.NonEmptyRule
import com.github.dhaval2404.form_validation.validation.FormValidator
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlin.system.exitProcess

class Register : AppCompatActivity(), View.OnClickListener {

    private val tags = Register::class.java.simpleName

    // session
    private lateinit var mUserPreference: UserPreference
    private lateinit var session: PreferenceModel

    // double back pressed
    private var doubleBackToExitPressedOnce = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // hide keyboard at first
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

        // set title
        setTitle("LOGIN")

        // define SharedFreference
        mUserPreference = UserPreference(this)
        session = mUserPreference.getUser()


        // animation
        val topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)
        val leftAnim = AnimationUtils.loadAnimation(this, R.anim.left_animation)

        // hooks
        til_name.animation = topAnim
        til_gender.animation = topAnim
        til_nohp.animation = topAnim
        btn_register.animation = leftAnim

        if (session.rememberLogin) {
            tiet_name.setText(session.name)
            tiet_gender.setText(session.gender)
            tiet_nohp.setText(session.mobileNumber)
//            checkBox_remember_login.isChecked = true
        } else {
            tiet_name.setText("")
            tiet_gender.setText("")
            tiet_nohp.setText("")
//            checkBox_remember_login.isChecked = false
        }

        // on click
        btn_register.setOnClickListener(this)


    }

    private fun intentToHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun validationRegisterField() : Boolean{
        return FormValidator.getInstance()
            .addField(
                til_name,
                NonEmptyRule(R.string.empty_name),
            )
            .addField(
                til_gender,
                NonEmptyRule(R.string.empty_gender)
            )
            .addField(
                til_nohp,
                NonEmptyRule(R.string.empty_nohp)
            )
            .validate()
    }


    private fun isValidRegisterField() {
        val name = tiet_name.text.toString()
        val gender = tiet_gender.text.toString()
        val nohp = tiet_nohp.text.toString()

        saveDataRegister(name, gender, nohp)
    }

    private fun saveDataRegister(name : String, gender : String, mobileNumber : String) {

        showLoading(true)
        session.name = name
        session.gender = gender
        session.mobileNumber = mobileNumber
        session.isLogin = true
        mUserPreference.setUser(session)
        showLoading(false)

        // intent to home activity
        intentToHome()
    }

    private fun showLoading(state: Boolean) {

        if (state) {
            progressBar_register.visibility = View.VISIBLE
        } else {
            progressBar_register.visibility = View.GONE
        }

    }

    override fun onBackPressed() {

        if (doubleBackToExitPressedOnce) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
            exitProcess(0)
        }
        this.doubleBackToExitPressedOnce = true
        Toasty.warning(this, "Klik sekali lagi untuk keluar", Toasty.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_register -> {
                if (validationRegisterField()){
                    isValidRegisterField()
                }
            }
        }
    }
}