package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.remote.response.ResultsItem
import com.example.myapplication.databinding.ItemMovieBinding

class MovieAdapter(private val list:ArrayList<ResultsItem>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    inner class ViewHolder(private var binding:ItemMovieBinding) : RecyclerView.ViewHolder(binding.root){
        fun itemBind(data: ResultsItem) {
            binding.apply {
                tvTittle.text = data.originalTitle
                tvDate.text=data.releaseDate
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemBind(list[position])
    }
}