package com.test.movieapplication.network.model

data class MainModel(
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)