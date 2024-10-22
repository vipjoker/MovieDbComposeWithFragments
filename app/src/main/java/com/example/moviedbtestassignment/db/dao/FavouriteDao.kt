package com.example.moviedbtestassignment.db.dao

import androidx.room.*
import com.example.moviedbtestassignment.db.entity.FavouriteMovie
import kotlinx.coroutines.flow.Flow


@Dao
interface FavouriteDao {


    @Query("SELECT * FROM favorite_movie WHERE id = :id LIMIT 1")
    fun getMovieById(id: Int): FavouriteMovie

    @Query("SELECT * FROM favorite_movie")
    suspend fun getAll(): List<FavouriteMovie>

    @Query("SELECT * FROM favorite_movie")
    fun getAllFlow(): Flow<List<FavouriteMovie>>

    @Delete
    suspend fun deleteMovie(movie: FavouriteMovie)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(favouriteMovie: FavouriteMovie)


    @Query("DELETE FROM favorite_movie")
    suspend fun deleteAll(): Int


}