package com.example.movieappkotlin.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.movieappkotlin.model.newMovie.Movie

class DiffUtilsMoviesRec(private val olderList: List<Movie>,
                         private val newList: List<Movie>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
    return    olderList.size
    }

    override fun getNewListSize(): Int {
   return  newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return  olderList[oldItemPosition].id==newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            olderList[oldItemPosition].id!=newList[newItemPosition].id->false
            olderList[oldItemPosition].title!=newList[newItemPosition].title->false
            olderList[oldItemPosition].release_date!=newList[newItemPosition].release_date->false
            else -> true
        }
        }
}