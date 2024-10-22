package com.example.moviedbtestassignment.repository

import com.example.moviedbtestassignment.db.entity.toMovieDomain
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.moviedbtestassignment.db.entity.FavouriteMovie
import com.example.moviedbtestassignment.paging.MoviePagingSource
import com.example.moviedbtestassignment.ui.model.MovieDomain
import kotlinx.coroutines.flow.*


class MovieRepository(
    private val remoteRepository: MovieRepositoryRemote,
    private val localRepository: MovieRepositoryLocal
) {

    fun getMoviesWithFavourites(): Flow<PagingData<MovieDomain>> =
        Pager(PagingConfig(pageSize = 20, prefetchDistance = 10, enablePlaceholders = false)) {
            MoviePagingSource(remoteRepository, localRepository)
        }.flow


    suspend fun makeFavorite(favouriteMovie: FavouriteMovie, isFavourite: Boolean) {

        if (isFavourite) {
            localRepository.saveFavourite(favouriteMovie)
        } else {
            localRepository.removeFavourite(favouriteMovie)
        }
    }

    fun getMovieById(id:Int) =

        localRepository.getLocalMovieById(id).flatMapMerge {
            movie->
            localRepository.getFavouritesFlow().map {
                movie.toMovieDomain(it.map{it.id}.contains(movie.id))
            }
        }
}





