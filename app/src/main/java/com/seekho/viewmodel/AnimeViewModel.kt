package com.seekho.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seekho.model.AnimeSeriesList
import com.seekho.model.NetworkState
import com.seekho.repository.AnimeDataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Ritik on: 10/05/25
 */

@HiltViewModel
class AnimeViewModel @Inject constructor(private val repository: AnimeDataRepository) :
    ViewModel() {

    private val _animeSeries = MutableLiveData<NetworkState<ArrayList<AnimeSeriesList>>?>()
    val animeSeries: LiveData<NetworkState<ArrayList<AnimeSeriesList>>?> get() = _animeSeries

    private val _animeDetails = MutableLiveData<NetworkState<AnimeSeriesList>?>()
    val animeDetails: LiveData<NetworkState<AnimeSeriesList>?> get() = _animeDetails


    /**
     * Get list of Anime series
     */
    fun getAnimeSeriesList() {
        _animeSeries.value = NetworkState.Loading()

        viewModelScope.launch {
            try {
                val response = repository.getAnimeSeriesData()
                _animeSeries.value = NetworkState.Success(response.data)

            } catch (ex: Exception) {
                _animeSeries.value = NetworkState.Error(
                    message = ex.message, t = ex
                )
            }
        }
    }

    /**
     * Get Anime  details by Id
     */
    fun getAnimeDetails(id: Int) {
        _animeDetails.value = NetworkState.Loading()

        viewModelScope.launch {
            try {
                val response = repository.getAnimeDetails(id)
                _animeDetails.value = NetworkState.Success(response.data)

            } catch (ex: Exception) {
                _animeDetails.value = NetworkState.Error(
                    message = ex.message, t = ex
                )
            }
        }
    }

}