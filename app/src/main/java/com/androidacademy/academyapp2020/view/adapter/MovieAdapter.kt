package com.androidacademy.academyapp2020.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.androidacademy.academyapp2020.R
import com.androidacademy.academyapp2020.data.model.Movie
import com.androidacademy.academyapp2020.databinding.ViewHolderMovieBinding

class MovieAdapter(
    private val movieList: List<Movie>,
    private val listener: OnMovieClickListener
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_holder_movie,
                parent,
                false
            )
        )

    override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) {
        viewHolder.bind(movieList[position], listener)
    }

    override fun getItemCount(): Int = movieList.size

    class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding = ViewHolderMovieBinding.bind(view)

        fun bind(movie: Movie, listener: OnMovieClickListener) {
            binding.apply {
                tvMovieTitle.text = movie.title
//                 TODO make class loader
                ivMoviePoster.load(movie.poster) {
                    transformations(RoundedCornersTransformation(topLeft = 16f, topRight = 16f))
                }
                ratingBar.rating = movie.ratings / 2f
                tvMovieNumberOfRatings.text =
                    itemView.context.getString(R.string.review, movie.numberOfRatings.toString())
                tvAge.text = itemView.context.getString(R.string.age, movie.minimumAge.toString())
                tvMovieRuntime.text =
                    itemView.context.getString(R.string.duration, movie.runtime.toString())
                tvMovieGenres.text = movie.genres.joinToString { it.name }
                itemView.setOnClickListener { listener.onMovieClick(movie) }
            }
        }
    }

    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }
}