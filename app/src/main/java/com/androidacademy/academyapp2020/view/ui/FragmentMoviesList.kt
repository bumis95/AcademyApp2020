package com.androidacademy.academyapp2020.view.ui

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidacademy.academyapp2020.R
import com.androidacademy.academyapp2020.data.model.Movie
import com.androidacademy.academyapp2020.data.model.loadMovies
import com.androidacademy.academyapp2020.databinding.FragmentMoviesListBinding
import com.androidacademy.academyapp2020.view.adapter.ItemDecorator
import com.androidacademy.academyapp2020.view.adapter.MovieAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class FragmentMoviesList : Fragment(), MovieAdapter.OnItemClickListener {

    private var movieList = listOf<Movie>()

    private var _binding: FragmentMoviesListBinding? = null
    private val binding get() = _binding!!

    private val uiScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.rv_movies).apply {
            when (resources.configuration.orientation) {
                Configuration.ORIENTATION_LANDSCAPE -> {
                    layoutManager = GridLayoutManager(context, 4)
                }
                Configuration.ORIENTATION_PORTRAIT -> {
                    layoutManager = GridLayoutManager(context, 2)
                }
            }
            uiScope.launch {
                movieList = loadMovies(view.context)
                adapter = MovieAdapter(movieList, this@FragmentMoviesList)
                addItemDecoration(ItemDecorator(left = 6, right = 6, bottom = 6, top = 6))
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDestroy() {
        super.onDestroy()
        uiScope.cancel()
    }

    override fun onItemClick() {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, FragmentMoviesDetails())
            addToBackStack(null)
            commit()
        }
    }
}