package com.example.movieappkotlin.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommercekotlin.utils.Status
import com.example.movieappkotlin.R
import com.example.movieappkotlin.adapter.CreditsMoviesAdapter
import com.example.movieappkotlin.adapter.MoviesVerticalAdapter
import com.example.movieappkotlin.databinding.FragmentDetailsBinding
import com.example.movieappkotlin.viewmodel.HomeViewModel

class DetailsFragment : Fragment() {


    lateinit var binding:FragmentDetailsBinding
    private val homeViewModel by lazy { HomeViewModel() }
    private val creditsAdapter by lazy {
        CreditsMoviesAdapter()
    }

    val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
           binding= FragmentDetailsBinding.inflate(inflater)
      return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeFunctions()
        getData()
    }

    private fun getData() {
        lifecycleScope.launchWhenCreated {
            homeViewModel.getDetails().observe(viewLifecycleOwner,{
                when(it.status){
                    Status.LOADING->{
                        Log.d("yyy","LOADING")

                    }
                    Status.SUCCESS->{
                        binding.details=it.data
                    }
                    Status.ERROR->{
                        Log.d("yyy","ERROR")

                    }
                }
            })
        }

        lifecycleScope.launchWhenCreated {
            homeViewModel.getCredits().observe(viewLifecycleOwner,{
                when(it.status){
                    Status.LOADING->{
                        Log.d("yyy","LOADING")

                    }
                    Status.SUCCESS->{
                        Log.d("hamza",it.data.toString())
                      creditsAdapter.setData(it.data!!.cast)
                        binding.recyclerViewCredite.apply {
                            adapter=creditsAdapter
                            layoutManager=LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
                        }
                   }
                    Status.ERROR->{
                        Log.d("yyy","ERROR")

                    }
                }
            })
        }
    }

    fun observeFunctions(){
        homeViewModel.observeDetails(args.idMovie)
        homeViewModel.observeCredit(args.idMovie)
    }


}