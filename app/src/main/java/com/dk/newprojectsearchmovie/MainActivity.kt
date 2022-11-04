package com.dk.newprojectsearchmovie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dk.newprojectsearchmovie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}