package com.example.myapplication.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.adapter.MovieAdapter
import com.example.myapplication.data.local.entity.MovieEntity
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.services.Service
import com.example.myapplication.utils.Result
import com.example.myapplication.viewmodel.MainViewModel
import com.example.myapplication.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val foregroundServiceIntent = Intent(this, Service::class.java)
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(foregroundServiceIntent)
        } else {
            startService(foregroundServiceIntent)
        }

        setupViewModel()
    }

    private fun setupViewModel() {
        val factory: ViewModelFactory = ViewModelFactory.getInstance(this)
        val viewModel: MainViewModel by viewModels {
            factory
        }
        viewModel.getMovies().observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        //binding progress bar
                    }

                    is Result.Success -> {
                        val movieData = result.data
                        setupMovieList(movieData)
                    }

                    is Result.Error -> {
                        Toast.makeText(
                            this,
                            "Something Wrong Check Your Connection",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
    }

    private fun setupMovieList(data: List<MovieEntity>) {
        val listMovie = ArrayList<MovieEntity>()
        data.forEach { listMovie.add(it) }
        binding.rvMovies.apply {
            val movieAdapter = MovieAdapter(listMovie)
            val layout = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutManager = layout
            adapter = movieAdapter
        }
    }
}