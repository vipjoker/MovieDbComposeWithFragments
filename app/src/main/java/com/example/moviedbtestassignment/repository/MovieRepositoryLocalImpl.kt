package com.example.moviedbtestassignment.repository

import com.example.moviedbtestassignment.db.dao.FavouriteDao
import com.example.moviedbtestassignment.db.dao.MovieDao
import com.example.moviedbtestassignment.db.entity.FavouriteMovie
import com.example.moviedbtestassignment.db.entity.MovieLocal
import kotlinx.coroutines.flow.Flow

class MovieRepositoryLocalImpl(val movieDao: MovieDao, val favouriteDao: FavouriteDao) : MovieRepositoryLocal {
    override suspend fun getFavourites(): List<FavouriteMovie> {
        return favouriteDao.getAll()
    }


    override fun getFavouritesFlow(): Flow<List<FavouriteMovie>> {
        return favouriteDao.getAllFlow()

    }

    override suspend fun saveFavourite(favouriteMovie: FavouriteMovie) {
        favouriteDao.saveMovie(favouriteMovie)
    }

    override suspend fun removeFavourite(favouriteMovie: FavouriteMovie) {
        favouriteDao.deleteMovie(favouriteMovie)
    }

    override suspend fun getLocalMovies(page: Int): List<MovieLocal> {
        return movieDao.getAllByPage(page)
    }

    override suspend fun saveLocalMovie(movieLocal: MovieLocal) {
        movieDao.saveMovie(movieLocal)
    }

    override suspend fun saveLocalMovies(movieLocals: List<MovieLocal>) {
        movieDao.saveMovies(movieLocals)
    }

    override fun getLocalMovieById(id: Int): Flow<MovieLocal> {
        return movieDao.getMovieByIdFlow(id)
    }
}