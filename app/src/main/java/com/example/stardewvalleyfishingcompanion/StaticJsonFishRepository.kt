package com.example.stardewvalleyfishingcompanion

import android.content.Context
import coil.request.ImageRequest
import com.example.stardewvalleyfishingcompanion.utils.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.annotations.JsonAdapter
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

data class Price(val normal: Int, val silver: Int, val gold:Int, val iridium: Int)

data class FishData(
    val name: String,
    val description: String,
    val image: String?,
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

    override fun getAllFishData(): Flow<List<FishData>> = flow {
        val jsonString = getJsonDataFromAsset(context, "fish.json")
        emit(gson.fromJson(jsonString, listFishType))


    }
}