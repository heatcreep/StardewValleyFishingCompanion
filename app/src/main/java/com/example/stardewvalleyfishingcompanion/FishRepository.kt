package com.example.stardewvalleyfishingcompanion

import kotlinx.coroutines.flow.Flow

interface FishRepository {
    fun getAllFishData(): Flow<List<FishData>>
}