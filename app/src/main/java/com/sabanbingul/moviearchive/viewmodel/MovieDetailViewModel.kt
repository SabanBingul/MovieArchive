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

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val firebaseRef : DatabaseReference = FirebaseDatabase.getInstance().getReference("movies")
    private val movieLiveDataMut = MutableLiveData<Movie>()
    val movieLiveData : LiveData<Movie> get() = movieLiveDataMut

    fun getData(id : Int) {
        firebaseRef.child(id.toString()).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val movie = snapshot.getValue(Movie::class.java)
                movieLiveDataMut.value = movie!!
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("MovieDetailViewModel", "Firebase data fetch cancelled: ${error.message}")
            }


        })
    }

}