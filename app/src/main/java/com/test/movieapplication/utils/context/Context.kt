package com.test.movieapplication.utils.context

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.dataStoreSettings by preferencesDataStore("settings")