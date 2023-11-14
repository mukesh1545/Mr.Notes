package com.example.mrnotes.Activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.mrnotes.R
import com.google.firebase.auth.FirebaseAuth

class splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            //val user = FirebaseAuth.getInstance().updateCurrentUser()
                var intent=Intent(this, NotePage::class.java)
                startActivity(intent)
            finish()
        },1000)
    }
}