package com.test.movieapplication.screens.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.test.movieapplication.R
import com.test.movieapplication.adapter.DetailsFragmentAdapter
import com.test.movieapplication.app.App
import com.test.movieapplication.databinding.FragmentFavoriteBinding
import com.test.movieapplication.network.model.Result
import com.test.movieapplication.screens.fragment.main.MainFragment
import com.test.movieapplication.screens.fragment.main.MainFragmentDirections
import com.test.movieapplication.utils.help.changeFragment
import com.test.movieapplication.utils.mapper.toResult
import com.test.movieapplication.utils.viewmodel.SharedViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavoriteFragment : Fragment() {
    private val binding by lazy { FragmentFavoriteBinding.inflate(layoutInflater) }

    @Inject
    lateinit var favoriteViewModelFactory: FavoriteViewModelFactory
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val favoriteViewModel: FavoriteViewModel by activityViewModels { favoriteViewModelFactory }

    private val detailsFragmentAdapter = DetailsFragmentAdapter { result -> goToDetailsFragment(result) }

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
        initRecyclerViewAndItemAnimator()
        bottomNavigationMenu()
        getDataForAdapter()
        checkIfFavoriteFragmentIsEmpty()
    }

    private fun initRecyclerViewAndItemAnimator() {
        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerView.adapter = detailsFragmentAdapter
        val itemAnimator = binding.recyclerView.itemAnimator
        if (itemAnimator is DefaultItemAnimator) {
            itemAnimator.supportsChangeAnimations = false
        }
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    private fun getDataForAdapter() {
        lifecycleScope.launch {
            favoriteViewModel.getAllFavoriteFilms().observe(viewLifecycleOwner) { listOfFilms ->
                detailsFragmentAdapter.setList(
                    listOfFilms.map { resultDatabaseModel -> resultDatabaseModel.toResult() }
                )
            }
        }
    }

    private fun checkIfFavoriteFragmentIsEmpty() {
        detailsFragmentAdapter.films.observe(activity as LifecycleOwner) {
            if (it == 0) {
                binding.textViewFrgamnetIsEmpty.visibility = View.VISIBLE
            } else {
                binding.textViewFrgamnetIsEmpty.visibility = View.GONE
            }
        }
    }

    private fun goToDetailsFragment(result: Result) {
        sharedViewModel.changeResult(result)
        val direction = MainFragmentDirections.actionFromMainFragmentToDetailsFragment()
        findNavController().navigate(direction)
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