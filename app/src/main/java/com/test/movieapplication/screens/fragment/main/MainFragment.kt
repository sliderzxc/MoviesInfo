package com.test.movieapplication.screens.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.test.movieapplication.R
import com.test.movieapplication.adapter.FooterAdapter
import com.test.movieapplication.app.App
import com.test.movieapplication.databinding.FragmentMainBinding
import com.test.movieapplication.network.model.Result
import com.test.movieapplication.paging.FilmsPagingAdapter
import com.test.movieapplication.screens.fragment.favorite.FavoriteFragment
import com.test.movieapplication.utils.help.changeFragment
import com.test.movieapplication.utils.viewmodel.SharedViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment() {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    @Inject
    lateinit var mainViewModuleFactory: MainViewModelFactory
    private val mainViewModel: MainViewModel by activityViewModels { mainViewModuleFactory }
    private val sharedViewModel: SharedViewModel by activityViewModels()
    private val filmsPagingAdapter = FilmsPagingAdapter { result -> goToDetailsFragment(result) }

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
        initRecyclerView()
        observeForRecyclerView()
        bottomNavigationMenu()
    }

    private fun inject() {
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerView.adapter = filmsPagingAdapter.withLoadStateFooter(FooterAdapter { filmsPagingAdapter.retry() })
    }

    private fun observeForRecyclerView() {
        mainViewModel.list.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                filmsPagingAdapter.submitData(it)
            }
        }
    }

    private fun goToDetailsFragment(result: Result) {
        sharedViewModel.changeResult(result)
        val direction = MainFragmentDirections.actionFromMainFragmentToDetailsFragment()
        findNavController().navigate(direction)
    }

    private fun bottomNavigationMenu() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.favoriteItem -> {
                    parentFragmentManager.changeFragment(FavoriteFragment())
                    true
                }
                else -> true
            }
        }
    }
}