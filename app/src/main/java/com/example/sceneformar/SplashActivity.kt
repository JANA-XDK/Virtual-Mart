package com.example.sceneformar

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Delay for 2 seconds (2000 milliseconds) and then decide which activity to start
        Handler(Looper.getMainLooper()).postDelayed({
            // Check if the user is logged in using Firebase Authentication
            val user = FirebaseAuth.getInstance().currentUser

            val intent = if (user != null) {
                // User is logged in, go to MainActivity
                Intent(this, MainActivity::class.java)
            } else {
                // User is not logged in, go to SignupActivity
                Intent(this, SignupActivity::class.java)
            }
            startActivity(intent)
            finish() // Close the SplashActivity
        }, 2000) // 2000 milliseconds delay
    }
}
