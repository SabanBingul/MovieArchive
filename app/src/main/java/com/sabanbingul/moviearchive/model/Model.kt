package com.sabanbingul.moviearchive.model

data class Movie(
    val id : Int = 0,
    val title : String?,
    val director : String?,
    val cover : String?,
    val summary : String?,
    val genre : String?
)