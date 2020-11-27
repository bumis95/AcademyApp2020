package com.androidacademy.academyapp2020.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidacademy.academyapp2020.view.adapter.FilmAdapter
import com.androidacademy.academyapp2020.view.adapter.ItemDecorator
import com.androidacademy.academyapp2020.R

class FragmentMoviesList : Fragment(), FilmAdapter.OnItemClickListener {

    private val filmAdapter = FilmAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movies_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = filmAdapter
            addItemDecoration(ItemDecorator(left = 6, right = 6, bottom = 6, top = 6))
        }
    }

    override fun onItemClick() {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, FragmentMoviesDetails())
            addToBackStack(null)
            commit()
        }
    }
}