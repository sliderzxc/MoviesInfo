package com.test.movieapplication.screens.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.test.movieapplication.paging.FilmsPagingAdapter
import com.test.movieapplication.adapter.FooterAdapter
import com.test.movieapplication.app.App
import com.test.movieapplication.databinding.FragmentMainBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : Fragment() {
    private val binding by lazy { FragmentMainBinding.inflate(layoutInflater) }
    @Inject
    lateinit var mainViewModuleFactory: MainViewModelFactory
    private lateinit var viewModel: MainViewModel
    private val filmsPagingAdapter = FilmsPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initViewModelAndInject()
        initRecyclerView()
        observeForRecyclerView()
    }

    private fun initViewModelAndInject() {
        (activity?.applicationContext as App).appComponent.inject(this)
        viewModel = ViewModelProvider(this, mainViewModuleFactory)[MainViewModel::class.java]
    }

    private fun initRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerView.adapter = filmsPagingAdapter.withLoadStateFooter( FooterAdapter { filmsPagingAdapter.retry() })
//        binding.reyclerView.setHasFixedSize(true)
    }

    private fun observeForRecyclerView() {
        viewModel.list.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                filmsPagingAdapter.submitData(it)
            }
        }
    }

}