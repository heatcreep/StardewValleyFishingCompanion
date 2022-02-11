package com.example.main.fish

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.fish.FishData
import com.example.core.fish.FishRepository
import javax.inject.Inject


class FishViewModel @Inject constructor(
    repository: FishRepository
) {
    private var _allFish = MutableLiveData(repository.getAllFishData())
    val allFish: LiveData<List<FishData>> = _allFish
}