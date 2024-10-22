package com.example.moviedbtestassignment.repository

import com.example.moviedbtestassignment.db.entity.FavouriteMovie
import com.example.moviedbtestassignment.db.entity.MovieLocal
import kotlinx.coroutines.flow.Flow

interface MovieRepositoryLocal {
    suspend fun getFavourites():List<FavouriteMovie>
    fun getFavouritesFlow():Flow<List<FavouriteMovie>>
    suspend fun saveFavourite(favouriteMovie: FavouriteMovie)
    suspend fun removeFavourite(favouriteMovie: FavouriteMovie)
    suspend fun getLocalMovies(page:Int):List<MovieLocal>
    fun getLocalMovieById(id:Int): Flow<MovieLocal>
    suspend fun saveLocalMovie(movieLocal: MovieLocal)
    suspend fun saveLocalMovies(movieLocals: List<MovieLocal>)


}