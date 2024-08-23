package com.sabanbingul.moviearchive.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.sabanbingul.moviearchive.R
import com.sabanbingul.moviearchive.databinding.FragmentMovieBinding
import com.sabanbingul.moviearchive.databinding.FragmentMovieDetailBinding
import com.sabanbingul.moviearchive.viewmodel.MovieDetailViewModel
import com.sabanbingul.moviearchive.viewmodel.MovieViewModel


class MovieDetailFragment : Fragment() {

    private lateinit var binding: FragmentMovieDetailBinding
    private var id : Int = 0
    private lateinit var viewModel: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        arguments?.let {
            id = MovieDetailFragmentArgs.fromBundle(it).id
        }
        viewModel.getData(id)
        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.movieLiveData.observe(viewLifecycleOwner, Observer { movie ->
            movie?.let {
                binding.title.text = it.title
                binding.genre.text = it.genre
                binding.director.text = it.director
                binding.summary.text = it.summary

                Glide.with(this)
                    .load(it.cover)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .into(binding.cover)
            }
        })
    }

}