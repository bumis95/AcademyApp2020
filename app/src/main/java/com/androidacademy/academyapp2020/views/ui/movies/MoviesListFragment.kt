package com.androidacademy.academyapp2020.views.ui.movies

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.androidacademy.academyapp2020.R
import com.androidacademy.academyapp2020.databinding.FragmentMoviesListBinding
import com.androidacademy.academyapp2020.viewmodelfactory.ViewModelFactory
import com.androidacademy.academyapp2020.views.adapters.ItemDecorator
import com.androidacademy.academyapp2020.views.adapters.MoviePagingDataAdapter
import com.androidacademy.academyapp2020.views.ui.details.MovieDetailsFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MoviesListFragment : Fragment(), MoviePagingDataAdapter.OnMovieClickListener {

    private val viewModel: MoviesListViewModel by viewModels { ViewModelFactory(requireContext()) }

    private val movieAdapter = MoviePagingDataAdapter(this)

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)

        initMovieRecyclerView()
        initObservers()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initMovieRecyclerView() {
        binding.rvMovies.apply {
            when (resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    layoutManager = GridLayoutManager(context, 4)
                }
                Configuration.ORIENTATION_PORTRAIT -> {
                    layoutManager = GridLayoutManager(context, 2)
                }
            }
            adapter = movieAdapter
            addItemDecoration(ItemDecorator(left = 6, right = 6, bottom = 6, top = 6))
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.fetchMovies().collectLatest {
                movieAdapter.submitData(it)
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            movieAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.pbMoviesList.isVisible =
                    loadStates.refresh is LoadState.Loading || loadStates.append is LoadState.Loading
            }
        }
    }

    override fun onMovieClick(movieId: Int) {
        Log.d("FRAGMENT", "Click on movie with id=$movieId")
        requireActivity().supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, MovieDetailsFragment.newInstance(movieId))
            addToBackStack(null)
            commit()
        }
    }
}