package com.example.fish

import com.example.core.fish.FishData
import com.example.core.fish.FishDetailRepository

class FishDetailRepositoryImpl : FishDetailRepository{

    override fun getFishDetails(allFish: List<FishData>, fishId: String): FishData {
        return allFish[fishId.toInt()]
    }
}