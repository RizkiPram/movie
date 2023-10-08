package com.example.myapplication.di

import android.content.Context
import com.example.myapplication.data.local.room.MovieDatabase
import com.example.myapplication.data.remote.api.ApiConfig
import com.example.myapplication.repository.MovieRepository
import com.example.myapplication.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context):MovieRepository{
        val apiService = ApiConfig.getApiService()
        val database = MovieDatabase.getInstance(context)
        val dao = database.movieDao()
        val appExecutors =AppExecutors
        return MovieRepository.getInstance(apiService,dao,appExecutors)
    }
}