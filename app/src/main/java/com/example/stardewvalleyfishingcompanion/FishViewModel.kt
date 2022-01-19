package com.example.stardewvalleyfishingcompanion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class FishViewModel(repository: FishRepository) : ViewModel() {
    private var _allFish = MutableLiveData(repository.getAllFishData())
    val allFish: LiveData<List<FishData>> = _allFish
}