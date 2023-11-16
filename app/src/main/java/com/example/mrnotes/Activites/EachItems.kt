package com.example.mrnotes.Activites

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mrnotes.databinding.EachtemsBinding


class EachItems : AppCompatActivity() {
    private lateinit var binding: EachtemsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EachtemsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
