package com.example.core.fish

import com.example.core.R
import com.google.gson.annotations.SerializedName

data class Price(val regular: Int = 0, val silver: Int = 0, val gold: Int = 0, val iridium: Int = 0)
data class AvailableTimes(val start: Int = 0, val end: Int = 26)

data class FishData(
    var name: String = "",
    var id: Int = 9999,
    val description: String = "",
    val image: String = "",
    val price: Price = Price(),
    val locations: List<String> = emptyList(),
    @SerializedName("available_times")
    val availableTimes: List<AvailableTimes> = listOf(AvailableTimes()),
    @SerializedName("available_seasons")
    val availableSeasons: List<String> = emptyList(),
    @SerializedName("available_weather")
    val availableWeather: List<String>? = emptyList(),
    val size: String = "",
    val bundles: List<String>? = null

) {
    fun isAvailableInSeason(season: String) = availableSeasons.contains(season)

    fun getImageResource(image: String) {
        when (image) {
            "albacore" -> R.drawable.albacore
            "anchovy" -> R.drawable.anchovy
            else ->
                R.drawable.junimo

        }
    }
}

interface FishRepository {
    val fishData: List<FishData>
    fun getSingleFish(fishId: Int): FishData
}