package com.devtamuno.themoviedatabase.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devtamuno.themoviedatabase.remote.repository.MoviesRemoteRepository
import com.devtamuno.themoviedatabase.remote.repository.Resource
import com.devtamuno.themoviedatabase.ui.data.mapper.MovieDetailMapper
import com.devtamuno.themoviedatabase.ui.navigation.ARG_MOVIE_ID
import com.devtamuno.themoviedatabase.ui.screen.state.MovieDetailState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val repository: MoviesRemoteRepository,
    private val movieDetailMapper: MovieDetailMapper,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieDetailState())
    val uiState: StateFlow<MovieDetailState> = _uiState.asStateFlow()

    init {
        val intentMovieId = savedStateHandle.get<Int>(ARG_MOVIE_ID)!!
        getSelectedMovieById(intentMovieId)
    }


    private fun getSelectedMovieById(movieId: Int) = viewModelScope.launch {
        when (val result = repository.getSelectedMovieDetail(movieId)) {
            is Resource.Success -> {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        movie = movieDetailMapper.mapToUi(result.result),
                        error = null
                    )
                }
            }
            is Resource.Failure -> {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = result.error
                    )
                }
            }
        }

    }
}