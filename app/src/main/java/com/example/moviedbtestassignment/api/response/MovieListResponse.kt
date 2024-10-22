package com.example.moviedbtestassignment.api.response

import com.example.moviedbtestassignment.db.entity.MovieLocal
import com.example.moviedbtestassignment.ui.model.MovieDomain
import com.google.gson.annotations.SerializedName

class MovieListResponse {
    var page: Int = 0
    var results: List<MovieRemote> = listOf()
    var total_pages: Int = 0
    var total_results: Int = 0

}

data class MovieRemote (
    @SerializedName("id")
    var id: Int,
    @SerializedName("adult")
    var adult: Boolean,
    @SerializedName("backdrop_path")
    var backdropPath: String? = null,

    @SerializedName("genre_ids")
    var genreIds: List<Int>,

    @SerializedName("original_language")
    var originalLanguage: String,

    @SerializedName("original_title")
    var originalTitle: String,

    @SerializedName("overview")
    var overview: String,

    @SerializedName("popularity")
    var popularity: Double,

    @SerializedName("poster_path")
    var posterPath: String,

    @SerializedName("release_date")
    var releaseDate: String,

    @SerializedName("title")
    var title: String,

    @SerializedName("video")
    var video: Boolean,

    @SerializedName("vote_average")
    var voteAverage: Double,

    @SerializedName("vote_count")
    var voteCount: Int
)

fun MovieRemote.toMovieDomain(isFavourite: Boolean) =
    MovieDomain(
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
        isFavourite = isFavourite
    )

fun MovieRemote.toMovieLocal(page: Int, totalPages:Int, updatedAt:Long, position:Int) =
    MovieLocal(
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
        page = page,
        updatedAt = updatedAt,
        totalPages = totalPages,
        position = position

    )