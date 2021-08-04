package com.noweto.moviemix.ui.search.viewModel

import androidx.lifecycle.ViewModel
import com.noweto.moviemix.core.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val moviesRepository: MoviesRepository) :ViewModel() {

    fun getMovieByName(movieName:String) = moviesRepository.getMovieByName(movieName)

}