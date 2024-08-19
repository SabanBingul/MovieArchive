package com.sabanbingul.moviearchive.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sabanbingul.moviearchive.R
import com.sabanbingul.moviearchive.model.Movie
import com.sabanbingul.moviearchive.view.MainFragmentDirections

class MovieAdapter(private val movieList : ArrayList<Movie>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    class MovieViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        var itemPic : ImageView = view.findViewById(R.id.itemPic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]

        Glide.with(holder.view.context)
            .load(movie.cover)
            .apply(RequestOptions().placeholder(R.mipmap.ic_launcher))
            .into(holder.itemPic)

        holder.view.setOnClickListener{
            val action = MainFragmentDirections.actionMainFragmentToMovieDetailFragment(movie.id)
            Navigation.findNavController(it).navigate(action)
        }
    }
}