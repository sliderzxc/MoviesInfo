package com.test.movieapplication.screens.fragment.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.test.movieapplication.R
import com.test.movieapplication.app.App
import com.test.movieapplication.databinding.FragmentSettingsBinding
import com.test.movieapplication.screens.fragment.main.MainFragmentDirections
import com.test.movieapplication.utils.datastore.saveLanguageToDataStore
import com.test.movieapplication.utils.datastore.saveThemeToDataStore
import com.test.movieapplication.utils.help.recreateSmoothly
import com.test.movieapplication.utils.other.MainConstants.ENGLISH_CODE
import com.test.movieapplication.utils.other.MainConstants.LANGUAGE
import com.test.movieapplication.utils.other.MainConstants.THEME
import kotlinx.coroutines.launch

class SettingsFragment : Fragment() {
    private val binding by lazy { FragmentSettingsBinding.inflate(layoutInflater) }
    private val settingsViewModel: SettingsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        inject()
        bottomNavigationMenu()
        changeColorButtonListener()
        defaultColorThemeApplicationButtonListener()
        goToAboutFragmentButtonListener()
        changeLanguageButtonListener()
        defaultLanguageButtonListener()
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    private fun changeLanguageButtonListener() {
        binding.btnChangeLanguage.setOnClickListener {
            settingsViewModel.showAlertDialogAndSaveLanguageToDataStore(context, activity)
        }
    }

    private fun defaultColorThemeApplicationButtonListener() {
        binding.btnDefaultColor.setOnClickListener {
            val theme = R.style.Theme_MovieApplication_Main
            lifecycleScope.launch { saveThemeToDataStore(THEME, theme, context) }
            activity?.recreateSmoothly()
        }
    }

    private fun changeColorButtonListener() {
        binding.btnChangeColor.setOnClickListener {
            settingsViewModel.showMaterialColorPickerDialog(requireActivity(), resources, activity)
        }
    }

    private fun goToAboutFragmentButtonListener() {
        binding.btnAboutTheApplication.setOnClickListener {
            val direction = MainFragmentDirections.actionFromMainFragmentToAboutFragment()
            findNavController().navigate(direction)
        }
    }

    private fun defaultLanguageButtonListener() {
        binding.btnDefaultLanguage.setOnClickListener {
            lifecycleScope.launch { saveLanguageToDataStore(LANGUAGE, ENGLISH_CODE, context) }
            activity?.recreateSmoothly()
        }
    }

    private fun bottomNavigationMenu() {
        binding.bottomNavigationView.menu.findItem(R.id.settingsItem).isChecked = true
        binding.bottomNavigationView.setOnItemSelectedListener { menuItem ->
            settingsViewModel.changeFragmentByMenuItem(menuItem, parentFragmentManager)
        }
    }
}