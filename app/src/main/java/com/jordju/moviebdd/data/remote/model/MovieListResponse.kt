package com.jordju.moviebdd.data.remote.model


import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("page")
    var page: Int?,
    @SerializedName("results")
    var results: List<MovieResponse?>?,
    @SerializedName("total_pages")
    var totalPages: Int?,
    @SerializedName("total_results")
    var totalResults: Int?
)