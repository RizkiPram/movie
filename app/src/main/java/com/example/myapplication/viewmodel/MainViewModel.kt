package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.remote.api.ApiConfig
import com.example.myapplication.data.remote.response.MovieResponse
import com.example.myapplication.data.remote.response.ResultsItem
import com.example.myapplication.repository.MovieRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val movieRepository: MovieRepository):ViewModel() {
    fun getMovies() = movieRepository.getMovieList()
//    private val _listMovie = MutableLiveData<List<ResultsItem>>()
//    val listMovie:MutableLiveData<List<ResultsItem>> = _listMovie
//
//    fun getListMovie(){
//        val client = ApiConfig.getApiService().getListMovies()
//        client.enqueue(object : Callback<MovieResponse>{
//            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
//                val responseBody=response.body()
//                if (response.isSuccessful){
//                    if (responseBody != null){
//                        _listMovie.value=responseBody.results
//                    }
//                }
//            }
//
//            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                t.message?.let { Log.e("MainViewModel", it) }
//            }
//        })
//    }
}