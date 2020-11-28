package com.example.aldodokter.controller.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.aldodokter.R
import com.example.aldodokter.Util.UserPreference
import com.example.aldodokter.controller.fragment.HomeFragment
import com.example.aldodokter.controller.fragment.ProfileFragment
import com.example.aldodokter.entity.PreferenceModel
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {

    private val TAG: String = HomeActivity::class.java.simpleName
    private lateinit var mUserPreference: UserPreference
    private lateinit var session: PreferenceModel
    private var doubleBackToExitPressedOnce = false

    private var mFragment = Fragment()
    private val mFragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_home)

        mUserPreference = UserPreference(this)
        session = mUserPreference.getUser()

        mFragment = HomeFragment()
        val fragment = mFragmentManager.findFragmentByTag(HomeFragment::class.java.simpleName)

        mFragmentManager.commit {
            replace(R.id.frame_container_home, mFragment)
        }

        lytHome.setOnClickListener(this)
        lytProfile.setOnClickListener(this)

    }

    private fun showLoading(state: Boolean) {
        if (state) {
            progressBar_home.visibility = View.VISIBLE
        } else {
            progressBar_home.visibility = View.GONE
        }
    }

    override fun onClick(p0: View?) {
        when(p0?.id){
            R.id.lytHome -> {
                showLoading(true)
                mFragment = HomeFragment()

                //image
                icHome.setImageResource(R.drawable.ic_home_selected)
                icProfile.setImageResource(R.drawable.ic_profile)

                //text
                tvHome.setTextColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.black
                    )
                )
                tvProfile.setTextColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.grey_text
                    )
                )

                mFragmentManager.commit {
                    replace(
                        R.id.frame_container_home,
                        mFragment,
                        HomeFragment::class.java.simpleName
                    )
                }
                showLoading(false)
            }
            R.id.lytProfile -> {

                showLoading(true)
                mFragment = ProfileFragment()

                //image
                icHome.setImageResource(R.drawable.ic_home)
                icProfile.setImageResource(R.drawable.ic_profile_selected)

                //text
                tvHome.setTextColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.grey_text
                    )
                )
                tvProfile.setTextColor(
                    ContextCompat.getColor(
                        applicationContext,
                        R.color.black
                    )
                )

                mFragmentManager.commit {
                    replace(
                        R.id.frame_container_home,
                        mFragment,
                        ProfileFragment::class.java.simpleName
                    )
                }
                showLoading(false)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        HomeFragment()
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
            System.exit(0)
        }
        this.doubleBackToExitPressedOnce = true
//        Toast.makeText(this, "Klik sekali lagi untuk keluar", Toast.LENGTH_SHORT).show()
        Toasty.warning(this, "Klik sekali lagi untuk keluar", Toasty.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}