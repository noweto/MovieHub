package com.noweto.moviemix.ui.home.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

data class MoviesModel(
    val page: Int,
    val results: ArrayList<MovieResult>,
    val total_pages: Int,
    val total_results: Int
)

const val MOVIE_ID = 0
@Entity(tableName = "popular_Movie_List")
data class MovieResult(
    val adult: Boolean,
    val backdrop_path: String,
  //  val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
):Serializable{
    @PrimaryKey(autoGenerate = true)
    var movieId : Int = MOVIE_ID

}