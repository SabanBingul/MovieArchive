package com.sabanbingul.moviearchive.viewmodel

import android.app.Application
import android.util.Log
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

    private val firebaseRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("movies")
    private val movieListMut = MutableLiveData<List<Movie>>()
    val movieList: LiveData<List<Movie>> get() = movieListMut

    init {
        fetchMovies()
    }

    private fun fetchMovies() {
        firebaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val movies = mutableListOf<Movie>()
                if (snapshot.exists()) {
                    for (movieSnap in snapshot.children) {
                        val movie = movieSnap.getValue(Movie::class.java)
                        if (movie != null) {
                            movies.add(movie)
                        } else {
                            Log.w("MovieViewModel", "Movie is null for snapshot: $movieSnap")
                        }
                    }
                    Log.d("MovieViewModel", "Movies fetched: ${movies.size}")
                } else {
                    Log.d("MovieViewModel", "No movies found in the database")
                }
                // Use postValue to update LiveData on the main thread
                movieListMut.postValue(movies)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MovieViewModel", "Firebase data fetch cancelled: ${error.message}")
            }
        })
    }
}
