package com.noweto.moviemix.core.di

import android.content.Context
import com.noweto.moviemix.core.data.local.AppDatabaseBuilder
import com.noweto.moviemix.core.data.local.MoviesDao
import com.noweto.moviemix.core.data.local.MoviesLocalDataSource
import com.noweto.moviemix.core.data.remote.AppNetworkBuilder
import com.noweto.moviemix.core.data.remote.MoviesApiServices
import com.noweto.moviemix.core.data.remote.MoviesRemoteDataSource
import com.noweto.moviemix.core.data.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * app module object apply hilt as a dependency injection ...
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitClient():Retrofit =
        AppNetworkBuilder().provideRetrofitClient()

    @Singleton
    @Provides
    fun provideRoomDataBase (@ApplicationContext context: Context):AppDatabaseBuilder =
        AppDatabaseBuilder.provideRoomDataBase(context)
    @Provides
    fun provideMoviesApiServices(retrofit: Retrofit) :MoviesApiServices=
        retrofit.create(MoviesApiServices::class.java)

    @Provides
    fun provideMoviesDao(appDatabaseBuilder: AppDatabaseBuilder) =
        appDatabaseBuilder.getMovies()

    @Provides
    fun provideMoviesRemoteDs(moviesApiServices: MoviesApiServices)=
        MoviesRemoteDataSource(moviesApiServices)

    @Provides
    fun provideMoviesLocalDs(moviesDao: MoviesDao) =
        MoviesLocalDataSource(moviesDao)
    @Provides
    fun provideMoviesRepository(moviesLocalDataSource: MoviesLocalDataSource,
    moviesRemoteDataSource: MoviesRemoteDataSource) =
        MoviesRepository(moviesRemoteDataSource,moviesLocalDataSource)
}