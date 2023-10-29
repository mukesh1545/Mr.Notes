package com.example.mrnotes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class splash : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            //val user = FirebaseAuth.getInstance().updateCurrentUser()
            var user=FirebaseAuth.getInstance().currentUser?.uid
            var Emailvalidation:Boolean=( FirebaseAuth.getInstance().currentUser?.isEmailVerified() == true)
            Log.d("mukesh","$user  $Emailvalidation")
            if (user != null && Emailvalidation )
            {


                var intent=Intent(this,NotePage::class.java)
                intent.putExtra("id",user)
                startActivity(intent)
            }
            else
            {


                val intent = Intent(this, Login_Page::class.java)
                startActivity(intent)

            }
            finish()


        },1000)
    }
}