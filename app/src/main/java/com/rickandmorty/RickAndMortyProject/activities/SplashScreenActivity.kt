package com.rickandmorty.RickAndMortyProject.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.rickandmorty.RickAndMortyProject.R
import com.rickandmorty.RickAndMortyProject.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

        private val splashScreen = 5000
        private lateinit var sharedPreferences: SharedPreferences
        private lateinit var sharedEditor: SharedPreferences.Editor
        private lateinit var binding : ActivitySplashScreenBinding

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Animation Setup
        val animationImage = AnimationUtils.loadAnimation(this, R.anim.splash_screen_logo)
        val animationText = AnimationUtils.loadAnimation(this, R.anim.splash_screen_text)

        val imageView = binding.splashLogo
        val text = binding.splashText

        imageView.animation = animationImage
        text.animation = animationText

        //Splash Screen create
        Handler().postDelayed({

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()

        },splashScreen.toLong())

        //SharedPreferences defination
        sharedPreferences = getPreferences(MODE_PRIVATE)
        sharedEditor = sharedPreferences.edit()
        val splashText = findViewById<TextView>(R.id.splashText)

        //Checking if the application is opened for the first time
        if (firstTimeWorkCheck()) {

                splashText.text = "Welcome!"

        } else {

                splashText.text = "Hello!"

        }

    }

        //FirstTimeWorkCheck function
        private fun firstTimeWorkCheck(): Boolean {

                return if (sharedPreferences.getBoolean("firstTime", true)) {
                        sharedEditor.putBoolean("firstTime", false)
                        sharedEditor.commit()
                        sharedEditor.apply()
                        true

                } else {

                        false

                }

        }

}