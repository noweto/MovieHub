package com.noweto.moviemix.core.data.remote

import com.noweto.moviemix.ui.home.models.MoviesModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Services interface for end points ..
 */

interface MoviesApiServices {

    //~~ get popular movie ..
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey :String ,@Query("page") pageNum :Int )
    :Response<MoviesModel>

    //~~ search on specific movie ..
    @GET("search/movie")
    suspend fun getSpecificMovie(@Query("api_key") apiKey: String,@Query("query") movieName:String)
    :Response<MoviesModel>






}