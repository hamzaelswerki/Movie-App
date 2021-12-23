package com.example.movieappkotlin.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.movieappkotlin.model.credite.Cast
import com.example.movieappkotlin.model.credite.Credite
import com.example.movieappkotlin.model.newMovie.Movie

class DiffUtilsCredits(private val olderList: List<Cast>,
                       private val newList: List<Cast>): DiffUtil.Callback() {

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
            olderList[oldItemPosition].cast_id!=newList[newItemPosition].cast_id->false
            olderList[oldItemPosition].name!=newList[newItemPosition].name->false
            else -> true
        }
        }
}