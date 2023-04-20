package com.jordju.moviebdd.data.remote.service

import com.jordju.moviebdd.data.remote.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie/top_rated")
    suspend fun getTopRatedMovieList(
        @Query("api_key") apiKey: String = "e43e6690e57c8cca398e6f2ea53fcbf4",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieListResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovieList(
        @Query("api_key") apiKey: String = "e43e6690e57c8cca398e6f2ea53fcbf4",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieListResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovieList(
        @Query("api_key") apiKey: String = "e43e6690e57c8cca398e6f2ea53fcbf4",
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1
    ): MovieListResponse

}