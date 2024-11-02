package com.example.moviedbtestassignment.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.example.moviedbtestassignment.api.response.toMovieLocal
import com.example.moviedbtestassignment.db.entity.MovieLocal
import com.example.moviedbtestassignment.repository.MovieRepositoryLocal
import com.example.moviedbtestassignment.repository.MovieRepositoryRemote
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class MoviePagingMediator(
    private val remoteService: MovieRepositoryRemote,
    private val localRepository: MovieRepositoryLocal
): RemoteMediator<Int, MovieLocal>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MovieLocal>
    ): MediatorResult {
        return try {
            val loadKey = when(loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if(lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }

            val response = remoteService.getPopularMovies(loadKey)
            val movies = response.results





                if(loadType == LoadType.REFRESH) {
                    localRepository.clearMovies()
                }
                val beerEntities = movies.mapIndexed {index, item-> item.toMovieLocal(response.page,response.total_pages,System.currentTimeMillis(),index + (response.page * movies.size ) ) }
                localRepository.saveLocalMovies(beerEntities)

            MediatorResult.Success(
                endOfPaginationReached = movies.isEmpty()
            )
        } catch(e: IOException) {
            MediatorResult.Error(e)
        } catch(e: HttpException) {
            MediatorResult.Error(e)
        }
    }
}