package com.example.heal_me

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.content.SharedPreferences
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var auth : FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        auth = FirebaseAuth.getInstance()
        // Check if a user is logged in
        val currentUser = auth.currentUser

        // Delayed navigation to the main activity after the splash screen
        Handler(Looper.getMainLooper()).postDelayed({
            if (currentUser != null){
                val intent = Intent(this, HomeNavControl::class.java)
                startActivity(intent)
                finish()
            }else{
                val intent = Intent(this, IntroActivity::class.java)
                startActivity(intent)
                finish()
            }
        }, 2000)

    }
}