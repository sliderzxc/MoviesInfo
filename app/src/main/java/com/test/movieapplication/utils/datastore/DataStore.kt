package com.test.movieapplication.utils.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.test.movieapplication.utils.context.dataStoreSettings
import kotlinx.coroutines.flow.first

suspend fun saveThemeToDataStore(key: String, value: Int, context: Context?) {
    val dataStoreKey = intPreferencesKey(key)
    context?.dataStoreSettings?.edit { settings ->
        settings[dataStoreKey] = value
    }
}

suspend fun saveLanguageToDataStore(key: String, value: String, context: Context?) {
    val dataStoreKey = stringPreferencesKey(key)
    context?.dataStoreSettings?.edit { settings ->
        settings[dataStoreKey] = value
    }
}

suspend fun getLanguageFromDataStore(key: String, context: Context?): String? {
    val dataStoreKey = stringPreferencesKey(key)
    val preferences = context?.dataStoreSettings?.data?.first()
    return preferences?.get(dataStoreKey)
}

suspend fun getThemeFromDataStore(key: String, context: Context?): Int? {
    val dataStoreKey = intPreferencesKey(key)
    val preferences = context?.dataStoreSettings?.data?.first()
    return preferences?.get(dataStoreKey)
}