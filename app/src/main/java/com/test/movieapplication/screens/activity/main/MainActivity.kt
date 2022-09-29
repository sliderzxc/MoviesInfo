package com.test.movieapplication.screens.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.test.movieapplication.databinding.ActivityMainBinding
import com.test.movieapplication.utils.context.dataStore
import com.test.movieapplication.utils.other.MainConstants.THEME
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            val theme = getData(THEME)
            theme?.let { setTheme(it) }
            setContentView(binding.root)
        }
    }

    private suspend fun getData(key: String): Int? {
        val dataStoreKey = intPreferencesKey(key)
        val preferences = dataStore.data.first()
        return preferences[dataStoreKey]
    }

}