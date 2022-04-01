package com.example.core.fish

interface FishDetailRepository {
    fun getFishDetails(allFish: List<FishData>, fishId: String): FishData
}