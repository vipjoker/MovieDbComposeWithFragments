package com.example.moviedbtestassignment.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.example.moviedbtestassignment.api.MovieDbApiService
import com.example.moviedbtestassignment.db.entity.MovieLocal
import com.example.moviedbtestassignment.repository.MovieRepository
import com.example.moviedbtestassignment.repository.UserPreferences
import com.example.moviedbtestassignment.repository.UserPrefferencesRepository
import com.example.moviedbtestassignment.ui.model.MovieDomain
import com.example.moviedbtestassignment.ui.model.toMovieFavourite
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesDbViewModel @Inject constructor(
    private val repository: MovieRepository,
    private val userPreferences: UserPrefferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<PagingData<MovieDomain>>(PagingData.empty())
    val uiState: StateFlow<PagingData<MovieDomain>> = _uiState.asStateFlow()

    fun onSearchClicked() {
        viewModelScope.launch() {
            try {
                repository.getMoviesWithFavourites().collectLatest { pagingData ->
                    _uiState.value = pagingData
                }
            } catch (e: Exception) {
                Log.d("XXX:", e.toString())

            }
        }
    }


    fun findOneMovieById(id:Int) = repository.getMovieById(id)


    fun isDarkModeFlow() = userPreferences.userPreferncesFlow().map { it.isDarkModeActive }

    fun updateDarkMode(isDarkMode: Boolean) {
        viewModelScope.launch {
            userPreferences.saveDarkMode(isDarkMode)
        }
    }

    fun toggleFavourite(movie: MovieDomain) {

        viewModelScope.launch {

            repository.makeFavorite(movie.toMovieFavourite(), movie.isFavourite.not())
        }
    }
}