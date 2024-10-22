package com.example.moviedbtestassignment.api

import com.example.moviedbtestassignment.api.response.MovieListResponse
import okhttp3.ResponseBody
import retrofit2.http.*

interface MovieDbApiService {

        @GET("/3/movie/popular")
        suspend fun getPopularMovies(@Query("page") page:Int): MovieListResponse


}