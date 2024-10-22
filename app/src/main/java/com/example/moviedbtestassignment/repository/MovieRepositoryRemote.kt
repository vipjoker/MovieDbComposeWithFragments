package com.example.moviedbtestassignment.repository

import com.example.moviedbtestassignment.api.response.MovieListResponse

interface MovieRepositoryRemote {

    suspend fun getPopularMovies(page: Int): MovieListResponse

}