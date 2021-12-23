package com.example.movieappkotlin.network

import com.example.movieappkotlin.model.credite.Credite
import com.example.movieappkotlin.model.detailsMovie.Details
import com.example.movieappkotlin.model.newMovie.MoveiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("movie/{type}")
    suspend fun getMovieByType(
        @Path("type")type:String,
        @Query("page")page:Int
    ): Response<MoveiResponse>

    @GET("movie/{movie_id}")
    suspend fun getDetails(@Path("movie_id")movie_id:Int):Response<Details>
  @GET("movie/{movie_id}/credits")
    suspend fun getCredit(@Path("movie_id")movie_id:Int):Response<Credite>


}