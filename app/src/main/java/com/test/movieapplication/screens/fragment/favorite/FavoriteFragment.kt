package com.test.movieapplication.screens.fragment.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.movieapplication.R
import com.test.movieapplication.databinding.FragmentFavoriteBinding
import com.test.movieapplication.screens.fragment.main.MainFragment
import com.test.movieapplication.utils.help.changeFragment

class FavoriteFragment : Fragment() {
    private val binding by lazy { FragmentFavoriteBinding.inflate(layoutInflater) }

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
    }

    private fun bottomNavigationMenu() {
        binding.bottomNavigationMenu.menu.findItem(R.id.favoriteItem).isChecked = true
        binding.bottomNavigationMenu.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.mainItem -> {
                    parentFragmentManager.changeFragment(MainFragment())
                    true
                }
                else -> false
            }
        }
    }
}