package com.test.movieapplication.screens.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.test.movieapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}