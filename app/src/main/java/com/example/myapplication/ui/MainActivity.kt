package com.example.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.MovieAdapter
import com.example.myapplication.data.remote.response.ResultsItem
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getListMovie()
        viewModel.listMovie.observe(this@MainActivity){
            setupMovieList(it)
        }
    }
    private fun setupMovieList(data:List<ResultsItem>){
        val listMovie=ArrayList<ResultsItem>()
        data.forEach { listMovie.add(it) }
        binding.rvMovies.apply {
            val movieAdapter = MovieAdapter(listMovie)
            val layout=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            layoutManager=layout
            adapter=movieAdapter
        }
    }
}