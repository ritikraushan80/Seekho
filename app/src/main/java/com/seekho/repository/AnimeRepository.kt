package com.seekho.repository

import com.seekho.model.AnimeSeriesList
import com.seekho.model.ApiResult
import com.seekho.network.NetworkModule


/**
 * Created by Ritik on: 10/05/25
 */

class AnimeRepository : AnimeDataRepository {

    override suspend fun getAnimeSeriesData(): ApiResult<ArrayList<AnimeSeriesList>> {
        return NetworkModule.fitbitApi.getAnimeSeries()
    }

    override suspend fun getAnimeDetails(animeId: Int): ApiResult<AnimeSeriesList> {
        return NetworkModule.fitbitApi.getAnimeDetails(animeId)
    }

}