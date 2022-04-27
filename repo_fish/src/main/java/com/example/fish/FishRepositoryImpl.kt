package com.example.fish

import android.app.Application
import android.content.Context
import com.example.core.fish.FishData
import com.example.core.fish.FishRepository
import com.example.fish.utils.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import javax.inject.Inject

class FishRepositoryImpl @Inject constructor(
    private val context: Application
): FishRepository {
    private val gson = Gson()
    private val listFishType: Type = object : TypeToken<List<FishData>>() {}.type
    override val fishData = getAllFishData()

    private fun getAllFishData(): List<FishData> {
        val jsonString = getJsonDataFromAsset(context, "fishv2.json")
        return gson.fromJson(jsonString, listFishType)
    }

    override fun getSingleFish(fishId: Int): FishData {
        return fishData[fishId]
    }
}