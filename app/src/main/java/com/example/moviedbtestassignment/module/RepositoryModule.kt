package com.example.moviedbtestassignment.module

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.moviedbtestassignment.repository.MovieRepositoryLocal
import com.example.moviedbtestassignment.api.MovieDbApiService
import com.example.moviedbtestassignment.db.dao.FavouriteDao
import com.example.moviedbtestassignment.db.dao.MovieDao
import com.example.moviedbtestassignment.db.entity.MovieLocal
import com.example.moviedbtestassignment.paging.MoviePagingMediator
import com.example.moviedbtestassignment.repository.*
import com.example.moviedbtestassignment.ui.model.MovieDomain
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRemoteRepository(apiService: MovieDbApiService): MovieRepositoryRemote {
        return MovieRepositoryRemoteImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideLocalMovieRepository(movieDao: MovieDao, favouriteDao: FavouriteDao): MovieRepositoryLocal {
        return MovieRepositoryLocalImpl(movieDao, favouriteDao)

    }

    @Provides
    @Singleton
    fun provideRepository(remoteRepository: MovieRepositoryRemote, localRepository: MovieRepositoryLocal,pager: Pager<Int, MovieLocal>): MovieRepository {
        return MovieRepository(remoteRepository,localRepository, pager)
    }

    @Provides
    @Singleton
    fun provideUserPreferncesRepository(@ApplicationContext context: Context): UserPrefferencesRepository{
        return UserPrefferencesRepositoryImpl(context)
    }

    @Provides
    @Singleton
    fun provideDeviceInfoRepository(@ApplicationContext context: Context):DeviceInfoRepository{
        return DeviceInfoRepositoryImpl(context)
    }


    @OptIn(ExperimentalPagingApi::class)
    @Provides
    @Singleton
    fun providerPager(remoteRepository: MovieRepositoryRemote, localRepository: MovieRepositoryLocal): Pager<Int, MovieLocal> {
            return Pager(
                config = PagingConfig(pageSize = 20),
                remoteMediator = MoviePagingMediator(
                    remoteRepository,
                    localRepository
                ),
                pagingSourceFactory = {
                    localRepository.getMoviePagingSource()
                }
            )
        }
    }
