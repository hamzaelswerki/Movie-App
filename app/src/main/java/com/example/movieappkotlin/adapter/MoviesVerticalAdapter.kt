package com.example.movieappkotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappkotlin.databinding.ItemMovieRecyclerBinding
import com.example.movieappkotlin.model.newMovie.Movie
import com.example.movieappkotlin.BR
import com.example.movieappkotlin.utils.DiffUtilsMoviesRec


class MoviesVerticalAdapter() : RecyclerView.Adapter<MoviesVerticalAdapter.ViewHolder>() {

    var listMovie= emptyList<Movie>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(ItemMovieRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    fun setData(newPersonList: List<Movie>){
        val diff= DiffUtilsMoviesRec(listMovie,newPersonList)
        val result= DiffUtil.calculateDiff(diff)
        listMovie=newPersonList
        result.dispatchUpdatesTo(this)


    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie=listMovie.get(position)
        holder.bind(movie)
    }

    override fun getItemCount()=listMovie.size


    open inner class ViewHolder(var itemRow: ItemMovieRecyclerBinding) : RecyclerView.ViewHolder(itemRow.root) {

        fun bind(movie: Movie){
            itemRow.setVariable(BR.movie,movie)
        }

    }
}