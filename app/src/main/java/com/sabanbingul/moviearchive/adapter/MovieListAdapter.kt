package com.sabanbingul.moviearchive.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sabanbingul.moviearchive.R
import com.sabanbingul.moviearchive.model.Movie
import com.sabanbingul.moviearchive.view.MovieFragment
import com.sabanbingul.moviearchive.view.MovieFragmentDirections

class MovieListAdapter(private val movieList : ArrayList<Movie>) : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {
    class MovieListViewHolder(val view : View) : RecyclerView.ViewHolder(view) {
        var listItemName : TextView = view.findViewById(R.id.listItemName)
        var listItemPic : ImageView = view.findViewById(R.id.listItemPic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_list, parent, false)
        return MovieListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val movie = movieList[position]
        holder.listItemName.text = movie.title

        Glide.with(holder.view.context)
            .load(movie.cover)
            .apply(RequestOptions().placeholder(R.mipmap.ic_launcher))
            .into(holder.listItemPic)

        holder.view.setOnClickListener{
            val action = MovieFragmentDirections.actionMovieFragmentToMovieDetailFragment(movie.id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    fun updateMovieList(newMovieList : List<Movie>){
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }
}