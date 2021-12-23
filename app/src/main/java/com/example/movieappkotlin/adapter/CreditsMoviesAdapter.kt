package com.example.movieappkotlin.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieappkotlin.databinding.ItemMovieRecyclerBinding
import com.example.movieappkotlin.model.newMovie.Movie
import com.example.movieappkotlin.BR
import com.example.movieappkotlin.databinding.ItemCreditBinding
import com.example.movieappkotlin.databinding.ItemCreditsBinding
import com.example.movieappkotlin.model.credite.Cast
import com.example.movieappkotlin.model.credite.Credite
import com.example.movieappkotlin.utils.DiffUtilsCredits
import com.example.movieappkotlin.utils.DiffUtilsMoviesRec


class CreditsMoviesAdapter() : RecyclerView.Adapter<CreditsMoviesAdapter.ViewHolder>() {

    var listMovie= emptyList<Cast>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    return ViewHolder(ItemCreditsBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    fun setData(newPersonList: List<Cast>){
        val diff= DiffUtilsCredits(listMovie,newPersonList)
        val result= DiffUtil.calculateDiff(diff)
        listMovie=newPersonList
        result.dispatchUpdatesTo(this)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val movie=listMovie.get(position)
        holder.bind(movie)
    }

    override fun getItemCount()=listMovie.size


    open inner class ViewHolder(var itemRow: ItemCreditsBinding) : RecyclerView.ViewHolder(itemRow.root) {

        fun bind(movie: Cast){
            itemRow.setVariable(BR.cast,movie)
            Log.d("teto",movie.profile_path)
            Log.d("teto",movie.character)
        }

    }
}