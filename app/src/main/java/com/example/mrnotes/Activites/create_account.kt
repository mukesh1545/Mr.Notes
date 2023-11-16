package com.example.mrnotes.Activites

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import com.example.mrnotes.databinding.CreateAccountBinding
import com.google.firebase.auth.FirebaseAuth

class create_account : AppCompatActivity() {
    private lateinit var binding: CreateAccountBinding
    lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = CreateAccountBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //firebase
        firebaseAuth = FirebaseAuth.getInstance()
        var Email = binding.TextViewEmailId.text.toString()

        binding.CreateAccountBtn.setOnClickListener {
            validation()
        }

        binding.Loginbtn.setOnClickListener {
            var intent= Intent(this, Login_Page::class.java)
            startActivity(intent)
            Toast.makeText(this,"Login_Page",Toast.LENGTH_SHORT).show()

        }

    }
    private fun createFireAcount(Email: String, Password: String) {
        ProgressBar(true)
        var user=firebaseAuth.currentUser?.uid
        firebaseAuth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener {
            if (it.isSuccessful) {
                sendEmailVerification()
                ProgressBar(false)
                Toast.makeText(this, "Registerd Sucessfully", Toast.LENGTH_SHORT).show()
                binding.TextViewPassword.text.clear()
                binding.TextViewEmailId.text.clear()
                binding.TextViewConfrimPassword.text.clear()
            } else {
                ProgressBar(false)
                binding.TextViewPassword.text.clear()
                binding.TextViewEmailId.text.clear()
                binding.TextViewConfrimPassword.text.clear()

                Toast.makeText(this, "  ${Email} is AlreadyExits", Toast.LENGTH_SHORT).show()
            }
        }


    }

    private fun sendEmailVerification() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            user.sendEmailVerification().addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(this, "verifcation Email is send ", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }


    private fun validation() {

        var Email = binding.TextViewEmailId.text.toString()
        var Password = binding.TextViewPassword.text.toString()
        var cPassword = binding.TextViewConfrimPassword.text.toString()


        ///Email Validation
        if (Email.isEmpty() && Password.isEmpty()) {
            Toast.makeText(this, "Please Enter the Email id and Password", Toast.LENGTH_SHORT)
                .show()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            binding.TextViewEmailId.setError("Enter the valid Email id")
           // binding.TextViewEmailId.setTextColor(ContextCompat.getColor(this, R.color.Error))

            return
        }
        if(Password.length<=6)
        {
            binding.TextViewPassword.setError("Enter the valid Password")
            return
        }
        if(cPassword!=Password)
        {
            binding.TextViewConfrimPassword.setError("Confirm Password Not matched")
            return

        }
        createFireAcount(Email,Password)

    }


    private fun ProgressBar(inProgress: Boolean) {

        if (inProgress) {
            binding.ProgrssBar.visibility = View.VISIBLE
            binding.CreateAccountBtn.visibility = View.GONE
        } else {
            binding.ProgrssBar.visibility = View.GONE
            binding.CreateAccountBtn.visibility = View.VISIBLE
        }
    }






}
