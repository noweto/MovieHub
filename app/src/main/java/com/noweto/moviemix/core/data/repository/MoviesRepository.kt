package com.noweto.moviemix.core.data.repository

import com.noweto.moviemix.core.data.local.MoviesLocalDataSource
import com.noweto.moviemix.core.data.remote.MoviesRemoteDataSource
import com.noweto.moviemix.core.utils.performGetOperations
import com.noweto.moviemix.core.utils.performSearchOperations
import javax.inject.Inject

class MoviesRepository @Inject constructor (private val moviesRemoteDataSource: MoviesRemoteDataSource,
                        private val moviesLocalDataSource: MoviesLocalDataSource)
{

    //~~ Get popular movie
    fun getPopularMovies() =
        performGetOperations(dataBaseQuery = {moviesLocalDataSource.getMovies()},
                             networkCall = {moviesRemoteDataSource.getMovies()},
                             saveCallResults = {moviesLocalDataSource.insertMovies(it.results)}
                            )




    //~~ Get Specific movie

    fun getMovieByName (movieName : String) = performSearchOperations { moviesRemoteDataSource.getSpecificMovie(movieName) }




}