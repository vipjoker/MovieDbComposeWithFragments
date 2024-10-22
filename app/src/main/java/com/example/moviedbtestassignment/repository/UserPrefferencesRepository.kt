package com.example.moviedbtestassignment.repository

import kotlinx.coroutines.flow.Flow

interface UserPrefferencesRepository {


    fun userPreferncesFlow(): Flow<UserPreferences>

    suspend fun saveDarkMode(isDarkMode:Boolean)
}