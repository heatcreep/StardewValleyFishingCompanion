package com.example.core.fish

import com.google.gson.annotations.SerializedName

data class Price(val normal: Int, val silver: Int, val gold:Int, val iridium: Int)

data class FishData(
    var name: String = "",
    var id: Int = 9999,
    val description: String = "",
    @SerializedName("image_url")
    val imageUrl: String? = null,
    val price: Price? = null,
    val locations: List<String>? = null,
    @SerializedName("available_times")
    val availableTimes: List<String>? = null,
    @SerializedName("available_seasons")
    val availableSeasons: List<String>? = null,
    @SerializedName("available_weather")
    val availableWeather: List<String>? = emptyList(),
    val size: String = "",
    val bundles: List<String>? = null
)

interface FishRepository {
    val fishData: List<FishData>
    fun getSingleFish(fishId: Int): FishData
}