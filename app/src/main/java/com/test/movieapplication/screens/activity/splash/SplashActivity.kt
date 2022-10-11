package com.test.movieapplication.screens.activity.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.test.movieapplication.databinding.ActivitySplashBinding
import com.test.movieapplication.screens.activity.main.MainActivity
import com.test.movieapplication.utils.datastore.getThemeFromDataStore
import com.test.movieapplication.utils.other.MainConstants.THEME
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lifecycleScope.launch {
            lifecycleScope.launch {
                val theme = getThemeFromDataStore(THEME, this@SplashActivity)
                theme?.let { setTheme(it) }
            }
            delay(2000)
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }

}