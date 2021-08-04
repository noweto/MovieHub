package com.noweto.moviemix.ui.home.viewModels

import androidx.lifecycle.ViewModel
import com.noweto.moviemix.core.data.repository.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PopMoviesViewModel @Inject constructor(private val moviesRepository: MoviesRepository):ViewModel(){

    val popularMoviesList = moviesRepository.getPopularMovies()


}