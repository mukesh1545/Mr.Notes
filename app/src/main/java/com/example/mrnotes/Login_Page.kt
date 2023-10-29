package com.example.mrnotes

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.media3.common.util.Log
import com.example.mrnotes.databinding.ActivityLoginPageBinding
import com.google.firebase.auth.FirebaseAuth

class Login_Page : AppCompatActivity() {
    private lateinit var binding: ActivityLoginPageBinding
    private lateinit var firebaseAuth: FirebaseAuth
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth = FirebaseAuth.getInstance()
        var Email = binding.LoginEmailId.text.toString()
        var Password = binding.LoginPassword.text.toString()

        ///sigin
        binding.SignUpBtn.setOnClickListener {
            startActivity(Intent(this,create_account::class.java))
        }
        /// checked login it will check
        binding.btn.setOnClickListener {
            Toast.makeText(this,"clicked",Toast.LENGTH_SHORT).show()
            var Email = binding.LoginEmailId.text.toString()
            var Password = binding.LoginPassword.text.toString()
            var userId=firebaseAuth.currentUser?.uid.toString()
            var user=FirebaseAuth.getInstance().currentUser

            ///this it will check whther the user email id and password or empty or not
            if (Email.isNotEmpty() && Password.isNotEmpty()) {

                //it checked whether the email id is correct or not
                if (Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {

                    ///this will turn on the progress bar to true
                    ProgressBar(true)

                    /// chaeck whether the user given details or not
                    firebaseAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener {

                        //this check if the user entered deatils or correct it will return true
                        if (it.isSuccessful) {
                            if(firebaseAuth.currentUser!!.isEmailVerified()) {
                                android.util.Log.d("mukesh","${firebaseAuth.currentUser!!.isEmailVerified()}")
                                ProgressBar(false)
                                var userId = firebaseAuth.currentUser?.uid.toString()
                                Toast.makeText(this, "${userId}", Toast.LENGTH_SHORT).show()
                              var intent=Intent(this,NotePage::class.java)
                                intent.putExtra("id", userId)
                                startActivity(intent)
                                finish()



                            }
                            else
                            {
                                ProgressBar(false)
                                Toast.makeText(this,"Email is not verify",Toast.LENGTH_SHORT).show()

                            }
                        }
                        else
                        {
                            ProgressBar(false)
                            Toast.makeText(this, "Invalid User", Toast.LENGTH_SHORT).show()
                        }
                    }

                }
                else
                {
                    binding.LoginEmailId.setError("Email Id Invalid")
                    Toast.makeText(this, "Enter the valid Email Id", Toast.LENGTH_SHORT).show()
                }

            }
            else
            {
                Toast.makeText(this,"Enter the valid Email && Password",Toast.LENGTH_SHORT).show()
            }

        }
    }


    private fun ProgressBar(inProgress: Boolean) {

        if (inProgress) {
            binding.ProgrssBar.visibility = View.VISIBLE
            binding.btn.visibility = View.GONE
        } else {
            binding.ProgrssBar.visibility = View.GONE
            binding.btn.visibility = View.VISIBLE
        }
    }

}