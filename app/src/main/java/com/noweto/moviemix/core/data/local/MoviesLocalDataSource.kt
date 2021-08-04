package com.noweto.moviemix.core.data.local

import com.noweto.moviemix.ui.home.models.MovieResult
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor (private val moviesDao: MoviesDao){


    fun getMovies() = moviesDao.getPopularMovies()
    fun insertMovies(moviesList:ArrayList<MovieResult>) = moviesDao.insetPopularMovies(moviesList)




}