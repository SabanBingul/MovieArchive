package com.sabanbingul.moviearchive.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.sabanbingul.moviearchive.model.Movie

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val firebaseRef : DatabaseReference = FirebaseDatabase.getInstance().getReference("movies")
    private val movieListMut = MutableLiveData<List<Movie>>()
    val movieList : LiveData<List<Movie>> get() = movieListMut

    init {
        fetchMovies()
    }

    private fun fetchMovies(){
        firebaseRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val movies = mutableListOf<Movie>()
                if (snapshot.exists()){
                    for (movieSnap in snapshot.children){
                        val movie = movieSnap.getValue(Movie::class.java)
                        movie?.let { movies.add(it) }
                    }
                }
                movieListMut.value = movies
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

}