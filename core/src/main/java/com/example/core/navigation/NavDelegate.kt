package com.example.core.navigation

import com.example.core.fish.FishData

interface NavDelegate {
    fun goBack()
    fun goToFishDetails(fishName: Int)
}