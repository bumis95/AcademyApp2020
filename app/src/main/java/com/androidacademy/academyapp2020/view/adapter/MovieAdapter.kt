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
    private val listener: OnItemClickListener
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

        fun bind(movie: Movie, listener: OnItemClickListener) {

            binding.apply {
                tvMovieTitle.text = movie.title
//                 TODO make class loader
                ivMoviePoster.load(movie.poster) {
                    transformations(RoundedCornersTransformation(topLeft = 16f, topRight = 16f))
                }
                //stars
                tvMovieNumberOfRatings.text = movie.numberOfRatings.toString()
//                minage
                tvMovieRuntime.text = movie.runtime.toString()
//                tvMovieGenres.text = movie.genres.joinToString(separator = ", ")
                itemView.setOnClickListener { listener.onItemClick() }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick()
    }
}