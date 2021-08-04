package com.noweto.moviemix.core.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.Query
import com.noweto.moviemix.ui.home.models.MovieResult

/**
 * Dao Interface ..
 */
@Dao
interface MoviesDao {

    //~~ get popular movies
    @Query("SELECT * FROM popular_Movie_List")
    fun getPopularMovies():LiveData<List<MovieResult>>

    //~~ insert into popular movies
    @Insert(onConflict = ABORT)
    fun insetPopularMovies(moviesList : ArrayList<MovieResult>)



}