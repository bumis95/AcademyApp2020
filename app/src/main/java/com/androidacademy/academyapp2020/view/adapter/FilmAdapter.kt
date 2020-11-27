package com.androidacademy.academyapp2020.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidacademy.academyapp2020.data.model.Film
import com.androidacademy.academyapp2020.R

class FilmAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<FilmAdapter.FilmHolder>() {

    private val filmList = listOf(
        Film(
            13,
            R.drawable.ic_like_no,
            R.drawable.movie_1_poster,
            R.string.tag,
            4,
            125,
            R.string.name,
            137
        ),
        Film(
            16,
            R.drawable.ic_like_yes,
            R.drawable.movie_2_poster,
            R.string.tag,
            5,
            98,
            R.string.name,
            97
        ),
        Film(
            13,
            R.drawable.ic_like_no,
            R.drawable.movie_3_poster,
            R.string.tag,
            4,
            38,
            R.string.name,
            102
        ),
        Film(
            13,
            R.drawable.ic_like_no,
            R.drawable.movie_4_poster,
            R.string.tag,
            5,
            74,
            R.string.name,
            120
        ),
    )

    interface OnItemClickListener {
        fun onItemClick()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder =
        FilmHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.movie_list_item,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.bind(filmList[position], listener)
    }

    override fun getItemCount(): Int = filmList.size


    inner class FilmHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(film: Film, listener: OnItemClickListener) {
            itemView.findViewById<TextView>(R.id.tv_age).text =
                itemView.context.getString(R.string.age, film.pegi.toString())
            itemView.findViewById<ImageView>(R.id.iv_like).setImageResource(film.isLike)
            itemView.findViewById<ImageView>(R.id.iv_poster).setImageResource(film.imageUrl)
            itemView.findViewById<TextView>(R.id.tv_tag).text =
                itemView.context.getText(R.string.tag)
//            itemView.findViewById<TextView>(R.id.tv_age).text = film.pegi  rate
            itemView.findViewById<TextView>(R.id.tv_review).text =
                itemView.context.getString(R.string.review, film.numberOfReviews.toString())
            itemView.findViewById<TextView>(R.id.tv_film_name).text =
                itemView.context.getText(R.string.name)
            itemView.findViewById<TextView>(R.id.tv_duration).text =
                itemView.context.getString(R.string.duration, film.duration.toString())
            itemView.setOnClickListener { listener.onItemClick() }
        }
    }
}