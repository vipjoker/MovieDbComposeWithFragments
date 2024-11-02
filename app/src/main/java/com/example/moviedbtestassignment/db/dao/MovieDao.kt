package com.example.moviedbtestassignment.db.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.moviedbtestassignment.db.entity.MovieLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {


    @Query("SELECT * FROM movie WHERE id = :id LIMIT 1")
    suspend fun getMovieById(id:Int): MovieLocal

    @Query("SELECT * FROM movie WHERE id = :id LIMIT 1")
    fun getMovieByIdFlow(id:Int): Flow<MovieLocal>

    @Query("SELECT * FROM movie WHERE page = :page ORDER BY position")
    suspend fun getAllByPage(page:Int): List<MovieLocal>

    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<MovieLocal>

    @Delete
    suspend fun deleteMovie(movie: MovieLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovie(movieLocal: MovieLocal)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movieLocal:List<MovieLocal>)

    @Query("DELETE FROM movie")
    suspend fun deleteAll(): Int

    @Upsert
    suspend fun upsertAll(beers: List<MovieLocal>)

    @Query("SELECT * FROM movie")
    fun pagingSource(): PagingSource<Int, MovieLocal>

    }