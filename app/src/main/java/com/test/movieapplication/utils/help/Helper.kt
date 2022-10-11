package com.test.movieapplication.utils.help

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.test.movieapplication.R
import com.test.movieapplication.utils.datastore.getIsAppStartedForTheFirstTime
import com.test.movieapplication.utils.datastore.saveIsAppStartedForTheFirstTime
import com.test.movieapplication.utils.datastore.saveLanguageToDataStore
import com.test.movieapplication.utils.datastore.saveThemeToDataStore
import com.test.movieapplication.utils.other.MainConstants.ENGLISH
import com.test.movieapplication.utils.other.MainConstants.IS_FIRST_TIME_STARTED
import com.test.movieapplication.utils.other.MainConstants.LANGUAGE
import com.test.movieapplication.utils.other.MainConstants.THEME
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

suspend fun Activity.isAppStartedForTheFirstTime() {
    val value = getIsAppStartedForTheFirstTime(IS_FIRST_TIME_STARTED, this)
    if (value == false || value == null) {
        saveLanguageToDataStore(LANGUAGE, ENGLISH, this)
        saveThemeToDataStore(THEME, R.style.Theme_MovieApplication_Main, this)
        saveIsAppStartedForTheFirstTime(IS_FIRST_TIME_STARTED, true, this)
    }
}