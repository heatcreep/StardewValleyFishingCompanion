package com.example.core.fish

import com.google.gson.annotations.SerializedName

data class Price(val normal: Int, val silver: Int, val gold:Int, val iridium: Int)

data class FishData(
    val name: String,
    val description: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val price: Price,
    val locations: List<String>,
    @SerializedName("available_times")
    val availableTimes: List<String>,
    @SerializedName("available_seasons")
    val availableSeasons: List<String>,
    @SerializedName("available_weather")
    val availableWeather: List<String>,
    val size: String,
    val bundles: List<String>
)

interface FishRepository {
    fun getAllFishData(): List<FishData>
}