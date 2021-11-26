package com.example.stardewvalleyfishingcompanion

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData

class FishViewModel(private val repository: FishRepository): ViewModel() {
    val allFish: LiveData<List<FishData>> get() = repository.getAllFishData().asLiveData()
}