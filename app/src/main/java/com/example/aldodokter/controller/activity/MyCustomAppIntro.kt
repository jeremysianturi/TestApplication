package com.example.aldodokter.controller.activity

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.aldodokter.R
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType

class MyCustomAppIntro : AppIntro() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Get extra
        var nameAnimalIntent : String? = intent.getStringExtra("name")

        // Turtle got selected
        if (nameAnimalIntent.equals("Turtle")){
            turtleGotSelected()
        }

        // Sea Star got selected
        else if (nameAnimalIntent.equals("Sea Star")){
            seastarGotSelected()
        }

        // Plankton got selected
        else if (nameAnimalIntent.equals("Plankton")){
            planktonGotSelected()
        }

        setTransformer(AppIntroPageTransformerType.Flow)
        isColorTransitionsEnabled = true
        setImmersiveMode()
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        finish()
    }

    private fun turtleGotSelected(){
        addSlide(AppIntroFragment.newInstance(
            title = "Turtle images in the desert",
            description = "The animal that can walk very fast wk WK wk",
            backgroundColor = Color.DKGRAY,
            imageDrawable = R.drawable.kura_kura2

        ))

        addSlide(AppIntroFragment.newInstance(
            title = "Turtle images in the sea",
            description = "The animal that can swim very fast wk WK wk",
            imageDrawable = R.drawable.kura_kura1,
            backgroundColor = Color.BLUE
        ))

        addSlide(AppIntroFragment.newInstance(
            title = "Turtle images at nowhere",
            description = "The animal that can flyk very fast wk WK wk",
            imageDrawable = R.drawable.kura_kura3,
            backgroundColor = Color.GRAY
        ))
    }

    private fun seastarGotSelected(){
        addSlide(AppIntroFragment.newInstance(
            title = "Turtle images in the desert",
            description = "The animal that can walk very fast wk WK wk",
            imageDrawable = R.drawable.seastar1,
            backgroundColor = Color.DKGRAY,

        ))

        addSlide(AppIntroFragment.newInstance(
            title = "Turtle images in the sea",
            description = "The animal that can swim very fast wk WK wk",
            imageDrawable = R.drawable.seastar2,
            backgroundColor = Color.BLUE
        ))

        addSlide(AppIntroFragment.newInstance(
            title = "Turtle images at nowhere",
            description = "The animal that can flyk very fast wk WK wk",
            imageDrawable = R.drawable.seastar3,
            backgroundColor = Color.GRAY
        ))
    }

    private fun planktonGotSelected(){
        addSlide(AppIntroFragment.newInstance(
            title = "Turtle images in the desert",
            description = "The animal that can walk very fast wk WK wk",
            imageDrawable = R.drawable.plankton1,
            backgroundColor = Color.DKGRAY

        ))

        addSlide(AppIntroFragment.newInstance(
            title = "Turtle images in the sea",
            description = "The animal that can swim very fast wk WK wk",
            imageDrawable = R.drawable.plankton2,
            backgroundColor = Color.BLUE
        ))

        addSlide(AppIntroFragment.newInstance(
            title = "Turtle images at nowhere",
            description = "The animal that can flyk very fast wk WK wk",
            imageDrawable = R.drawable.plankton3,
            backgroundColor = Color.GRAY
        ))
    }

}