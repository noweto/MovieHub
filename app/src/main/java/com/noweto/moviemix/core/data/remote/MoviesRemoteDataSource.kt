package com.noweto.moviemix.core.data.remote

import com.noweto.moviemix.core.utils.BusinessConst.API_KEY
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor ( private val moviesApiServices: MoviesApiServices):
    BaseRemoteDataSource() {

    //~~ Convert retrofit response into my Resource -> getResults()

    suspend fun getMovies() = getResults { moviesApiServices.getPopularMovies(API_KEY,1) }

    suspend fun getSpecificMovie(movieName:String) = getResults { moviesApiServices.getSpecificMovie(API_KEY,movieName) }


}