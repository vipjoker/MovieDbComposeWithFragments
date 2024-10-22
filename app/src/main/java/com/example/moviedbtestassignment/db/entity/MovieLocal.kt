package com.example.moviedbtestassignment.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.moviedbtestassignment.api.response.MovieRemote
import com.example.moviedbtestassignment.ui.model.MovieDomain

@Entity(tableName = "movie")

data class MovieLocal(

    @PrimaryKey()
    val id: Int,
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

    var updatedAt: Long,
    var page: Int,
    var totalPages: Int,
    var position:Int
)

fun MovieLocal.toMovieDomain(isFavourite: Boolean) =
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



