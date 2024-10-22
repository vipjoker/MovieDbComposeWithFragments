package com.example.moviedbtestassignment.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.moviedbtestassignment.db.converters.DbConverter
import com.example.moviedbtestassignment.db.dao.FavouriteDao
import com.example.moviedbtestassignment.db.dao.MovieDao
import com.example.moviedbtestassignment.db.entity.FavouriteMovie
import com.example.moviedbtestassignment.db.entity.MovieLocal


    @Database(
        entities = [MovieLocal::class,FavouriteMovie::class],
        version = 1
    )

    @TypeConverters(DbConverter::class)

    abstract class AppDatabase : RoomDatabase() {
        abstract fun movieDao(): MovieDao
        abstract fun favouritesDao(): FavouriteDao
    }
