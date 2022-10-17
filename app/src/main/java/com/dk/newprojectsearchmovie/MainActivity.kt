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

        val data = Data("Danya", 33)
        val data2 = data.copy()

        binding.objectTV.text = data2.toString()

        printCycle("Hello Kotlin")

        binding.btn.setOnClickListener {
            binding.apply {
                dataName.text = data.name
                dataAge.text = data.age.toString()
            }
        }
    }
}

fun printCycle(str: String) {
    for (i in str.indices){
        println(str[i])
    }
    for (i in 0 until  10){
        println("$str in  $i until 10")
    }
    for (b in 6 downTo 0 step  3){
        println("$str by step 3")
    }
    str.forEach {
        println(it)
    }
}