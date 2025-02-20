package com.sabanbingul.moviearchive.model

data class Movie(
    val id : Int = 0,
    val title : String? = null,
    val director : String? = null,
    val cover : String? = null,
    val summary : String? = null,
    val genre : String? = null
)