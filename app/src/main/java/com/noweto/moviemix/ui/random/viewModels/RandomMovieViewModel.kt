package com.noweto.moviemix.ui.random.viewModels

import androidx.lifecycle.ViewModel
import com.noweto.moviemix.core.data.repository.MoviesRepository
import com.noweto.moviemix.ui.home.models.MovieResult
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class RandomMovieViewModel @Inject constructor (private val moviesRepository: MoviesRepository) : ViewModel() {

    val movieList = moviesRepository.getPopularMovies()

    fun getRandomMovie(movieList : ArrayList<MovieResult>) : ArrayList<MovieResult>{
        val randomValue = Random.nextInt(movieList.size)

        val randomObject : MovieResult = movieList[randomValue]

        val random : ArrayList<MovieResult> = arrayListOf()
        random.add(randomObject)

        return random
    }

}