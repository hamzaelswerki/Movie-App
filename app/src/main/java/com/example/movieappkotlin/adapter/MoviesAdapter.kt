package com.example.movieappkotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.example.ecommercekotlin.model.callback.OnCellClickListener
import com.example.movieappkotlin.BR
import com.example.movieappkotlin.R
import com.example.movieappkotlin.databinding.ItemMovieLargeBinding
import com.example.movieappkotlin.databinding.ItemMovieSmBinding
import com.example.movieappkotlin.model.newMovie.Movie

class MoviesAdapter(var movies:List<Movie>,var itemSize:ITEM_SIZE,val onCellClickListener: OnCellClickListener) :PagerAdapter() {
    override fun getCount(): Int {
     return movies.size
    }
    override fun isViewFromObject(view: View, `object`: Any): Boolean {
    return view==`object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
    var binding= if (itemSize==ITEM_SIZE.SMALL)
        DataBindingUtil.inflate<ItemMovieSmBinding>(LayoutInflater.from(container.context),
            R.layout.item_movie_sm,container,false)
    else
        ItemMovieLargeBinding.inflate(LayoutInflater.from(container.context),container,false)
       /* DataBindingUtil.inflate<ItemMovieLargeBinding>(LayoutInflater.from(container.context),
            R.layout.item_movie_large,container,false)*/

        binding.setVariable(BR.movie,movies.get(position))
        container.addView(binding.root)
        binding.root.setOnClickListener {
            onCellClickListener.onItemClicked(movies.get(position))

        }
             return binding.root
    }
    override fun getPageWidth(position: Int): Float {
        return if (itemSize == ITEM_SIZE.SMALL) 0.30f else 1.0f
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
         container.removeView(`object`as View)
    }

    enum class ITEM_SIZE {
        SMALL,LARGE
    }
    }