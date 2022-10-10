package com.test.movieapplication.utils.help

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.test.movieapplication.R
import java.util.*

val Context.dataStoreSettings by preferencesDataStore("settings")

fun FragmentManager.changeFragment(fragment: Fragment) {
    this.beginTransaction().replace(R.id.main_activity, fragment).commit()
}

fun Activity.recreateSmoothly() {
    startActivity(Intent(this, this::class.java))
    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    finish()
}

fun Activity.setLocale(languageCode: String) {
    val locale = Locale(languageCode)
    val resources = this.resources
    val config = resources.configuration
    config.setLocale(locale)
    resources.updateConfiguration(config, resources.displayMetrics)
}