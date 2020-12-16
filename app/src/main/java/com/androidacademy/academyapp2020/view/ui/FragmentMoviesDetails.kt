package com.androidacademy.academyapp2020.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.androidacademy.academyapp2020.R
import com.androidacademy.academyapp2020.data.model.Movie
import com.androidacademy.academyapp2020.databinding.FragmentMoviesDetailsBinding
import com.androidacademy.academyapp2020.view.adapter.ActorAdapter
import com.androidacademy.academyapp2020.view.adapter.ItemDecorator

const val ARG_MOVIE = "movie_param"

class FragmentMoviesDetails : Fragment() {

    private var movie: Movie? = null

    private var _binding: FragmentMoviesDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = arguments?.getParcelable(ARG_MOVIE)
        Log.i("FragmentMoviesList", movie.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        view.findViewById<RecyclerView>(R.id.rv_actors).apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = ActorAdapter(movie!!.actors)
            addItemDecoration(ItemDecorator(left = 4, right = 4))
        }
        view.findViewById<TextView>(R.id.tv_back).setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initViews() {
        binding.apply {
            tvTitle.text = movie?.title
            ivPreview.load(movie?.backdrop)
            tvMovieGenres.text = movie?.genres?.joinToString { it.name }
            ratingBar.rating = movie?.ratings!! / 2f
            tvMovieNumberOfRatings.text = getString(R.string.review, movie?.numberOfRatings.toString())
            tvDescription.text = movie?.overview
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(movie: Movie) =
            FragmentMoviesDetails().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_MOVIE, movie)
                }
            }
    }
}