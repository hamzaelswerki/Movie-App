package com.example.movieappkotlin.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommercekotlin.utils.Resource
import com.example.movieappkotlin.model.credite.Credite
import com.example.movieappkotlin.model.detailsMovie.Details
import com.example.movieappkotlin.model.newMovie.MoveiResponse
import com.example.movieappkotlin.network.ApiClient
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    val mutableNowMovieLiveDatas=MutableLiveData<Resource<MoveiResponse>>()
    val mutableTopRatedMovieLiveData=MutableLiveData<Resource<MoveiResponse>>()
    val mutableUpComingMovieLiveData=MutableLiveData<Resource<MoveiResponse>>()
    val mutablePopularMovieLiveData=MutableLiveData<Resource<MoveiResponse>>()
    val mutableDetailsLiveData= MutableLiveData<Resource<Details>>()
    val mutableCreditsLiveData= MutableLiveData<Resource<Credite>>()

    fun observeNowMovie(){
        mutableNowMovieLiveDatas.postValue(Resource.loading(null))

        viewModelScope.launch {
            try {
                val response= ApiClient.getClient().getMovieByType("now_playing",1)
               if (response.isSuccessful){
                   mutableNowMovieLiveDatas.postValue(Resource.success(response.body()))
               }
            }catch (e:Exception){
                mutableNowMovieLiveDatas.postValue(Resource.error("oops Error ${e.message}",null))
            }
        }
    }

    fun observeTopRatedMovie(){
        mutableTopRatedMovieLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            val response= ApiClient.getClient().getMovieByType("top_rated",1)

            try {
                    if (response.isSuccessful){
                        mutableTopRatedMovieLiveData.postValue(Resource.success(response.body()))
                        }
            }catch (e:Exception){
                mutableTopRatedMovieLiveData.postValue(Resource.error("Oops Error ${response.message()}",null))
            }
        }
    }
  fun observeUpComingMovie(){
      mutableUpComingMovieLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            val response= ApiClient.getClient().getMovieByType("upcoming",1)

            try {
                    if (response.isSuccessful){
                        mutableUpComingMovieLiveData.postValue(Resource.success(response.body()))
                        }
            }catch (e:Exception){
                mutableUpComingMovieLiveData.postValue(Resource.error("Oops Error ${response.message()}",null))
            }
        }
    }
  fun observePopularMovie(){
      mutablePopularMovieLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            val response= ApiClient.getClient().getMovieByType("popular",1)

            try {
                    if (response.isSuccessful){
                        mutablePopularMovieLiveData.postValue(Resource.success(response.body()))
                        }
            }catch (e:Exception){
                mutablePopularMovieLiveData.postValue(Resource.error("Oops Error ${response.message()}",null))
            }
        }
    }

    fun  observeDetails(idMovie:Int){
        mutableDetailsLiveData.postValue(Resource.loading(null))

        viewModelScope.launch {
          val response=  ApiClient.getClient().getDetails(idMovie)
            Log.d("yyy","Resource loading")
            try {
              if (response.isSuccessful){
                  mutableDetailsLiveData.postValue(Resource.success(response.body()))
                  Log.d("yyy","isSuccessful")
              }else{
                  Log.d("yyy","isNOSuccessful")

              }

          }catch (e:Exception){
              mutableDetailsLiveData.postValue(Resource.error("Oops Error in ${response.message()}",null))

          }
        }
    }
    fun  observeCredit(idMovie:Int){
        mutableCreditsLiveData.postValue(Resource.loading(null))

        viewModelScope.launch {
          val response=  ApiClient.getClient().getCredit(idMovie)
            Log.d("yyy","Resource loading")
            try {
              if (response.isSuccessful){
                  mutableCreditsLiveData.postValue(Resource.success(response.body()))
                  Log.d("yyy","isSuccessful")
              }else{
                  Log.d("yyy","isNOSuccessful")

              }

          }catch (e:Exception){
                mutableCreditsLiveData.postValue(Resource.error("Oops Error in ${response.message()}",null))

          }
        }
    }



    fun getNowMovie()=mutableNowMovieLiveDatas
    fun getTopRated()=mutableTopRatedMovieLiveData
    fun getUpComingMovie()=mutableUpComingMovieLiveData
    fun getPopularMovie()=mutablePopularMovieLiveData
    fun  getDetails()=mutableDetailsLiveData
    fun  getCredits()=mutableCreditsLiveData

}