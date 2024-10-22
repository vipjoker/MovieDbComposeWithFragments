package com.example.moviedbtestassignment.module

import android.content.Context
import androidx.room.Room
import com.example.moviedbtestassignment.Constants
import com.example.moviedbtestassignment.db.AppDatabase
import com.example.moviedbtestassignment.db.dao.FavouriteDao
import com.example.moviedbtestassignment.db.dao.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbModule {
    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
        return Room.databaseBuilder(applicationContext, AppDatabase::class.java, Constants.MOVIED_DB)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun providesMoviesDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }

    @Provides
    @Singleton
    fun providesFavouritesDao(appDatabase: AppDatabase): FavouriteDao {
        return appDatabase.favouritesDao()
    }
}