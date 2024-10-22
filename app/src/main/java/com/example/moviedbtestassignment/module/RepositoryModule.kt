package com.example.moviedbtestassignment.module

import android.content.Context
import com.example.moviedbtestassignment.repository.MovieRepositoryLocal
import com.example.moviedbtestassignment.api.MovieDbApiService
import com.example.moviedbtestassignment.db.dao.FavouriteDao
import com.example.moviedbtestassignment.db.dao.MovieDao
import com.example.moviedbtestassignment.repository.*
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
    fun provideRepository(remoteRepository: MovieRepositoryRemote, localRepository: MovieRepositoryLocal): MovieRepository {
        return MovieRepository(remoteRepository,localRepository)
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


}