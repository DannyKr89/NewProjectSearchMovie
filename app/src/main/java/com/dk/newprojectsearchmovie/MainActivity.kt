package com.dk.newprojectsearchmovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dk.newprojectsearchmovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {

        }
    }
}