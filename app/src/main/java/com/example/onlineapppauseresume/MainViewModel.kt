package com.example.onlineapppauseresume

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jetpackcomposemvvmretrofitandrecyclerview.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainViewModel : ViewModel() {
    var weather: WeatherData by mutableStateOf(WeatherData())
    var apiKey: String by mutableStateOf("a6ea090d1f474d6abdee5db6552ff501")

    fun getMovieList(cityName: String) {
        viewModelScope.launch {
            val apiService = ApiService.getInstance() // Server
                runBlocking(Dispatchers.IO) {
                    weather = apiService.getWeather(cityName, apiKey)
                }
        }
    }
}