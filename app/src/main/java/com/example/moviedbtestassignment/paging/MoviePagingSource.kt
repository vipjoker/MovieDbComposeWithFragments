package com.example.moviedbtestassignment.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.moviedbtestassignment.api.response.toMovieDomain
import com.example.moviedbtestassignment.api.response.toMovieLocal
import com.example.moviedbtestassignment.db.entity.toMovieDomain
import com.example.moviedbtestassignment.repository.MovieRepositoryLocal
import com.example.moviedbtestassignment.repository.MovieRepositoryRemote
import com.example.moviedbtestassignment.ui.model.MovieDomain

class MoviePagingSource(
    private val remoteService: MovieRepositoryRemote,
    private val localRepository: MovieRepositoryLocal,
) : PagingSource<Int, MovieDomain>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDomain> {
        return try {
            val currentPage = params.key ?: 1

            val favouritesId = localRepository.getFavourites().map { it.id }

            val localMovies = localRepository.getLocalMovies(currentPage)
            val pair = if (localMovies.isEmpty()) {
                val response = remoteService.getPopularMovies(page = currentPage)
                val localsMovies = response.results.mapIndexed {pos,item-> item.toMovieLocal(currentPage,response.total_pages,System.currentTimeMillis(), pos ) }
                localRepository.saveLocalMovies(localsMovies)
                Pair( response.results.map {
                    it.toMovieDomain(favouritesId.contains(it.id))
                },response.total_pages)


            } else {
                Pair(localMovies.map { it.toMovieDomain(favouritesId.contains(it.id)) },localMovies.first().totalPages)

            }

            LoadResult.Page(
                data = pair.first,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (currentPage < pair.second) currentPage + 1 else null
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDomain>): Int? {
        return null
    }
}
