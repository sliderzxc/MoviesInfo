package com.test.movieapplication.screens.fragment.settings

import android.content.Context
import android.content.res.Resources
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.test.movieapplication.R
import com.test.movieapplication.screens.fragment.favorite.FavoriteFragment
import com.test.movieapplication.screens.fragment.main.MainFragment
import com.test.movieapplication.utils.datastore.saveLanguageToDataStore
import com.test.movieapplication.utils.datastore.saveThemeToDataStore
import com.test.movieapplication.utils.help.changeFragment
import com.test.movieapplication.utils.help.recreateSmoothly
import com.test.movieapplication.utils.other.MainConstants
import com.test.movieapplication.utils.settings.color.checkWhichThemeIsChecked
import kotlinx.coroutines.launch

class SettingsViewModel : ViewModel() {

    fun changeFragmentByMenuItem(menuItem: MenuItem, parentFragmentManager: FragmentManager) : Boolean {
        return when(menuItem.itemId) {
            R.id.favoriteItem -> {
                parentFragmentManager.changeFragment(FavoriteFragment())
                true
            }
            R.id.mainItem -> {
                parentFragmentManager.changeFragment(MainFragment())
                true
            }
            else -> true
        }
    }

    fun showMaterialColorPickerDialog(context: Context, resources: Resources, activity: FragmentActivity?) {
        MaterialColorPickerDialog
            .Builder(context)
            .setColorShape(ColorShape.CIRCLE)
            .setColorSwatch(ColorSwatch._400)
            .setColorRes(resources.getIntArray(R.array.colorPickerColors))
            .setColorListener { color, _ ->
                val theme = checkWhichThemeIsChecked(color)
                viewModelScope.launch { saveThemeToDataStore(MainConstants.THEME, theme, context) }
                activity?.recreateSmoothly()
            }
            .show()
    }

    fun showAlertDialogAndSaveLanguageToDataStore(context: Context?, activity: FragmentActivity?) {
        context?.let { it ->
            AlertDialog.Builder(it)
                .setTitle(R.string.choose_language)
                .setItems(MainConstants.arrayOfLanguages) { _, position ->
                    viewModelScope.launch { saveLanguageToDataStore(MainConstants.LANGUAGE, MainConstants.arrayOfLanguageCodes[position], it) }
                    activity?.recreateSmoothly()
                }
                .show()
        }
    }

}