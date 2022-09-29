package com.test.movieapplication.screens.fragment.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch
import com.test.movieapplication.R
import com.test.movieapplication.databinding.FragmentSettingsBinding
import com.test.movieapplication.screens.fragment.favorite.FavoriteFragment
import com.test.movieapplication.screens.fragment.main.MainFragment
import com.test.movieapplication.utils.context.dataStore
import com.test.movieapplication.utils.help.changeFragment
import com.test.movieapplication.utils.help.recreateSmoothly
import com.test.movieapplication.utils.other.MainConstants.THEME
import com.test.movieapplication.utils.settings.color.checkWhichThemeIsChecked
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {
    private val binding by lazy { FragmentSettingsBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        bottomNavigationMenu()
        binding.defaultButton.setOnClickListener {
            MaterialColorPickerDialog
                .Builder(requireActivity())
                .setColorShape(ColorShape.CIRCLE)
                .setColorSwatch(ColorSwatch._400)
                .setColorRes(resources.getIntArray(R.array.colorPickerColors))
                .setColorListener { color, _ ->
                    val theme = checkWhichThemeIsChecked(color)
                    lifecycleScope.launch { saveData(THEME, theme) }
                    activity?.recreateSmoothly()
                }
                .show()
        }
    }

    private fun bottomNavigationMenu() {
        binding.bottomNavigationView.menu.findItem(R.id.settingsItem).isChecked = true
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
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
    }

    private suspend fun saveData(key: String, value: Int) {
        val dataStoreKey = intPreferencesKey(key)
        context?.dataStore?.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

}