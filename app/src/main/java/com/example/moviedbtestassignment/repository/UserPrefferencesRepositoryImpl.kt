package com.example.moviedbtestassignment.repository

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.moviedbtestassignment.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import okio.IOException
import javax.inject.Inject


private val Context.dataStore by preferencesDataStore(name = Constants.USER_PREFERENCES_NAME)

data class UserPreferences(val isDarkModeActive: Boolean)
class UserPrefferencesRepositoryImpl (private val context: Context) : UserPrefferencesRepository {
    private val datastore = context.dataStore

    private object PreferenecesKeys {
        val DARK_MODE = booleanPreferencesKey("dark_mode")

    }

    override fun userPreferncesFlow(): Flow<UserPreferences> = datastore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { pref ->
            val isDarkModeActive = pref[PreferenecesKeys.DARK_MODE] ?: false
            UserPreferences(isDarkModeActive)
        }


    override suspend fun saveDarkMode(isDarkMode: Boolean) {
        datastore.edit { prefs ->
            prefs[PreferenecesKeys.DARK_MODE] = isDarkMode

        }
    }


}