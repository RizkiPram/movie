package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.myapplication.data.local.entity.MovieEntity
import com.example.myapplication.data.local.room.MovieDao
import com.example.myapplication.data.remote.api.ApiConfig
import com.example.myapplication.data.remote.api.ApiService
import com.example.myapplication.data.remote.response.MovieResponse
import com.example.myapplication.utils.AppExecutors
import com.example.myapplication.utils.Result
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository private constructor(
    private val apiService: ApiService,
    private val movieDao: MovieDao,
    private val appExecutors: AppExecutors
) {
    private val result = MediatorLiveData<Result<List<MovieEntity>>>()
    fun getMovieList():LiveData<Result<List<MovieEntity>>>{
        result.value=Result.Loading
        val client = apiService.getListMovies()
        client.enqueue(object : Callback<MovieResponse>{
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
                    val movie =response.body()?.results
                    val movieList=ArrayList<MovieEntity>()
                    appExecutors.diskIO.execute {
                        movie?.forEach { movie ->
                            val movieItem = MovieEntity(
                                movie.id,
                                movie.originalTitle,
                                movie.releaseDate
                            )
                            movieList.add(movieItem)
                        }
                        movieDao.insertMovie(movieList)
                    }
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                result.value = Result.Error(t.message.toString())
            }
        })
        val localData = movieDao.getMovie()
        result.addSource(localData) { newData: List<MovieEntity> ->
            result.value = Result.Success(newData)
        }
        return result
    }
    companion object {
        @Volatile
        private var instance: MovieRepository? = null
        fun getInstance(
            apiService: ApiService,
            movieDao: MovieDao,
            appExecutors: AppExecutors
        ): MovieRepository =
            instance ?: synchronized(this) {
                instance ?: MovieRepository(apiService, movieDao, appExecutors)
            }.also { instance = it }
    }
}