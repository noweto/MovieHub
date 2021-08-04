package com.noweto.moviemix.core.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.noweto.moviemix.core.utils.BusinessConst.MOVIE_MIX_DB
import com.noweto.moviemix.ui.home.models.MovieResult

/**
 * Single tone of room data base ..
 */



@Database(entities = [MovieResult::class],version = 1,exportSchema = false)

abstract class AppDatabaseBuilder :RoomDatabase()  {

    companion object{

        private var db : AppDatabaseBuilder ?=null


        fun provideRoomDataBase(context: Context):AppDatabaseBuilder =
            db?: synchronized(this){
                db?: buildDataBase(context).also { db = it }
            }


        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context,AppDatabaseBuilder::class.java,MOVIE_MIX_DB)
                .fallbackToDestructiveMigration()
                .build()

    }

    abstract fun getMovies():MoviesDao


}