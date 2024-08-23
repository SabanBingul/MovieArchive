package com.sabanbingul.moviearchive.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselSnapHelper
import com.sabanbingul.moviearchive.R
import com.sabanbingul.moviearchive.adapter.CarouselAdapter
import com.sabanbingul.moviearchive.adapter.MovieAdapter
import com.sabanbingul.moviearchive.databinding.FragmentMainBinding
import com.sabanbingul.moviearchive.viewmodel.MainViewModel


class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel : MainViewModel
    private lateinit var movieAdapter : MovieAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        movieAdapter = MovieAdapter(arrayListOf())
        binding.movieSliderRV.layoutManager = LinearLayoutManager(context)
        binding.movieSliderRV.adapter = movieAdapter

        viewModel.movieList.observe(viewLifecycleOwner, Observer { movies ->
            movies?.let { movieAdapter.updateMovieList(it) }
        })

        carouselActivity()

    }

    private fun carouselActivity(){
        binding.carouselRV.setHasFixedSize(true)
        binding.carouselRV.layoutManager = CarouselLayoutManager()
        CarouselSnapHelper().attachToRecyclerView(binding.carouselRV)

        val imageList = mutableListOf<Int>()
        imageList.add(R.drawable.imdb1)
        imageList.add(R.drawable.imdb2)
        imageList.add(R.drawable.imdb3)
        imageList.add(R.drawable.imdb4)
        imageList.add(R.drawable.imdb5)

        
        val carouselAdapter = CarouselAdapter(imageList)
        binding.carouselRV.adapter = carouselAdapter
    }

}