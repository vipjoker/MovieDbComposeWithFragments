package com.example.moviedbtestassignment.repository

import android.util.Log
import com.example.moviedbtestassignment.api.MovieDbApiService
import com.example.moviedbtestassignment.api.response.MovieListResponse

class MovieRepositoryRemoteImpl(val apiService: MovieDbApiService):MovieRepositoryRemote {
    override suspend fun getPopularMovies(page: Int): MovieListResponse {
        Log.i("DEBUGAND", "searchMovies: $page")
        return apiService.getPopularMovies(page)
    }

}