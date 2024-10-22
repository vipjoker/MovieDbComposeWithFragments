package com.example.moviedbtestassignment.ui.model

import com.example.moviedbtestassignment.api.response.toMovieDomain
import com.example.moviedbtestassignment.db.entity.FavouriteMovie
import com.google.gson.annotations.SerializedName

data class MovieDomain (
    var id: Int,
    var adult: Boolean,
    var backdropPath: String? = null,

    var genreIds: List<Int>,

    var originalLanguage: String,

    var originalTitle: String,

    var overview: String,

    var popularity: Double,

    var posterPath: String,

    var releaseDate: String,

    var title: String,

    var video: Boolean,

    var voteAverage: Double,

    var voteCount: Int,

    var isFavourite:Boolean
)



fun MovieDomain.toMovieFavourite():FavouriteMovie{
    return FavouriteMovie(
        id = this.id,
        adult = this.adult,
        backdropPath = this.backdropPath,
        genreIds = this.genreIds,
        originalLanguage = this.originalLanguage,
        originalTitle = this.originalTitle,
        overview = this.overview,
        popularity = this.popularity,
        posterPath = this.posterPath,
        releaseDate = this.releaseDate,
        title = this.title,
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
    )
}