package com.test.movieapplication.screens.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.test.movieapplication.databinding.ActivityMainBinding
import com.test.movieapplication.utils.datastore.getLanguageFromDataStore
import com.test.movieapplication.utils.datastore.getThemeFromDataStore
import com.test.movieapplication.utils.help.isAppStartedForTheFirstTime
import com.test.movieapplication.utils.help.setLocale
import com.test.movieapplication.utils.other.MainConstants.LANGUAGE
import com.test.movieapplication.utils.other.MainConstants.THEME
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            isAppStartedForTheFirstTime()
            val language = getLanguageFromDataStore(LANGUAGE, this@MainActivity)
            val theme = getThemeFromDataStore(THEME, this@MainActivity)
            language?.let { setLocale(it) }
            theme?.let { setTheme(it) }
            setContentView(binding.root)
        }
    }

}