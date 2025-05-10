package com.seekho.network

import com.seekho.model.AnimeSeriesList
import com.seekho.model.ApiResult
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

/**
 * Created by Ritik on: 10/05/25
 */

interface ApiService {

    @Headers("Content-Type: application/json")
    @GET("top/anime")
    suspend fun getAnimeSeries(): ApiResult<ArrayList<AnimeSeriesList>>

    @Headers("Content-Type: application/json")
    @GET("anime/{anime_id}")
    suspend fun getAnimeDetails(@Path("anime_id") id: Int): ApiResult<AnimeSeriesList>

}