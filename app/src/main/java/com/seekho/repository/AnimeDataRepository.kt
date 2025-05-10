package com.seekho.repository

import com.seekho.model.AnimeSeriesList
import com.seekho.model.ApiResult


/**
 * Created by Ritik on: 10/05/25
 */

interface AnimeDataRepository {

    /**
     * Gets Anime list data.
     */
    suspend fun getAnimeSeriesData(): ApiResult<ArrayList<AnimeSeriesList>>

    /**
     * Gets Anime details by Id.
     */
    suspend fun getAnimeDetails(animeId:Int): ApiResult<AnimeSeriesList>
}