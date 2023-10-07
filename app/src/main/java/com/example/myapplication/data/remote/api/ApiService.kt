package com.example.myapplication.data.remote.api

import com.example.myapplication.data.remote.response.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("movie?api_key=f7b67d9afdb3c971d4419fa4cb667fbf")
    fun getListMovies():Call<MovieResponse>
}