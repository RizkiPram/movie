package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.remote.api.ApiConfig
import com.example.myapplication.data.remote.response.MovieResponse
import com.example.myapplication.data.remote.response.ResultsItem
import com.example.myapplication.repository.MovieRepository
import com.example.myapplication.services.Service
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val movieRepository: MovieRepository):ViewModel() {
    fun getMovies() = movieRepository.getMovieList()
}