package com.example.movieappkotlin.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommercekotlin.model.callback.OnCellClickListener
import com.example.ecommercekotlin.utils.Status
import com.example.movieappkotlin.R
import com.example.movieappkotlin.adapter.MoviesAdapter
import com.example.movieappkotlin.adapter.MoviesVerticalAdapter
import com.example.movieappkotlin.databinding.FragmentHomeBinding
import com.example.movieappkotlin.model.newMovie.Movie
import com.example.movieappkotlin.viewmodel.HomeViewModel
import com.nurbk.ps.movieappq.view.WrapContentViewPager


class HomeFragment : Fragment(),OnCellClickListener {

    lateinit var homeViewModel: HomeViewModel
    lateinit var binding: FragmentHomeBinding
    private val moviesAdapterVertical by lazy {
        MoviesVerticalAdapter()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding= FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    homeViewModel= HomeViewModel()
        observeFunctions()
        getData()

    }

    private fun observeFunctions() {
        homeViewModel.observeNowMovie()
        homeViewModel.observeTopRatedMovie()
        homeViewModel.observeUpComingMovie()
        homeViewModel.observePopularMovie()
    }

    private fun getData() {
        lifecycleScope.launchWhenCreated {
        homeViewModel.getNowMovie().observe(viewLifecycleOwner,{

            when(it.status){
                Status.LOADING->{ Log.d("ttt","loading") }
                Status.SUCCESS->{
                    val lsitMovies =it.data?.results
                  createViewPager(
                      lsitMovies,
                      binding.viewPagerNowPlayingMovies,
                      MoviesAdapter.ITEM_SIZE.LARGE,
                      binding.shimmerNowPlay) }

                Status.ERROR->{ Log.d("ttt","ERROR ${it.message}") }

            }
        })
        }

        lifecycleScope.launchWhenCreated {
            homeViewModel.getTopRated().observe(viewLifecycleOwner,{
                when(it.status){
                    Status.LOADING->{ }
                    Status.SUCCESS->{
                        val lsitMovies =it.data?.results
                        createViewPager(lsitMovies,binding.layoutTopMovies.viewPager,
                            MoviesAdapter.ITEM_SIZE.SMALL,binding.layoutTopMovies.shimmerViewPager)
                        binding.layoutTopMovies.title = "Top Rated"

                    }
                    Status.ERROR->{

                    }
                }
            })

            homeViewModel.getUpComingMovie().observe(viewLifecycleOwner,{
                when(it.status){
                    Status.LOADING->{

                    }
                    Status.SUCCESS->{
                        val lsitMovies =it.data?.results
                        createViewPager(lsitMovies,binding.layoutUpMovies.viewPager,
                            MoviesAdapter.ITEM_SIZE.SMALL,binding.layoutUpMovies.shimmerViewPager)
                        binding.layoutTopMovies.title = "Upcoming"
                    }
                    Status.ERROR->{

                    }
                }
            })
        }
        lifecycleScope.launchWhenCreated {
            homeViewModel.getPopularMovie().observe(viewLifecycleOwner,{
                when(it.status){

                    Status.LOADING->{

                    }
                    Status.SUCCESS->{
                        val lsitMovies =it.data?.results
                        createRecyclerView(lsitMovies)

                    }
                    Status.ERROR->{

                    }
                }
            })

        }
    }

    private fun createRecyclerView(lsitMovies: List<Movie>?) {
        lsitMovies?.let {
            it1 -> moviesAdapterVertical.setData(it1) }

        with(binding.layoutPopularMovies){
            shimmerVerticall.isVisible=false
            title="Popular"
            rvMoviesVerticall.apply {
                adapter=moviesAdapterVertical
                layoutManager= LinearLayoutManager(requireContext())
            }
        }



    }

    private fun createViewPager(
        movies: List<Movie>?,
        viewPager: WrapContentViewPager,
        itemSize: MoviesAdapter.ITEM_SIZE,
        viewTOGone: View) {
     startaAnimations()
        MoviesAdapter(movies!!,itemSize,this).also { adapterMovie ->
            viewPager.apply {
            adapter=adapterMovie
            pageMargin=60
                viewTOGone.visibility=View.GONE
                if (itemSize == MoviesAdapter.ITEM_SIZE.LARGE) {
             //       setPageTransformer(false, BasicViewPagerTransformation())
                    currentItem = movies.size / 2
                }
            }

        }
    }


    override fun onStart() {
        super.onStart()
        startaAnimations()
    }
    private fun startaAnimations() {
        binding.shimmerNowPlay.startShimmerAnimation()
        binding.layoutTopMovies.shimmerViewPager.startShimmerAnimation()
        binding.layoutUpMovies.shimmerViewPager.startShimmerAnimation()
    }
    private fun stopAnimations() {
        binding.layoutTopMovies.shimmerViewPager.stopShimmerAnimation()
        binding.shimmerNowPlay.stopShimmerAnimation()
        binding.layoutUpMovies.shimmerViewPager.stopShimmerAnimation()
    }




    override fun onStop() {
        super.onStop()
    stopAnimations()
    }


    override fun onItemClicked(cell: Any) {
        val movie=cell as Movie
        Toast.makeText(context,movie.id.toString(),Toast.LENGTH_SHORT).show()
    val action=HomeFragmentDirections.actionHomeFragmentToDetailsFragment(idMovie = movie.id)
        findNavController().navigate(action)

    }



}