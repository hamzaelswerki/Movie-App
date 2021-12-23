package com.example.movieappkotlin.model.newMovie

data class MoveiResponse(
    val dates: Dates,
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)