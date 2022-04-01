package com.example.ui_fishdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.fish.FishData
import com.example.core.fish.FishRepository
import javax.inject.Inject
import javax.inject.Named

class FishDetailViewModel @Inject constructor(
    private val fishRepository: FishRepository,
    @Named("fishId") private val fishId: Int
) {

    private var _fishDetail = MutableLiveData(fishRepository.getSingleFish(fishId))
    val fishDetail: LiveData<FishData> = _fishDetail

    fun getFishDetail(): FishData {
        return fishRepository.getSingleFish(fishId)
    }

}