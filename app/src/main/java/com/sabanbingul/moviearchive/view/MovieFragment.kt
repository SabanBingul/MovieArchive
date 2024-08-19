package com.sabanbingul.moviearchive.view

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.GridLayoutManager
import com.sabanbingul.moviearchive.R
import com.sabanbingul.moviearchive.adapter.MovieListAdapter
import com.sabanbingul.moviearchive.databinding.FragmentMovieBinding
import com.sabanbingul.moviearchive.viewmodel.MovieViewModel

class MovieFragment : Fragment() {

    private lateinit var binding : FragmentMovieBinding
    private lateinit var viewModel : MovieViewModel
    private lateinit var movieListAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieViewModel::class.java)
        movieListAdapter = MovieListAdapter(arrayListOf())
        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.movieListRV.layoutManager = gridLayoutManager
        binding.movieListRV.adapter = movieListAdapter
        viewModel.movieList.observe(viewLifecycleOwner, Observer { movies ->
            movies?.let { movieListAdapter.updateMovieList(it) }
        })

    }
}