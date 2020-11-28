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
import kotlin.system.exitProcess

class Login : AppCompatActivity(),View.OnClickListener {

    private val tags = Login::class.java.simpleName

    // session
    private lateinit var mUserPreference: UserPreference
    private lateinit var session: PreferenceModel

    // double back pressed
    private var doubleBackToExitPressedOnce = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
        til_email.animation = topAnim
        til_password.animation = topAnim
        btn_signin.animation = leftAnim

        if (session.rememberLogin) {
            tiet_email.setText(session.email)
            tiet_password.setText(session.password)
            checkBox_remember_login.isChecked = true
        } else {
            tiet_email.setText("")
            tiet_password.setText("")
            checkBox_remember_login.isChecked = false
        }

        // on click
        btn_signin.setOnClickListener(this)

        // call method
        isLogin()
    }

    private fun isLogin(){
        if (session.isLogin){
            intentToHome()
        }
    }

    private fun intentToHome(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun intentToRegister(){
        val intent = Intent(this, Register::class.java)
        startActivity(intent)
    }

    private fun validationLoginField() : Boolean{
        return FormValidator.getInstance()
            .addField(
                til_email,
                NonEmptyRule(R.string.empty_email),
            )
            .addField(
                til_password,
                NonEmptyRule(R.string.empty_password)
            )
            .validate()
    }

    private fun isValidLoginField() {
        val email = tiet_email.text.toString()
        val password = tiet_password.text.toString()

        saveDataLogin(email, password)
    }

    private fun saveDataLogin(email: String, password: String) {

        showLoading(true)
        session.email = email
        session.password = password
        session.rememberLogin = checkBox_remember_login.isChecked
        mUserPreference.setUser(session)
        showLoading(false)

        // intent to register
        intentToRegister()
    }

    private fun showLoading(state: Boolean) {

        if (state) {
            progressBar.visibility = View.VISIBLE
         } else {
             progressBar.visibility = View.GONE
         }

    }


    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.btn_signin -> {

                if (validationLoginField()){
                    isValidLoginField()

                }
            }
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
    

}