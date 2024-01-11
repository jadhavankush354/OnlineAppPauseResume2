package com.example.jetpackcomposemvvmretrofitandrecyclerview


import com.example.onlineapppauseresume.WeatherData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather/") // Relative path to the base URL
    suspend fun getWeather(@Query("q") cityName: String, @Query("appid") apiKey: String): WeatherData

    companion object {
        var apiService: ApiService? = null
        fun getInstance() : ApiService {
            if (apiService == null)  {
                apiService = Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/data/2.5/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java) // Server Url
            }
            return apiService!!
        }
    }
}