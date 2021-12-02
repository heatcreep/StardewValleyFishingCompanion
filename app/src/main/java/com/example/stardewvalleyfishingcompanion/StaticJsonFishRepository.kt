package com.example.stardewvalleyfishingcompanion

import android.content.Context
import com.example.stardewvalleyfishingcompanion.utils.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken

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

class StaticJsonFishRepository(
    private val context: Context
): FishRepository {
    val gson = Gson()
    val listFishType = object : TypeToken<List<FishData>>() {}.type

    override fun getAllFishData(): List<FishData> {
        val jsonString = getJsonDataFromAsset(context, "fish.json")
        return gson.fromJson(jsonString, listFishType)
    }
}